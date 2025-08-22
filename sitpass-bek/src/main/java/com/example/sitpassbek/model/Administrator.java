package com.example.sitpassbek.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@DiscriminatorValue("Admin")
public class Administrator extends User {

}