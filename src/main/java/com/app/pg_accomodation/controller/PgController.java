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

    @PreAuthorize("hasRole('ROLE_OWNER') or hasRole('ROLE_ADMIN')")
    @PostMapping("/add/{ownerId}")
    public PgDto addPg(@PathVariable Long ownerId, @RequestBody PgDto pgDto) {
        return pgService.addPg(ownerId, pgDto);
    }

    @PreAuthorize("hasRole('ROLE_OWNER') or hasRole('ROLE_ADMIN')")
    @PutMapping("/update/{ownerId}/{pgId}")
    public PgDto updatePg(@PathVariable Long ownerId, @PathVariable Long pgId, @RequestBody PgDto pgDto) {
        return pgService.updatePg(ownerId, pgId, pgDto);
    }
     
    @PreAuthorize("hasRole('ROLE_OWNER') or hasRole('ROLE_ADMIN')")
    @DeleteMapping("/delete/{ownerId}/{pgId}")
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
