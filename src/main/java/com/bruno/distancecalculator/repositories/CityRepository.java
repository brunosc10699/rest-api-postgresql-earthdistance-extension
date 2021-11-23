package com.bruno.distancecalculator.repositories;

import com.bruno.distancecalculator.entities.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<CityEntity, Long> {

    @Query(value = "SELECT ((SELECT lat_lon FROM geo WHERE id = ?1) <@> (SELECT lat_lon FROM geo WHERE id = ?2))", nativeQuery = true)
    Double getDistance(@Param("id1") final Long from, @Param("id2") final Long to);
}
