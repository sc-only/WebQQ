package qqweb.login.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PersonalizedSignature {
    @Id
    private String username ;

    private String signature ;

    public PersonalizedSignature() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}
