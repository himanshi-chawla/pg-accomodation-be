package com.app.pg_accomodation.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
//import jakarta.persistence.*;
//import lombok.*;

@Entity
@Table(name = "pg_details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PgDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "pg_id", nullable = false)
    private Pg pg;  // Reference to PG

    @Column(nullable = false)
    private String address;

    private String locality;

    private boolean singleOccupancy;
    private boolean doubleOccupancy;
    private boolean tripleOccupancy;

    private int singleCapacity;
    private int doubleCapacity;
    private int tripleCapacity;

    private double singlePrice;
    private double doublePrice;
    private double triplePrice;

    private String singleImage;
    private String doubleImage;
    private String tripleImage;
}

