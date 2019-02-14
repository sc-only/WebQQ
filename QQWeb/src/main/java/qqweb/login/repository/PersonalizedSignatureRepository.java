package qqweb.login.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import qqweb.login.domain.PersonalizedSignature;

import java.util.List;

@Repository
public interface PersonalizedSignatureRepository extends JpaRepository<PersonalizedSignature,String> {
    public List<PersonalizedSignature> findByUsername(String username);
}
