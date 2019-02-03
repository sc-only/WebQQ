package qqweb.login.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {
    @Id
    private String username;
    @JsonIgnore
    private String password ;
    @JsonIgnore
    private String firstpinyin ;
    @JsonIgnore
    private String pinyin ;
    @JsonIgnore
    private int enter ;

    public int getEnter() {
        return enter;
    }

    public void setEnter(int enter) {
        this.enter = enter;
    }

    public User() {
    }

    public String getFirstpinyin() {
        return firstpinyin;
    }

    public void setFirstpinyin(String firstpinyin) {
        this.firstpinyin = firstpinyin;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
