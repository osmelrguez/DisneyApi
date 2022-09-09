package com.alkemy.disney.controllers;

import com.alkemy.disney.models.Genre;
import com.alkemy.disney.services.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("api/genres")
public class GenreController {

    @Autowired
    GenreService genreService;

    @GetMapping
    public ResponseEntity<List<Genre>> getGenreList(){
        return new ResponseEntity<> (genreService.getGenreList(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Genre> createGenre(@RequestBody Genre genre){
        return new ResponseEntity<Genre> ((Genre) genreService.createGenre(genre), HttpStatus.CREATED);
    }

    @PostMapping(value = "{genId}/movies/{msId}")
    public ResponseEntity<Genre> associateMovie(@PathVariable(value = "genId") long genId, @PathVariable(value = "msId") long msId){
        return new ResponseEntity<>(genreService.associateMovie(genId, msId), HttpStatus.CREATED);
    }
}
