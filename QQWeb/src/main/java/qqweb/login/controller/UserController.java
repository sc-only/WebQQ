package qqweb.login.controller;

import com.alibaba.fastjson.JSON;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import qqweb.login.Service.ProductService;
import qqweb.login.Util.ConstantUtils;
import qqweb.login.Util.PinYinUtil;
import qqweb.login.domain.Relation;
import qqweb.login.domain.User;
import qqweb.login.domain.imgTest;
import qqweb.login.repository.ImgTestReposrtory;
import qqweb.login.repository.RelationRepository;
import qqweb.login.repository.UserRepository;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private RelationRepository relationRepository;

    @Autowired
    private ImgTestReposrtory imgTestReposrtory;

    public String jsonsum;

    @PostMapping(value = "/register")
    public String userAdd(@RequestParam("username") String username,
                          @RequestParam("password") String password,
                          @RequestParam("repassword") String repassword){
        System.out.println("取得参数：" + username +" "+ password +" "+ repassword);
        if(!password.equals(repassword)){
//            System.out.println("no1");
           return "no1";
        }else{
            if(userRepository.findByUsername(username).isEmpty()){
                if(password.length()>=6 && password.length()<=16) {
                    User user = new User();
                    user.setUsername(username);
                    user.setPassword(password);
                    user.setFirstpinyin(PinYinUtil.toFirstChar(username));
                    user.setPinyin(PinYinUtil.toPinyin(username));
                    user.setEnter(1);
                    userRepository.save(user);
                    System.out.println("注册成功");
                    return "yes";
                }else{
                    System.out.println("no2");
                    return "no2";
                }
            }else{
                return "no";
            }
        }
    }

    @PostMapping(value = "/login")
    public String userFindOne(@RequestParam("username") String username,
                              @RequestParam("password") String password, HttpServletRequest request) throws JSONException {
        System.out.println("取得参数:" + username + " " + password);
        List<User> list = userRepository.findByUsername(username);
        if(!list.isEmpty()){
            if(userRepository.findByUsernameAndPassword(username,password).isEmpty()){
                return "no";
            }else{
                HttpSession session = request.getSession();
                User user = new User();
                user.setUsername(username);
                user.setPassword(password);
                session.setAttribute("user",user);
                System.out.println("用户登录成功");
                String json =JSON.toJSONString(list);
                jsonsum = json;
                System.out.println(jsonsum);
                return json;
            }
        }else{
            return "no1";
        }
    }

    @GetMapping(value = "/username")
    public String user(){
        System.out.println(jsonsum);
        return jsonsum;
    }

    @PostMapping(value = "/update")
    public String userUpdate(@RequestParam("username") String username,
                             @RequestParam("password") String password,
                             @RequestParam("repassword") String repassword){
        System.out.println("取得参数" + username + " " + password + " " + repassword);
        if(userRepository.findByUsername(username).isEmpty()){
            return "no1";
        }else{
            if(!password.equals(repassword)){
                return "no2";
            }else{
                if(password.length()>=6&&password.length()<=16){
                    User user = new User();
                    user.setUsername(username);
                    user.setPassword(password);
                    user.setFirstpinyin(PinYinUtil.toFirstChar(username));
                    user.setPinyin(PinYinUtil.toPinyin(username));
                    user.setEnter(1);
                    userRepository.save(user);
                    System.out.println("密码修改成功");
                    return "yes";
                }else{
                    return "no3";
                }
            }
        }
    }

    @PostMapping(value = "/search")
    public String findFriend(@RequestParam("username") String username){
        logger.info("username:" + username);
        List<User> list;
        if(!productService.searchFirst(username).isEmpty()){
            list = productService.searchFirst(username);
        }else if(!productService.searchUser(username).isEmpty()){
            list = productService.searchUser(username);
        } else{
            list = productService.searchPinYin(username);
        }
        logger.info("查询个数" + list.size());
//      ` String json = JSON.toJSONString(list);
//        return json;
//        List<User> list = userRepository.findByUsername(username);
        String json = JSON.toJSONString(list);
        return json;
    }

    @GetMapping(value = "/get/{username}")
    public String getFriend(@PathVariable("username") String username){
        System.out.println(username);
        List<Relation> list = relationRepository.findByFinduser(username);
        String json = JSON.toJSONString(list);
        return json;
    }

    @PostMapping(value = "/headimage")
    public String getHeadimg(@RequestParam("username") String username){
        System.out.println("取得参数" + username);
        List<imgTest> list = imgTestReposrtory.findByUsername(username);
        String json = JSON.toJSONString(list);
        return json;
    }
}
