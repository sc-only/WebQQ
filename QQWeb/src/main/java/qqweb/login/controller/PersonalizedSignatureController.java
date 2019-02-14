package qqweb.login.controller;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import qqweb.login.domain.PersonalizedSignature;
import qqweb.login.repository.PersonalizedSignatureRepository;

import java.util.List;

@RestController
public class PersonalizedSignatureController {

    private Logger logger = LoggerFactory.getLogger(PersonalizedSignatureController.class);

    @Autowired
    private PersonalizedSignatureRepository personalizedSignatureRepository;

    @PostMapping(value = "/ps")
    public String personalizedSignatureAdd(@RequestParam("username") String username,
                                           @RequestParam("ps") String ps){
        System.out.println("取得参数" + username + " " + ps);
        PersonalizedSignature personalizedSignature = new PersonalizedSignature();
        personalizedSignature.setUsername(username);
        personalizedSignature.setSignature(ps);
        personalizedSignatureRepository.save(personalizedSignature);
        return "yes";
    }

    @GetMapping(value = "/getps/{username}")
    public String getPS(@PathVariable("username") String username){
        List<PersonalizedSignature> list = personalizedSignatureRepository.findByUsername(username);
        if(list.isEmpty()){
            return " ";
        }else{
            String json = JSON.toJSONString(list);
            return json;
        }
    }
}
