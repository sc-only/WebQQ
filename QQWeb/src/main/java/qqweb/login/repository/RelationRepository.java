package qqweb.login.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import qqweb.login.domain.Relation;
import qqweb.login.domain.User;

import java.util.List;

@Repository
public interface RelationRepository extends JpaRepository<Relation,Integer> {
    public List<Relation> findByFinduserAndFriend(String finduser,String friend);
    public List<Relation> findByFinduser(String username);
    public List<Relation> findByFriend(String friend);
}
