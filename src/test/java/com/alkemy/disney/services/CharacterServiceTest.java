package com.alkemy.disney.services;

import com.alkemy.disney.models.CharacterModel;
import com.alkemy.disney.repositories.CharacterRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CharacterServiceTest {

    @Mock
    private CharacterRepository characterRepository;
    @InjectMocks
    private CharacterService characterService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getCharacterByIdTest(){
        CharacterModel mockCharacter = new CharacterModel();
        mockCharacter.setChId(1);
        mockCharacter.setChName("character1");
        mockCharacter.setChAge(25);
        mockCharacter.setChWeight(70.0);
        mockCharacter.setChHistory("History");

        Mockito.when(characterRepository.save(Mockito.any(CharacterModel.class))).thenReturn(mockCharacter);
    }
}
