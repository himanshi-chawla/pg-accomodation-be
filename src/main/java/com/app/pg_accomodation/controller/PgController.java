package com.app.pg_accomodation.controller;

import com.app.pg_accomodation.dto.PgDto;
import com.app.pg_accomodation.service.PgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pg")
public class PgController {

    @Autowired
    private PgService pgService;

    @PostMapping("/add/{ownerId}")
    @PreAuthorize("hasRole('OWNER') or hasRole('ADMIN')")
    public PgDto addPg(@PathVariable Long ownerId, @RequestBody PgDto pgDto) {
        return pgService.addPg(ownerId, pgDto);
    }

    @PutMapping("/update/{ownerId}/{pgId}")
    @PreAuthorize("hasRole('OWNER') or hasRole('ADMIN')")
    public PgDto updatePg(@PathVariable Long ownerId, @PathVariable Long pgId, @RequestBody PgDto pgDto) {
        return pgService.updatePg(ownerId, pgId, pgDto);
    }

    @DeleteMapping("/delete/{ownerId}/{pgId}")
    @PreAuthorize("hasRole('OWNER') or hasRole('ADMIN')")
    public void deletePg(@PathVariable Long ownerId, @PathVariable Long pgId) {
        pgService.deletePg(ownerId, pgId);
    }

    @GetMapping("/all")
    public List<PgDto> getAllPgs() {
        return pgService.getAllPgs();
    }

    @GetMapping("/owner/{ownerId}")
    public List<PgDto> getOwnerPgs(@PathVariable Long ownerId) {
        return pgService.getPgsByOwner(ownerId);
    }

    @GetMapping("/by-locality")
    public List<PgDto> getPgsByLocality(@RequestParam String locality) {
        return pgService.getPgsByLocality(locality);
    }

}
