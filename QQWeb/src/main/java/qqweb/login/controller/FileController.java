package qqweb.login.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import qqweb.login.domain.imgTest;
import qqweb.login.repository.ImgTestReposrtory;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;

/*
 * @author 杨友奇
 * @Time 2018/7/27
 */
@Controller
public class FileController {

    @Value("${com.ccr}")
    //获取主机端口
    private String post;
    //获取本机ip
    private String host;
    //图片存放根路径
    private String rootPath = "G:";
    //图片存放根目录下的子目录
    private String sonPath = "/img/";
    //获取图片链接
    private String imgPath;

    @Autowired
    private ImgTestReposrtory imgTestReposrtory;
    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @RequestMapping(value = "upload")
    @ResponseBody
    public String upload(@RequestParam("test") MultipartFile file,
                         @RequestParam("username") String username) {
        //返回上传的文件是否为空，即没有选择任何文件，或者所选文件没有内容。
        //防止上传空文件导致奔溃
        if (file.isEmpty()) {
            return "文件为空";
        }

        //获取本机IP
        try {
            host = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            logger.error("get server host Exception e:", e);
        }

        // 获取文件名
        String fileName = file.getOriginalFilename();
        //logger.info("上传的文件名为：" + fileName);
        // 设置文件上传后的路径
        String filePath = rootPath + sonPath;
        logger.info("上传的文件路径" + filePath);
        logger.info("整个图片路径：" + host + ":" + post + sonPath + fileName);
        //创建文件路径
        File dest = new File(filePath + fileName);

        String imgPath = (host + ":" + post + sonPath + fileName).toString();

        // 解决中文问题，liunx下中文路径，图片显示问题
        // fileName = UUID.randomUUID() + suffixName;

        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
            //假如文件不存在即重新创建新的文件已防止异常发生
            dest.getParentFile().mkdirs();
        }
        try {
            //transferTo（dest）方法将上传文件写到服务器上指定的文件
            file.transferTo(dest);

            //将链接保存到URL中
            imgTest imgTest = new imgTest();
            imgTest.setUsername(username);
            imgTest.setUrl(imgPath);
            imgTestReposrtory.save(imgTest);
//            imgTest imgTest = service.add(new imgTest(),imgPath,username);
            return "上传成功";
        } catch (Exception e) {
            return "上传失败";
        }
    }
}
