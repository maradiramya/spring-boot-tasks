package com.stackroute.trackservice.repository;

import com.stackroute.trackservice.domain.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrackRepository extends JpaRepository<Track, Integer> {
    //is used for defining custom queries in spring data jpa
    //creation of custom query using name
    @Query(value = "select * from Track t where t.name=?", nativeQuery = true)
    public List<Track> findByName(String name);
}
