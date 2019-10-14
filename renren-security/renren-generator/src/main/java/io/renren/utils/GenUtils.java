/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package io.renren.utils;

import io.renren.entity.ColumnEntity;
import io.renren.entity.TableEntity;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 代码生成器   工具类
 *
 * @author Mark sunlightcs@gmail.com
 */
public class GenUtils {

    public static List<String> getTemplates() {
        List<String> templates = new ArrayList<String>();
        templates.add("template/Entity.java.vm" );
        templates.add("template/Dao.java.vm" );
        templates.add("template/Dao.xml.vm" );
        templates.add("template/Service.java.vm" );
        templates.add("template/ServiceImpl.java.vm" );
        templates.add("template/Controller.java.vm" );
//        templates.add("template/list.html.vm");
//        templates.add("template/list.js.vm");
//        templates.add("template/menu.sql.vm");
        templates.add("template/PageRequest.java.vm" );
        templates.add("template/InfoRequest.java.vm" );
        templates.add("template/SaveRequest.java.vm" );
        templates.add("template/UpdateRequest.java.vm" );
        templates.add("template/DelRequest.java.vm" );
        templates.add("template/PushRequest.java.vm" );

        templates.add("template/Api.js.vm" );
        templates.add("template/List.vue.vm" );
        templates.add("template/Create.vue.vm" );
        templates.add("template/Edit.vue.vm" );
        templates.add("template/Detail.vue.vm" );
        templates.add("template/Routes.js.vm" );
        return templates;
    }

    /**
     * 生成代码
     */
    public static void generatorCode(Map<String, String> table,
                                     List<Map<String, String>> columns, ZipOutputStream zip) {
        //配置信息
        Configuration config = getConfig();
        boolean hasBigDecimal = false;
        //表信息
        TableEntity tableEntity = new TableEntity();
        tableEntity.setTableName(table.get("tableName" ));
        tableEntity.setComments(table.get("tableComment" ));
        //表名转换成Java类名
        String className = tableToJava(tableEntity.getTableName(), config.getString("tablePrefix" ));
        tableEntity.setClassName(className);
        tableEntity.setClassname(StringUtils.uncapitalize(className));

        //列信息
        List<ColumnEntity> columsList = new ArrayList<>();
        List<ColumnEntity> columsBaseList = new ArrayList<>();
        for (Map<String, String> column : columns) {
            ColumnEntity columnEntity = new ColumnEntity();
            columnEntity.setColumnName(column.get("columnName" ));
            columnEntity.setDataType(column.get("dataType" ));
            columnEntity.setComments(column.get("columnComment" ));
            columnEntity.setExtra(column.get("extra" ));

            //列名转换成Java属性名
            String attrName = columnToJava(columnEntity.getColumnName());
            columnEntity.setAttrName(attrName);
            columnEntity.setAttrname(StringUtils.uncapitalize(attrName));

            //列的数据类型，转换成Java类型
            String attrType = config.getString(columnEntity.getDataType(), "unknowType" );
            columnEntity.setAttrType(attrType);
            if (!hasBigDecimal && attrType.equals("BigDecimal" )) {
                hasBigDecimal = true;
            }
            //是否主键
            if ("PRI".equalsIgnoreCase(column.get("columnKey" )) && tableEntity.getPk() == null) {
                tableEntity.setPk(columnEntity);
            }
            if (!columnEntity.getAttrname().equals("status" ) &&
                    !columnEntity.getAttrname().equals("remarks" ) &&
                    !columnEntity.getAttrname().equals("version" ) &&
                    !columnEntity.getAttrname().equals("createdUserId" ) &&
                    !columnEntity.getAttrname().equals("createdTime" ) &&
                    !columnEntity.getAttrname().equals("modifyTime" ) &&
                    !columnEntity.getAttrname().equals("modifyUserId" ) &&
                    !columnEntity.getAttrname().equals("sort" ) &&
                    !columnEntity.getAttrname().equals("id" ) &&
                    !columnEntity.getAttrname().equals("sysSystem" )
            ) {
                columsBaseList.add(columnEntity);
            }
            columsList.add(columnEntity);

        }
        tableEntity.setColumns(columsList);
        tableEntity.setColumnBase(columsBaseList);

        //没主键，则第一个字段为主键
        if (tableEntity.getPk() == null) {
            tableEntity.setPk(tableEntity.getColumns().get(0));
        }

        //设置velocity资源加载器
        Properties prop = new Properties();
        prop.put("file.resource.loader.class" , "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader" );
        Velocity.init(prop);

        String mainPath = config.getString("mainPath" );
        mainPath = StringUtils.isBlank(mainPath) ? "io.renren" : mainPath;

        //封装模板数据
        Map<String, Object> map = new HashMap<>();
        map.put("tableName" , tableEntity.getTableName());
        map.put("tableLabel" , tableEntity.getTableName().replace("_" , "-" ));
        map.put("comments" , tableEntity.getComments());
        map.put("pk" , tableEntity.getPk());
        map.put("className" , tableEntity.getClassName());
        map.put("classname" , tableEntity.getClassname());
        map.put("pathName" , tableEntity.getClassname().toLowerCase());
        map.put("columns" , tableEntity.getColumns());
        map.put("columnBase" , tableEntity.getColumnBase());
        map.put("hasBigDecimal" , hasBigDecimal);
        map.put("mainPath" , mainPath);
        map.put("package" , config.getString("package" ));
        map.put("moduleName" , config.getString("moduleName" ));
        map.put("roleName" , config.getString("roleName" ));
        map.put("roleCnName" , config.getString("roleCnName" ));
        map.put("author" , config.getString("author" ));
        map.put("email" , config.getString("email" ));
        map.put("symbol" , "$" );
        map.put("symbols" , "=>" );
        map.put("system",config.getString("system" ));
        map.put("menu",config.getString("menu" ));



        map.put("datetime" , DateUtils.format(new Date(), DateUtils.DATE_TIME_PATTERN));
        VelocityContext context = new VelocityContext(map);

        //获取模板列表
        List<String> templates = getTemplates();
        for (String template : templates) {
            //渲染模板
            StringWriter sw = new StringWriter();
            Template tpl = Velocity.getTemplate(template, "UTF-8" );
            tpl.merge(context, sw);

            try {
                //添加到zip
                zip.putNextEntry(new ZipEntry(getFileName(template, tableEntity.getClassName(), config.getString("package" ), config.getString("moduleName" ), config.getString("system" ))));
                IOUtils.write(sw.toString(), zip, "UTF-8" );
                IOUtils.closeQuietly(sw);
                zip.closeEntry();
            } catch (IOException e) {
                throw new RRException("渲染模板失败，表名：" + tableEntity.getTableName(), e);
            }
        }
    }


    /**
     * 列名转换成Java属性名
     */
    public static String columnToJava(String columnName) {
        return WordUtils.capitalizeFully(columnName, new char[]{'_'}).replace("_" , "" );
    }

    /**
     * 表名转换成Java类名
     */
    public static String tableToJava(String tableName, String tablePrefix) {
        if (StringUtils.isNotBlank(tablePrefix)) {
            tableName = tableName.replace(tablePrefix, "" );
        }
        return columnToJava(tableName);
    }

    /**
     * 获取配置信息
     */
    public static Configuration getConfig() {
        try {
            return new PropertiesConfiguration("generator.properties" );
        } catch (ConfigurationException e) {
            throw new RRException("获取配置文件失败，" , e);
        }
    }

    /**
     * 获取文件名
     */
    public static String getFileName(String template, String className, String packageName, String moduleName, String system) {
        String packagePath = "main" + File.separator + "java" + File.separator;
        if (StringUtils.isNotBlank(packageName)) {
            packagePath += packageName.replace("." , File.separator) + File.separator + moduleName + File.separator;
        }

        if (template.contains("Entity.java.vm" )) {
            return packagePath + "entity" + File.separator + className + "Entity.java";
        }

        if (template.contains("Dao.java.vm" )) {
            return packagePath + "dao" + File.separator + className + "Dao.java";
        }

        if (template.contains("Service.java.vm" )) {
            return packagePath + "service" + File.separator + className + "Service.java";
        }

        if (template.contains("ServiceImpl.java.vm" )) {
            return packagePath + "service" + File.separator + "impl" + File.separator + className + "ServiceImpl.java";
        }

        if (template.contains("PageRequest.java.vm" )) {
            return packagePath + "request" + File.separator + className + "PageRequest.java";
        }
        if (template.contains("DelRequest.java.vm" )) {
            return packagePath + "request" + File.separator + className + "DelRequest.java";
        }
        if (template.contains("InfoRequest.java.vm" )) {
            return packagePath + "request" + File.separator + className + "InfoRequest.java";
        }
        if (template.contains("SaveRequest.java.vm" )) {
            return packagePath + "request" + File.separator + className + "SaveRequest.java";
        }
        if (template.contains("UpdateRequest.java.vm" )) {
            return packagePath + "request" + File.separator + className + "UpdateRequest.java";
        }
        if (template.contains("PushRequest.java.vm" )) {
            return packagePath + "request" + File.separator + className + "PushRequest.java";
        }

        if (template.contains("Controller.java.vm" )) {
            return packagePath + "controller" + File.separator + className + "Controller.java";
        }

        if (template.contains("Dao.xml.vm" )) {
            return "main" + File.separator + "resources" + File.separator + "mapper" + File.separator + moduleName + File.separator + className + "Dao.xml";
        }

        if (template.contains("Api.js.vm" )) {
            return "main" + File.separator + "resources" + File.separator + "api" + File.separator + moduleName + File.separator + className + "Api.js";
        }
        if (template.contains("List.vue" )) {
            return "main" + File.separator + "resources" + File.separator + "views" + File.separator + system + File.separator + moduleName + File.separator + className.toLowerCase() + File.separator + className + "List.vue";
        }
        if (template.contains("Create.vue" )) {
            return "main" + File.separator + "resources" + File.separator + "views" + File.separator + system + File.separator + moduleName + File.separator + className.toLowerCase() + File.separator + className + "Create.vue";
        }
        if (template.contains("Edit.vue" )) {
            return "main" + File.separator + "resources" + File.separator + "views" + File.separator + system + File.separator + moduleName + File.separator + className.toLowerCase() + File.separator + className + "Edit.vue";
        }
        if (template.contains("Detail.vue" )) {
            return "main" + File.separator + "resources" + File.separator + "views" + File.separator + system + File.separator + moduleName + File.separator + className.toLowerCase() + File.separator + "components" + File.separator + className + "Detail.vue";
        }
        if (template.contains("Routes.js" )) {
            return "main" + File.separator + "resources" + File.separator + "router" + File.separator + system + File.separator + moduleName + File.separator + className + "Routes.js";
        }


//        if (template.contains("list.html.vm")) {
//            return "main" + File.separator + "resources" + File.separator + "templates" + File.separator
//                    + "modules" + File.separator + moduleName + File.separator + className.toLowerCase() + ".html";
//        }
//
//        if (template.contains("list.js.vm")) {
//            return "main" + File.separator + "resources" + File.separator + "statics" + File.separator + "js" + File.separator
//                    + "modules" + File.separator + moduleName + File.separator + className.toLowerCase() + ".js";
//        }

//        if (template.contains("menu.sql.vm")) {
//            return className.toLowerCase() + "_menu.sql";
//        }

        return null;
    }
}
