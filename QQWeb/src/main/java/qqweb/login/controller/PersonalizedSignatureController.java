package qqweb.login.controller;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import qqweb.login.domain.PersonalizedSignature;
import qqweb.login.domain.Relation;
import qqweb.login.repository.PersonalizedSignatureRepository;
import qqweb.login.repository.RelationRepository;

import java.util.List;

@RestController
public class PersonalizedSignatureController {

    private Logger logger = LoggerFactory.getLogger(PersonalizedSignatureController.class);

    @Autowired
    private PersonalizedSignatureRepository personalizedSignatureRepository;

    @Autowired
    private RelationRepository relationRepository;

    @PostMapping(value = "/ps")
    public String personalizedSignatureAdd(@RequestParam("name") String name,
                                           @RequestParam("ps") String ps){
        System.out.println("取得参数" + name + " " + ps);
        PersonalizedSignature personalizedSignature = new PersonalizedSignature();
        personalizedSignature.setUsername(name);
        personalizedSignature.setSignature(ps);
        personalizedSignatureRepository.save(personalizedSignature);
        return "yes";
    }

    @PostMapping(value = "/getps")
    public String getPS(@RequestParam("username") String username){
        List<PersonalizedSignature> list = personalizedSignatureRepository.findByUsername(username);
        if(list.isEmpty()){
            return " ";
        }else{
            String json = JSON.toJSONString(list);
            System.out.println(json);
            return json;
        }
    }

    @PostMapping(value = "/dianzan/{username}")
    public int dianzan(@PathVariable("username") String username,
                          @RequestParam("friend") String friend,
                          @RequestParam("zan") int zan){
        if(zan==1){
            Relation relation = new Relation();
            relation.setZan(0);
            relation.setFinduser(username);
            relation.setFriend(friend);
            relation.setRela(username+friend);
            relationRepository.save(relation);
            return 0;
        }else{
            Relation relation = new Relation();
            relation.setZan(1);
            relation.setFinduser(username);
            relation.setFriend(friend);
            relation.setRela(username+friend);
            relationRepository.save(relation);
            return 1;
        }
    }

    @GetMapping(value = "/sum/{username}")
    public String getSum(@PathVariable("username") String username){
        List<Relation> list = relationRepository.findByFriend(username);
        String json = JSON.toJSONString(list);
        return json;
    }

}
