package qqweb.login.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Relation {
    @Id
    @GeneratedValue
    private int id ;

    private String finduser ;

    private String friend ;

    public Relation() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFinduser() {
        return finduser;
    }

    public void setFinduser(String finduser) {
        this.finduser = finduser;
    }

    public String getFriend(String finduser) {
        return friend;
    }

    public void setFriend(String friend) {
        this.friend = friend;
    }
}
