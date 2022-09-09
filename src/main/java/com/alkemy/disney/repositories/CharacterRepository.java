package com.alkemy.disney.repositories;

import com.alkemy.disney.models.CharacterModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface CharacterRepository extends JpaRepository<CharacterModel, Long> {

    CharacterModel findByChId(long id);
    ArrayList<CharacterModel> findByChName(String name);
    ArrayList<CharacterModel> findByChAge(int age);
    ArrayList<CharacterModel> findByAssociatedMovies(long id);
}
