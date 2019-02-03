package qqweb.login.controller;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import qqweb.login.Service.ProductService;
import qqweb.login.Util.PinYinUtil;
import qqweb.login.domain.User;
import qqweb.login.repository.UserRepository;

import java.util.List;

@RestController
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductService productService;

    @PostMapping(value = "/register")
    public String userAdd(@RequestParam("username") String username,
                          @RequestParam("password") String password,
                          @RequestParam("repassword") String repassword){
        System.out.println("取得参数：" + username +" "+ password +" "+ repassword);
        if(!password.equals(repassword)){
            System.out.println("no1");
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
                              @RequestParam("password") String password){
        System.out.println("取得参数:" + username + " " + password);
        if(!userRepository.findByUsername(username).isEmpty()){
            if(userRepository.findByUsernameAndPassword(username,password).isEmpty()){
                return "no";
            }else{
                User user = new User();
                if(user.getEnter()==1){
                    System.out.println("用户登录成功");
                }else{
                    return "no2";
                }
                return "yes";
            }
        }else{
            return "no1";
        }
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
    public List<User> findFriend(@RequestParam("username") String username,
                                 Model model){
        logger.info("username:" + username);
        List<User> list;
        if(!productService.searchUser(username).isEmpty()){
            list = productService.searchUser(username);
        }else if(!productService.searchFirst(username).isEmpty()){
            list = productService.searchFirst(username);
        }else{
            list = productService.searchPinYin(username);
        }
        logger.info("查询个数" + list.size());
//        String json = JSON.toJSONString(list);
//        return json;
//        List<User> list = userRepository.findByUsername(username);
        System.out.println(list);
        return list;
    }
}
