package com.app.pg_accomodation.dto;
import com.app.pg_accomodation.entity.PgDetails;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PgDetailsDto {
    private Long pgId;
    private String address;
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

    public PgDetailsDto(PgDetails details) {
        this.pgId = details.getPg().getId();
        this.address = details.getAddress();
        this.singleOccupancy = details.isSingleOccupancy();
        this.doubleOccupancy = details.isDoubleOccupancy();
        this.tripleOccupancy = details.isTripleOccupancy();
        this.singleCapacity = details.getSingleCapacity();
        this.doubleCapacity = details.getDoubleCapacity();
        this.tripleCapacity = details.getTripleCapacity();
        this.singlePrice = details.getSinglePrice();
        this.doublePrice = details.getDoublePrice();
        this.triplePrice = details.getTriplePrice();
        this.singleImage = details.getSingleImage();
        this.doubleImage = details.getDoubleImage();
        this.tripleImage = details.getTripleImage();
    }
}

