package qqweb.login.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import qqweb.login.domain.Administrator;

import java.util.List;

public interface AdministratorRepository extends JpaRepository<Administrator,String> {
    public List<Administrator> findByName(String name);
    public List<Administrator> findByNameAndPassword(String name,String password);
}
