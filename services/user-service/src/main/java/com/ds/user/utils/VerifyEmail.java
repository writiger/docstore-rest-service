package com.ds.user.utils;

import com.ds.common.utils.ToEmail;

/**
 * @author writiger
 * @description 邮箱验证模板
 * @create_at 2024-03-14 18:12
 */
public class VerifyEmail extends ToEmail {
    /**
     *
     * @param to 邮件接收人
     * @param verifyCode 验证码
     */
    public VerifyEmail(String to,String verifyCode){
        this.setTos(new String[]{to});
        this.setSubject("DocStore");
        this.setContent("<body style=\"\n" +
                "            text-align: center;\n" +
                "            margin-left: auto;\n" +
                "            margin-right: auto;\n" +
                "\">\n" +
                "<div id=\"welcome\" style=\"text-align: center;\">\n" +
                "    <h3>欢迎使用 <span>DocStore</span> - Powered By <a href=\"https://github.com/writiger\">Writiger</a></h3>\n" +
                "    <span></span>\n" +
                "    <div style=\"text-align: center; padding: 10px\">\n" +
                "        <strong>欢迎注册论文检索系统</strong>\n" +
                "        <br>\n" +
                "        <strong>\n" +
                "            这是您的验证码："+verifyCode+"\n" +
                "        </strong>\n" +
                "        <br>\n" +
                "        如非本人操作请忽略此邮件.\n" +
                "    </div>\n" +
                "</div>\n" +
                "</body>");
    }

}
