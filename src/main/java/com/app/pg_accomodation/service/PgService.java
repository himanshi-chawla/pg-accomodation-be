package com.app.pg_accomodation.service;

import com.app.pg_accomodation.dto.PgDto;
import com.app.pg_accomodation.entity.Pg;

import java.util.List;

public interface PgService {
    PgDto addPg(Long ownerId, PgDto pgDto);
    PgDto updatePg(Long ownerId, Long pgId, PgDto pgDto);
    void deletePg(Long ownerId, Long pgId);

    List<PgDto> getAllPgs();  // Get all PG listings
    List<PgDto> getPgsByOwner(Long ownerId);  // Get PGs owned by a specific owner
    List<PgDto> getPgsByLocality(String locality);

}

