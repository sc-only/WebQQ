package qqweb.login.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import qqweb.login.Service.imgService;
import qqweb.login.domain.imgTest;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

@Controller
public class FileController {

    @Autowired
    private imgService imgService;
    
    private String post ;
    
    private String host ;
    
    private String rootPath = "G:";

    private String sonPath = "/img/";

    private String imgPath;

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @RequestMapping(value = "upload")
    @ResponseBody
    public String upload(@RequestParam("test")MultipartFile file,
                         @RequestParam("username") String username){
        if(file.isEmpty()){
            return "文件为空";
        }

        try {
            host = InetAddress.getLocalHost().getHostAddress();
        }catch (UnknownHostException e){
            logger.error("get server host Exception e:",e);
        }

        String fileName = file.getOriginalFilename();
        String filePath = rootPath + sonPath;
        logger.info("上传的文件路径" + filePath);
        logger.info("整个图片路径：" + host + ":" + post + sonPath + fileName);

        File dest = new File(filePath + fileName);

        String imgPath = (host + ":" + post + sonPath + fileName).toString();

        if(!dest.getParentFile().exists()){
            dest.getParentFile().mkdirs();
        }

        try {
            file.transferTo(dest);

            imgTest imgTest = imgService.add(new imgTest(),imgPath,username);
            return "上传成功";
        } catch (Exception e) {
            return "上传失败";
        }
    }
}
