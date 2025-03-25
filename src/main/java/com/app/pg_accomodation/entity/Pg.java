package com.app.pg_accomodation.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

//import jakarta.persistence.*;
//import lombok.*;
//not used due to memory constraints therefore import as per requirements

@Entity
@Table(name = "pg")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pg {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //used for auto geneting teh id by sql
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User owner;  // Reference to User entity (Owner of PG)

    private String name;
    private String locality;
    private String coverImage;
}



