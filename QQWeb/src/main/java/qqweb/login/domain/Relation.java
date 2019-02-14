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

    private int zan ;

    public Relation() {
    }

    public String getFinduser() {
        return finduser;
    }

    public void setFinduser(String finduser) {
        this.finduser = finduser;
    }

    public String getFriend() {
        return friend;
    }

    public void setFriend(String friend) {
        this.friend = friend;
    }

    public int getZan() {
        return zan;
    }

    public void setZan(int zan) {
        this.zan = zan;
    }
}
