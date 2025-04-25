package com.app.demo.Tables;

import jakarta.persistence.*;

@Entity
@Table(name = "Servers")
public class ServerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

}
