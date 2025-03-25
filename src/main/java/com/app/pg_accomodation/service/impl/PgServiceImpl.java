package com.app.pg_accomodation.service.impl;


import com.app.pg_accomodation.dto.PgDto;
import com.app.pg_accomodation.entity.Pg;
import com.app.pg_accomodation.entity.User;
import com.app.pg_accomodation.repository.PgRepository;
import com.app.pg_accomodation.repository.UserRepository;
import com.app.pg_accomodation.service.PgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PgServiceImpl implements PgService {

    @Autowired
    private PgRepository pgRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public PgDto addPg(Long ownerId, PgDto pgDto) {
        User owner = userRepository.findById(ownerId)
                .orElseThrow(() -> new RuntimeException("Owner not found"));

        Pg pg = new Pg();
        pg.setOwner(owner);
        pg.setName(pgDto.getName());
        pg.setLocality(pgDto.getLocality());
        pg.setCoverImage(pgDto.getCoverImage());

        Pg savedPg = pgRepository.save(pg);
        return new PgDto(savedPg);
    }

    @Override
    public PgDto updatePg(Long ownerId, Long pgId, PgDto pgDto) {
        Pg pg = pgRepository.findById(pgId)
                .orElseThrow(() -> new RuntimeException("PG not found"));

        if (!pg.getOwner().getId().equals(ownerId)) {
            throw new RuntimeException("Unauthorized to update this PG");
        }

        pg.setName(pgDto.getName());
        pg.setLocality(pgDto.getLocality());
        pg.setCoverImage(pgDto.getCoverImage());

        Pg updatedPg = pgRepository.save(pg);
        return new PgDto(updatedPg);
    }

    @Override
    public void deletePg(Long ownerId, Long pgId) {
        Pg pg = pgRepository.findById(pgId)
                .orElseThrow(() -> new RuntimeException("PG not found"));

        if (!pg.getOwner().getId().equals(ownerId)) {
            throw new RuntimeException("Unauthorized to delete this PG");
        }

        pgRepository.delete(pg);
    }

    @Override
    public List<PgDto> getPgsByOwner(Long ownerId) {
        List<Pg> pgs = pgRepository.findByOwnerId(ownerId);
        return pgs.stream().map(PgDto::new).collect(Collectors.toList());
    }

    @Override
    public List<PgDto> getPgsByLocality(String locality) {
        List<Pg> pgs = pgRepository.findByLocality(locality);
        return pgs.stream().map(PgDto::new).collect(Collectors.toList());
    }

    @Override
    public List<PgDto> getAllPgs() {
        List<Pg> pgs = pgRepository.findAll();
        return pgs.stream().map(PgDto::new).collect(Collectors.toList());
    }
}


//        return pgs.stream().map(PgDto::new).collect(Collectors.toList());


//    @Override
//    public List<PgDto> getOwnerPgs(Long ownerId) {
//        List<Pg> pgs = pgRepository.findByOwnerId(ownerId);
//        return pgs.stream().map(PgDto::new).collect(Collectors.toList());
//    }
//    @Override
//    public List<Pg> getAllPgs() {
//        return pgRepository.findAll();  // Fetch all PG listings
//    }




