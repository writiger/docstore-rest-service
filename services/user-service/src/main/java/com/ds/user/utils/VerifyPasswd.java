package com.ds.user.utils;

import com.ds.common.utils.ToEmail;

/**
 * @author writiger
 * @description 修改密码验证模板
 * @create_at 2024-03-16 14:34
 */
public class VerifyPasswd extends ToEmail {
    /**
     * @param url 前端地址
     * @param to 邮件接收人
     * @param verifyCode 验证码
     */
    public VerifyPasswd(String to,String url,String verifyCode){
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
                "        <strong>请访问如下地址修改密码</strong>\n" +
                "        <br>\n" +
                "        <strong>\n" +
                "            <a href='"+url+"/#/forget/"+verifyCode+"'>点我修改</a>\n" +
                "        </strong>\n" +
                "        <br>\n" +
                "        如非本人操作请忽略此邮件.\n" +
                "    </div>\n" +
                "</div>\n" +
                "</body>");
    }
}
