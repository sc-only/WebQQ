package qqweb.login.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import qqweb.login.Util.PinYinUtil;
import qqweb.login.domain.Administrator;
import qqweb.login.domain.User;
import qqweb.login.repository.AdministratorRepository;
import qqweb.login.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class AdministratorController {
    @Autowired
    private AdministratorRepository administratorRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping(value = "/login2")
    public String administratorFindOne(@RequestParam("name") String name,
                                       @RequestParam("password") String password,HttpServletRequest request){
        System.out.println("取得参数" + name + " " + password);
        if(administratorRepository.findByName(name).isEmpty()){
            return "no1";
        }else{
            if(administratorRepository.findByNameAndPassword(name,password).isEmpty()){
                HttpSession session = request.getSession();
                Administrator administrator = new Administrator();
                administrator.setName(name);
                administrator.setPassword(password);
                session.setAttribute("administrator",administrator);
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

    @PostMapping(value = "/change")
    public String administratorUpdate(@RequestParam("name") String name,
                                      @RequestParam("password") String password,
                                      @RequestParam("repassword") String repassword){
        System.out.println("取得参数" + name + " " + password + " " + repassword);
        if(administratorRepository.findByName(name).isEmpty()){
            return "no1";
        }else{
            if(!password.equals(repassword)){
                return "no2";
            }else{
                if(password.length()>=6&&password.length()<=16){
                    User user = new User();
                    user.setUsername(name);
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
    @PostMapping(value = "/ban")
    public String administratorBan(@RequestParam("username") String username,
                                   @RequestParam("password") String password){
        System.out.println("取得参数" + username);
        User user = new User();
        user.setUsername(username);
        user.setEnter(0);
        user.setPassword(password);
        user.setFirstpinyin(PinYinUtil.toFirstChar(username));
        user.setPinyin(PinYinUtil.toPinyin(username));
        userRepository.save(user);
        return "yes";
    }

    @PostMapping(value = "/find")
    public String administratorFind(){
        List<User> list = userRepository.findAll();
        String json = JSON.toJSONString(list);
        return json;
    }
}
