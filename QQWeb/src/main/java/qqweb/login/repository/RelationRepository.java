package qqweb.login.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import qqweb.login.domain.Relation;

public interface RelationRepository extends JpaRepository<Relation,Integer> {
    public String findByFinduserAndFriend(String finduser,String friend);
}
