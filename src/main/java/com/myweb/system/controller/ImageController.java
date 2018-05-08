package com.myweb.system.controller;

import com.myweb.core.WebContent;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * 显示图片controller
 */

@RestController
@RequestMapping("/image")
public class ImageController {


    @RequestMapping("/{imagePath}/{type}")
    public void getImage(@PathVariable(value = "imagePath") String imagePath, @PathVariable("type") String type, HttpServletResponse response) throws IOException {
        try {
            response.setContentType("image/" + type);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());

            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(WebContent.UPLOAD_PATH + imagePath + "." + type));
            int data;
            while ((data = bufferedInputStream.read()) != -1) {
                bufferedOutputStream.write(data);
            }
            bufferedOutputStream.flush();
            return;
        } finally {
            response.getOutputStream().close();
        }
    }

}
