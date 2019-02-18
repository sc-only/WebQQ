package qqweb.login.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import qqweb.login.domain.imgTest;

import java.util.List;


public interface ImgTestReposrtory extends JpaRepository<imgTest,String>{
    public List<imgTest> findByUsername(String username);
}
