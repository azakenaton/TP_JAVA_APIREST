package com.myaudiolibrary.apirest.repository;

import com.myaudiolibrary.apirest.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AlbumRepository extends JpaRepository<Album, Integer> {

}
