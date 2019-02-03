package qqweb.login.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import qqweb.login.domain.Administrator;

import java.util.List;

@Repository
public interface AdministratorRepository extends JpaRepository<Administrator,String> {
    public List<Administrator> findByName(String name);
    public List<Administrator> findByNameAndPassword(String name,String password);
}
