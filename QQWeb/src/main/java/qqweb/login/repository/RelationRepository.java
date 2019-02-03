package qqweb.login.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import qqweb.login.domain.Relation;

@Repository
public interface RelationRepository extends JpaRepository<Relation,Integer> {
    public String findByFinduserAndFriend(String finduser,String friend);
}
