package qqweb.login.repository;

import org.hibernate.criterion.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import qqweb.login.domain.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User,String>, JpaSpecificationExecutor<User> {
    public List<User> findByUsername(String username);
    public List<User> findByUsernameAndPassword(String username , String password);
    public List<User> findAllByUsernameLike(String key);
    public List<User> findAllByFirstpinyinLike(String key);
    public List<User> findAllByPinyinLike(String key);
}
