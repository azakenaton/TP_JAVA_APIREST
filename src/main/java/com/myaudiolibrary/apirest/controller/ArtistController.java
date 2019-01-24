package com.myaudiolibrary.apirest.controller;

import com.myaudiolibrary.apirest.exception.ArtistAlreadyExistException;
import com.myaudiolibrary.apirest.model.Artist;
import com.myaudiolibrary.apirest.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/artists")
public class ArtistController {

    @Autowired
    private ArtistRepository artistRepository;

    @RequestMapping("/{id_a}")
    public Artist findBbyId(@PathVariable("id_a") Integer id_a) throws EntityNotFoundException{
        Artist artist = artistRepository.findOne(id_a);
        if(artist == null){
            throw new EntityNotFoundException("L'artist avec l'id "+id_a+" n'existe pas.");
        }
        return artist;
    }

    @RequestMapping(params = {"name","page","size","sortProperty","sortDirection"})
    public Page<Artist> findByNom (@RequestParam("name") String nom,
                                   @RequestParam("page") Integer page,
                                   @RequestParam("size") Integer size,
                                   @RequestParam("sortProperty") String sortP,
                                   @RequestParam("sortDirection") Sort.Direction sortD){
        PageRequest pageRequest = new PageRequest(page, size, sortD, sortP);
        Page<Artist> artists = artistRepository.findByNameContainingIgnoreCase(nom, pageRequest);
        return artists;
    }

    @RequestMapping(params = {"page","size","sortProperty","sortDirection"})
    public Page<Artist> findAll(@RequestParam("page") Integer page,
                                @RequestParam("size") Integer size,
                                @RequestParam("sortProperty") String sortP,
                                @RequestParam("sortDirection") Sort.Direction sortD){
        PageRequest pageRequest = new PageRequest(page, size, sortD, sortP);
        return artistRepository.findAll(pageRequest);
    }

    @RequestMapping(method = RequestMethod.POST,
            consumes = "application/json",
            produces = "application/json")
    public Artist newArtist(
            @RequestBody Artist artist) throws ArtistAlreadyExistException {
        if(artistRepository.findByName(artist.getName()) != null){
            throw new ArtistAlreadyExistException("L'artist existe déjà.");
        }
        artistRepository.save(artist);
        return artist;
    }

    @RequestMapping(method = RequestMethod.PUT,
            consumes = "application/json",
            produces = "application/json",
            value = "/{id}")
    public Artist modArtist(
            @RequestBody Artist artist){
        artistRepository.save(artist);
        return artist;
    }

    @RequestMapping(method = RequestMethod.DELETE,
            value = "/{id}")
    public Integer delArtist(
            @PathVariable("id") Integer id){
        artistRepository.delete(id);
        return id;
    }
}
