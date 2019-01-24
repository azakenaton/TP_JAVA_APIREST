package com.myaudiolibrary.apirest.controller;

import com.myaudiolibrary.apirest.exception.ArtistAlreadyExistException;
import com.myaudiolibrary.apirest.model.Album;
import com.myaudiolibrary.apirest.model.Artist;
import com.myaudiolibrary.apirest.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/albums")
public class AlbumController {

    @Autowired
    private AlbumRepository albumRepository;

    @RequestMapping(method = RequestMethod.POST,
            consumes = "application/json",
            produces = "application/json")
    public Album newAlbum(
            @RequestBody Album album){
        albumRepository.save(album);
        return album;
    }

    @RequestMapping(method = RequestMethod.DELETE,
            value = "/{id}")
    public Integer delAlbum(
            @PathVariable("id") Integer id){
        albumRepository.delete(id);
        return id;
    }
}
