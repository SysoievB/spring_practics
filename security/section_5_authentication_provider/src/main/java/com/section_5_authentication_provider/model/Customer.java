package com.section_5_authentication_provider.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @NotBlank
    String email;
    @NotBlank
    @Column(name = "pwd")
    String password;
    @NotBlank
    @Column(name = "role")
    String role;
    @NotNull
    LocalDate birthday;

    public Customer() {
    }

    public Customer(Long id, String email, String password, String role, LocalDate birthday) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.role = role;
        this.birthday = birthday;
    }

    public Customer(String email, String password, String role, LocalDate birthday) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.birthday = birthday;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id) && Objects.equals(email, customer.email) && Objects.equals(password, customer.password) && Objects.equals(role, customer.role) && Objects.equals(birthday, customer.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, password, role, birthday);
    }
}
