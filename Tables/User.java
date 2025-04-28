package com.app.demo.Tables;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @JsonProperty("username")
    private String userName;
    @JsonProperty("password")
    private String password;
    @JsonProperty("email")
    private String email;

    private String pfpName;

    @Column(
            length = 2000
    )
    @JsonProperty("notes")
    private String notes;

    public User(){

    }

    public User(String userName, String password, String email, String notes, String pfpName) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.notes = notes;
        this.pfpName = pfpName;
    }

    public Integer getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getPfpName() {
        return pfpName;
    }

    public void setPfpName(String pfpName) {
        this.pfpName = pfpName;
    }
}
