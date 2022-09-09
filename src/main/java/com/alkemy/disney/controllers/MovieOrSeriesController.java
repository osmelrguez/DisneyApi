package com.alkemy.disney.controllers;

import com.alkemy.disney.models.MovieOrSeries;
import com.alkemy.disney.services.CharacterService;
import com.alkemy.disney.services.MovieOrSeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/movies")
public class MovieOrSeriesController {

    @Autowired
    MovieOrSeriesService movieOrSeriesService;

    @GetMapping
    public ResponseEntity<List<MovieOrSeries>> getMoviesList(){
        return new ResponseEntity<>(movieOrSeriesService.getMovieList(), HttpStatus.OK);
    }

    @GetMapping(value = "find")
    public List<String> findByTitle(@RequestParam("title") String title){
        return movieOrSeriesService.findByTitle(title).stream().map(movieOrSeries -> movieOrSeries.getMsImg()
                                                                   +" "+ movieOrSeries.getMsTitle()).toList();
    }

    @GetMapping(value = "find1")
    public List<String> findByGenderId(@RequestParam("genre") long genId){
        return movieOrSeriesService.getMovieList().stream()
                                                  .filter(movieOrSeries -> movieOrSeries.getGenre().getGenId() == genId)
                                                  .map(movieOrSeries -> movieOrSeries.getMsImg()
                                                  +" "+ movieOrSeries.getMsTitle()).toList();
    }

    @GetMapping(value = "{id}")
    public List<MovieOrSeries> getById(@PathVariable long id){
        return movieOrSeriesService.getMovieById(id);

    }

    @PostMapping(value = "{msId}/characters/{chId}")
    public ResponseEntity<MovieOrSeries> associateCharacter(@PathVariable(value = "msId") long msId, @PathVariable(value = "chId") long chId){
        return new ResponseEntity<>(movieOrSeriesService.associateCharacter(msId, chId), HttpStatus.CREATED);
    }

    @PostMapping
    public ResponseEntity<MovieOrSeries> createMovie(@RequestBody MovieOrSeries movie){
        return new ResponseEntity<> (movieOrSeriesService.createMovie(movie), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<MovieOrSeries> updateMovie(@RequestBody MovieOrSeries movieOrSeries){
        return new ResponseEntity<>(movieOrSeriesService.updateMovie(movieOrSeries), HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<?> deleteMovie(@PathVariable long id){
        movieOrSeriesService.deleteMovie(id);
        return ResponseEntity.status(HttpStatus.OK).body("Movie deleted successfully");
    }

    @DeleteMapping(value = "{msId}/characters/{chId}")
    public ResponseEntity<MovieOrSeries> deleteAssociateCharacter(@PathVariable(value = "msId") long msId, @PathVariable(value = "chId") long chId){
        return new ResponseEntity<>(movieOrSeriesService.deleteAssociatedCharacter(msId, chId), HttpStatus.OK);
    }
}
