package io.renren.common.utils;

import lombok.Data;

import java.io.Serializable;

@Data
public class MailBean implements Serializable {
    private String recipient;   //邮件接收人
    private String subject; //邮件主题
    private String content; //邮件内容

    private String type;//模板类型

}
