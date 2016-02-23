package com.epam.restaurant.entity;

import com.epam.restaurant.dao.Identified;

import java.io.Serializable;

/**
 * Entity is user in online restaurant.
 */
public class User implements Serializable, Identified<Long>{
    public static final long serialVersionUID = 1;

    private long id;
    private String name;
    private String surname;
    private String email;
    private String payCard;
    private String login;
    private String hash;
    private Role role;

    public enum Role{
        USER,ADMIN,BLOCKED
    }

    public User(){}

    public User(String login, String hash, String email) {
        this.login = login;
        this.hash = hash;
        this.email = email;
        this.role = Role.USER;
    }


    /**
     * Constructor for registration.
     */
    public User(String name, String surname, String email,
                String payCard, String login, String hash) {
        this.name=name;
        this.surname=surname;
        this.email=email;
        this.payCard=payCard;
        this.login=login;
        this.hash=hash;
        this.role=Role.USER;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPayCard() {
        return payCard;
    }

    public void setPayCard(String payCard) {
        this.payCard = payCard;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", payCardId='" + payCard + '\'' +
                ", login='" + login + '\'' +
                ", hash='" + hash + '\'' +
                ", role=" + role +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }

        User user = (User) o;

        if (id != user.id){
            return false;
        }
        if (name != null ? !name.equals(user.name) : user.name != null){
            return false;
        }
        if (surname != null ? !surname.equals(user.surname) : user.surname != null){
            return false;
        }
        if (email != null ? !email.equals(user.email) : user.email != null){
            return false;
        }
        if (payCard != null ? !payCard.equals(user.payCard) : user.payCard != null){
            return false;
        }
        if (login != null ? !login.equals(user.login) : user.login != null){
            return false;
        }
        if (hash != null ? !hash.equals(user.hash) : user.hash != null){
            return false;
        }
        return role == user.role;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (payCard != null ? payCard.hashCode() : 0);
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (hash != null ? hash.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        return result;
    }
}
