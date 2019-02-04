package qqweb.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import qqweb.login.Util.PinYinUtil;
import qqweb.login.domain.User;
import qqweb.login.repository.AdministratorRepository;
import qqweb.login.repository.UserRepository;

@RestController
public class AdministratorController {
    @Autowired
    private AdministratorRepository administratorRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping(value = "/login2")
    public String administratorFindOne(@RequestParam("name") String name,
                                       @RequestParam("password") String password){
        System.out.println("取得参数" + name + " " + password);
        if(administratorRepository.findByName(name).isEmpty()){
            return "no1";
        }else{
            if(administratorRepository.findByNameAndPassword(name,password).isEmpty()){
                return "no2";
            }else{
                System.out.println("管理员登录成功");
                return "yes";
            }
        }
    }

    @PostMapping(value = "/delete")
    public String administratorDelete(@RequestParam("username") String username){
        System.out.println("取得参数" + username);
        if(userRepository.findByUsername(username).isEmpty()){
            return "no";
        }else{
            userRepository.deleteById(username);
            return "yes";
        }
    }

    @PostMapping(value = "/add")
    public String administratorAdd(@RequestParam("username") String username,
                                   @RequestParam("password") String password,
                                   @RequestParam("repassword") String repassword){
        System.out.println("取得参数" + username + " " + password + " " + repassword);
        if(!password.equals(repassword)){
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

    @PostMapping(

    )
}
