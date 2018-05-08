package com.myweb.system.controller;

import com.myweb.core.ResultMap;
import com.myweb.core.WebContent;
import com.myweb.core.exception.ParameterException;
import com.myweb.core.utils.UUIDUtil;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@RestController
@RequestMapping("/upload")
public class UploadController {


    @PostMapping("")
    public ResultMap uploadPhoneImg(@RequestParam("file")MultipartFile file) throws Exception {
        //判断是手机图片还是品牌图片
        //如果文件内容不为空，则写入上传路径
        if (!file.isEmpty()) {
            //上传文件名
            String fileName = UUIDUtil.randomUUID8() + file.getOriginalFilename().substring(file.getOriginalFilename().indexOf(".") - 1);
            File filePath = new File(WebContent.UPLOAD_PATH,fileName);

            //判断路径是否存在，没有就创建一个
            if (!filePath.getParentFile().exists()) {
                filePath.getParentFile().mkdirs();
            }

            //将上传文件保存到一个目标文档中
            File file1 = new File(WebContent.UPLOAD_PATH + fileName);
            file.transferTo(file1);
            System.err.println("图片上传路径：" + file1.getAbsolutePath());
            return ResultMap.ok("图片上传成功").put("url",fileName);

        } else {
            return ResultMap.error("图片上传失败");
        }

    }

}
