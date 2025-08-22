package com.example.sitpassbek.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@Table(name = "users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name ="role",discriminatorType = DiscriminatorType.STRING)
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String surname;

    private LocalDate createdAt;

    private LocalDate birthday;

    private String phoneNumber;

    private String address;

    private String city;

    private String zipCode;

    private String email;

    private String password;

    private boolean isDeleted;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this instanceof Administrator) {
            return List.of(new SimpleGrantedAuthority("Admin"));
        }
        return List.of(new SimpleGrantedAuthority("User"));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}