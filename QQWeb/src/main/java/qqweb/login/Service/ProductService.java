package qqweb.login.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import qqweb.login.domain.User;
import qqweb.login.repository.UserRepository;

import java.nio.file.attribute.UserPrincipal;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private UserRepository userRepository;
    public List<User> searchUser(String username){
        return userRepository.findAllByUsernameLike("%" + username + "%");
    }
    public List<User> searchFirst(String username){
        return userRepository.findAllByFirstpinyinLike("%" + username + "%");
    }
    public List<User> searchPinYin(String username){
        return userRepository.findAllByPinyinLike("%" + username + "%");
    }
}
