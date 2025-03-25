package com.app.pg_accomodation.dto;

import com.app.pg_accomodation.entity.Pg;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PgDto {
    private Long id;
    private String name;
    private String locality;
    private String coverImage;
    private Long ownerId; // To track the owner of the PG

    public PgDto(Pg pg) {
        this.id = pg.getId();
        this.name = pg.getName();
        this.locality = pg.getLocality();
        this.coverImage = pg.getCoverImage();
        this.ownerId = pg.getOwner().getId();
    }
}
