package qqweb.login.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import qqweb.login.domain.Relation;
import qqweb.login.repository.RelationRepository;
import qqweb.login.repository.UserRepository;

@RestController
public class FindController {

    private Logger logger = LoggerFactory.getLogger(FindController.class);

    @Autowired
    private RelationRepository relationRepository;

    @PostMapping(value = "/addfriend")
    public String addFriends(@RequestParam("finduser") String finduser,
                             @RequestParam("friend") String friend){
        System.out.println("取得参数" + finduser + " " + friend);
        if(relationRepository.findByFinduserAndFriend(finduser,friend).isEmpty()){
            Relation relationA = new Relation();
            Relation relationB = new Relation();
            relationA.setFinduser(finduser);
            relationB.setFriend(finduser);
            relationA.setFriend(friend);
            relationB.setFinduser(friend);
            relationA.setZan(0);
            relationB.setZan(0);
            relationRepository.save(relationA);
            relationRepository.save(relationB);
            return "yes";
        }else{
            return "no";
        }
    }
}
