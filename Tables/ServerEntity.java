package com.app.demo.Tables;

import jakarta.persistence.*;

@Entity
@Table(name = "Servers")
public class ServerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String ownerName;


    public ServerEntity(){

    }

    public ServerEntity(String name, String ownerName) {
        this.name = name;
        this.ownerName = ownerName;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
