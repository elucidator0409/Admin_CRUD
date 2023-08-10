package com.test.admincrud.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "client")
public class Client implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(columnDefinition = "nvarchar(150) not null")
    private String name;
    @Column(columnDefinition = "nvarchar(150) not null")
    private String email;
    @Column(columnDefinition = "nvarchar(100) not null")
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }


}