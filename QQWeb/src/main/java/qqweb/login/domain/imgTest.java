package qqweb.login.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "img")
public class imgTest {

    @Id
    private String username ;
    
    private String url ;

    public imgTest() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
