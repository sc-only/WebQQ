package qqweb.login.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Relation {

    private String finduser ;

    private String friend ;

    private int zan ;
    @Id
    private String rela;

    public Relation() {
    }

    public String getRela() {
        return rela;
    }

    public void setRela(String rela) {
        this.rela = rela;
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
