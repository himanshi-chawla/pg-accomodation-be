package com.app.pg_accomodation.repository;

import com.app.pg_accomodation.entity.PgDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PgDetailsRepository extends JpaRepository<PgDetails, Long> {
    PgDetails findByPgId(Long pgId);
    List<PgDetails> findByLocality(String locality);
}



