package qqweb.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import qqweb.login.repository.AdministratorRepository;

@RestController
public class AdministratorController {
    @Autowired
    private AdministratorRepository administratorRepository;

    @PostMapping(value = "/login2")
    public String AdministratorFindOne(@RequestParam("name") String name,
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
}
