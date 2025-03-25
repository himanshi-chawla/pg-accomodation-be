package com.app.pg_accomodation.repository;


import com.app.pg_accomodation.entity.Pg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PgRepository extends JpaRepository<Pg, Long> {
    List<Pg> findByOwnerId(Long ownerId);

//    @Query(value = "")//used by owner to get PGs by Owner ID
    List<Pg> findByLocality(String locality);     // used by user to seacrh PGs


}





