package com.example.demo.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name ="person")
public class Person {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name="id")
    private int id ;
    @NotNull
    @Column(name= "username")
    private String username ;
    @Column(name="password")
    @NotNull
    private String password  ;
    @Column(name = "user_role")
    private String user_role ;

    @Column(name="confines")
    private Integer confines;
    @OneToMany(mappedBy = "person")
    private List<Operations> operations;
    public Person(String username, String password, String user_role) {
        this.username = username;
        this.password = password;
        this.user_role = user_role;
    }

    public Person() {
    }
    public boolean isAdmin(){
        if(Objects.equals(this.getUser_role(), "ROLE_ADMIN")){
            return true;
        }
        return false;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getConfines() {
        return confines;
    }

    public void setConfines(Integer confines) {
        this.confines = confines;
    }

    public List<Operations> getOperations() {
        return operations;
    }

    public void setOperations(List<Operations> operations) {
        this.operations = operations;
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

    public String getUser_role() {
        return user_role;
    }

    public void setUser_role(String user_role) {
        this.user_role = user_role;
    }
}
