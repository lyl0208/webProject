package com.myweb.system.controller;

import com.myweb.core.ResultMap;
import com.myweb.core.exception.ParameterException;
import com.myweb.core.utils.UUIDUtil;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@RestController
@RequestMapping("/upload")
public class UploadController {

    private static final String PHONEIMG_PATH = "phoneImg" + File.separator;

    private static final String BRANDIMG_PATH = "brandImg" + File.separator;

    @PostMapping("")
    public ResultMap uploadPhoneImg(@RequestParam("file")MultipartFile file,String type) throws Exception {
        //判断是手机图片还是品牌图片
        String typePath = "phone".equals(type)?PHONEIMG_PATH:"brand".equals(type)?BRANDIMG_PATH:null;
        if (typePath == null) {
            throw new ParameterException("参数有误");
        }
        //如果文件内容不为空，则写入上传路径
        if (!file.isEmpty()) {
            //上传文件名
            String fileName = UUIDUtil.randomUUID8() + file.getOriginalFilename().substring(file.getOriginalFilename().indexOf(".") - 1);
            File filePath = new File(getImageSavePath() + typePath,fileName);

            //判断路径是否存在，没有就创建一个
            if (!filePath.getParentFile().exists()) {
                filePath.getParentFile().mkdirs();
            }

            //将上传文件保存到一个目标文档中
            File file1 = new File(getImageSavePath() + typePath + fileName);
            file.transferTo(file1);
            System.err.println("图片上传路径：" + file1.getAbsolutePath());
            return ResultMap.ok("图片上传成功").put("url",fileName);

        } else {
            return ResultMap.error("图片上传失败");
        }

    }


    /**
     * 获取图片保存地址
     * @return
     */
    private String getImageSavePath() {
        String systemProperty = System.getProperty("webApp.root");
        return systemProperty.substring(0,systemProperty.indexOf("out")) + File.separator + "web"+File.separator+"static"+File.separator+"images"+File.separator;
    }
}
