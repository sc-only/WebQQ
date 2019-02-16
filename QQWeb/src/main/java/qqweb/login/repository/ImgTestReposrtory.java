package qqweb.login.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import qqweb.login.domain.imgTest;

@Repository
public interface ImgTestReposrtory extends JpaRepository<imgTest,String> {

}
