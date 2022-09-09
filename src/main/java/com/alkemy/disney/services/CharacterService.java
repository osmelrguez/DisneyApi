package com.alkemy.disney.services;

import com.alkemy.disney.exceptions.BadRequestException;
import com.alkemy.disney.exceptions.NotFoundException;
import com.alkemy.disney.models.CharacterModel;
import com.alkemy.disney.models.MovieOrSeries;
import com.alkemy.disney.repositories.CharacterRepository;
import com.alkemy.disney.repositories.MovieOrSeriesRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CharacterService {

    @Autowired
    CharacterRepository characterRepository;

    @Autowired
    MovieOrSeriesRepository movieOrSeriesRepository;

    public List<CharacterModel> getCharacterList(){
        return characterRepository.findAll();
    }

    public Optional<CharacterModel> getCharacterById(long id){
        if (!characterRepository.existsById(id)) {
            throw new NotFoundException("Character not found");
        }
        return characterRepository.findById(id);
    }

    public List<CharacterModel> findByName(String name){

        List<CharacterModel> list = characterRepository.findByChName(name);
        if (list.isEmpty()) {
            throw new NotFoundException("Name not found");
        }
        return list;
    }

    public List<CharacterModel> findByAge(int age){
        List<CharacterModel> list = characterRepository.findByChAge(age);
        if (list.isEmpty()) {
            throw new NotFoundException("Age not found");
        }
        return list;
    }

    public CharacterModel createCharacter(@NotNull CharacterModel character){
        if (character.getChName().isEmpty()) {
            throw new BadRequestException("Name is required");
        }
        return characterRepository.save(character);
    }

    public CharacterModel updateCharacter(@NotNull CharacterModel characterModel){
        CharacterModel characterMod = new CharacterModel();

        characterMod.setChName(characterModel.getChName());
        characterMod.setChImg(characterModel.getChImg());
        characterMod.setChAge(characterModel.getChAge());
        characterMod.setChHistory(characterModel.getChHistory());
        characterMod.setChWeight(characterModel.getChWeight());

        characterRepository.save(characterMod);
        return characterMod;
    }
    public CharacterModel associateMovie(long chId, long msId){
        if (!characterRepository.existsById(chId)) {
            throw new NotFoundException("Character not found");
        }
        if (!movieOrSeriesRepository.existsById(msId)) {
            throw new NotFoundException("Movie not found");
        }
        MovieOrSeries movie = movieOrSeriesRepository.findByMsId(msId);
        CharacterModel charModel = characterRepository.findByChId(chId);

        charModel.addAssociatedMovie(movie);
        characterRepository.save(charModel);
        return charModel;
    }
    public boolean deleteCharacter(long id){
        if (!characterRepository.existsById(id)) {
            throw new NotFoundException("Character not found");
        }
        characterRepository.findById(id).map(characterModel -> {
            characterRepository.deleteById(id);
            return true;
        });
        return false;
    }
}
