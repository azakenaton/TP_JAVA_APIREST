package com.myaudiolibrary.apirest.repository;

import com.myaudiolibrary.apirest.model.Artist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArtistRepository extends JpaRepository<Artist, Integer> {

    //Artist findById(int id);
    Page<Artist> findByNameContainingIgnoreCase(String name, Pageable page);
    Artist findByName(String name);
}
