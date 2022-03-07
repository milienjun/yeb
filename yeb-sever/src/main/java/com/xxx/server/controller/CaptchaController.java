package com.xxx.server.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * 验证码
 */
@RestController
public class CaptchaController {
    @Autowired
    private DefaultKaptcha defaultKaptcha;

    @ApiOperation(value = "验证码")
    @GetMapping(value = "/captcha",produces = "image/jpeg")
    public void captcha(HttpServletRequest request, HttpServletResponse response) {
        // 定义 response 输出类型为 image/jpeg 类型
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store,no-cache,must-revalidate");
        response.addHeader("Cache-Control", "post-check=0,pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");
        //-----------------生成验证码begin--------------//
        //获取验证码文本内容
        String text = defaultKaptcha.createText();
        System.out.println("验证码内容:" + text);
        //将验证码生成的文本内容放进session
        request.getSession().setAttribute("captcha", text);
        //根据文本内容创建图形验证码
        BufferedImage image=defaultKaptcha.createImage(text);
        ServletOutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            ImageIO.write(image, "jpg", outputStream);
            outputStream = response.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != outputStream) {

                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        }

        //-----------------生成验证码end--------------------//

    }
}
