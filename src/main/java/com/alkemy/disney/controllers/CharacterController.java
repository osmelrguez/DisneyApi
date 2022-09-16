package com.alkemy.disney.controllers;

import com.alkemy.disney.models.CharacterModel;
import com.alkemy.disney.models.MovieOrSeries;
import com.alkemy.disney.services.CharacterService;
import com.alkemy.disney.services.MovieOrSeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/characters")
public class CharacterController {

    @Autowired
    CharacterService characterService;
    @Autowired
    MovieOrSeriesService movieOrSeriesService;

    @GetMapping
    public ResponseEntity<List<CharacterModel>> getCharacterList(){
        return new ResponseEntity<>(characterService.getCharacterList(), HttpStatus.OK);
    }

    @GetMapping(value = "{id}")
    public Optional<CharacterModel> getById(@PathVariable long id){
        return characterService.getCharacterById(id);

    }

    @GetMapping(value = "find")
    @ResponseStatus(HttpStatus.OK)
    public List<String> findByName(@RequestParam("name") String name){
        return characterService.findByName(name).stream()
                                                .map(characterModel -> characterModel.getChImg()
                                                +" "+characterModel.getChName()).toList();
    }

    @GetMapping(value = "find1")
    @ResponseStatus(HttpStatus.OK)
    public List<String> findByAge(@RequestParam("age") int age){
        return characterService.findByAge(age).stream().map(characterModel -> characterModel.getChImg()
                                                +" "+ characterModel.getChName()).toList();
    }

    @GetMapping(value = "find2")
    @ResponseStatus(HttpStatus.OK)
    public List<String> findByMovieId(@RequestParam("id") long id){
        MovieOrSeries movie = movieOrSeriesService.findByMsId(id);
        return movie.getAssociatedCharacters().stream()
                                              .map(characterModel -> characterModel.getChImg()
                                               +" "+ characterModel.getChName()).toList();
    }

    @PostMapping
    public ResponseEntity<CharacterModel> createCharacter(@RequestBody CharacterModel characterMod){
        return new ResponseEntity<> (characterService.createCharacter(characterMod), HttpStatus.CREATED);
    }

    @PostMapping(value = "{chId}/movies/{msId}")
    public ResponseEntity<CharacterModel> associateMovie(@PathVariable(value = "chId") long chId, @PathVariable(value = "msId") long msId){
        return new ResponseEntity<>(characterService.associateMovie(chId, msId), HttpStatus.CREATED);
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<CharacterModel> updateCharacter(@PathVariable long id, @RequestBody CharacterModel characterModel){
        return new ResponseEntity<>(characterService.updateCharacter(id, characterModel), HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<?> deleteCharacter(@PathVariable long id){
        characterService.deleteCharacter(id);
        return ResponseEntity.status(HttpStatus.OK).body("Character deleted successfully");
    }
}
