package io.renren.common.utils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import io.renren.common.exception.RRException;
import io.renren.common.utils.MailBean;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lance(ZYH)
 * @function 邮件发送工具类
 * @date 2018-07-07 13:14
 */
@Slf4j
public class SendMailUtils {
    @Value("${lance.mail.sender}")
    private static String MAIL_SENDER; //邮件发送者
    @Value("${lance.mail.url}")
    private static String MAIL_URL; //邮件发送者

    @Autowired
    private static JavaMailSender javaMailSender;

    @Autowired
    private static Configuration configuration; //freemarker

    /**
     * 发送基于Freemarker模板的邮件
     *
     * @param mailBean
     */
    public static void sendTemplateMail(MailBean mailBean) throws RRException {
        MimeMessage mimeMailMessage = null;
        try {
            mimeMailMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage, true);
            mimeMessageHelper.setFrom(MAIL_SENDER);
            mimeMessageHelper.setTo(mailBean.getRecipient());
            mimeMessageHelper.setSubject(mailBean.getSubject());

            Map<String, Object> model = new HashMap<String, Object>();
            model.put("content", mailBean.getContent());
            model.put("url", MAIL_URL);
            String type = "";
            if ("1".equals(mailBean.getType())) {
                type = "1.ftl";
            } else if ("2".equals(mailBean.getType())) {
                type = "2.ftl";
            } else if ("3".equals(mailBean.getType())) {
                type = "3.ftl";
            } else if ("4".equals(mailBean.getType())) {
                type = "4.ftl";
            } else if ("5".equals(mailBean.getType())) {
                type = "5.ftl";
            }

            Template template = configuration.getTemplate(type);
            String text = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
            mimeMessageHelper.setText(text, true);

            javaMailSender.send(mimeMailMessage);
        } catch (Exception e) {
            throw new RRException("邮件发送失败" + e.getMessage());
        }

    }


}
