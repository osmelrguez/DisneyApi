package com.alkemy.disney.services;

import com.alkemy.disney.models.CharacterModel;
import com.alkemy.disney.models.MovieOrSeries;
import com.alkemy.disney.repositories.CharacterRepository;
import com.alkemy.disney.repositories.MovieOrSeriesRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieOrSeriesService {

    @Autowired
    MovieOrSeriesRepository movieOrSeriesRepository;
    @Autowired
    CharacterRepository characterRepository;

    public List<MovieOrSeries> getMovieList(){
        return movieOrSeriesRepository.findAll();
    }

    public List<MovieOrSeries> findByTitle(String title){
        return movieOrSeriesRepository.findByMsTitle(title);
    }

    public List<MovieOrSeries> getMovieById(long id){
        return movieOrSeriesRepository.findById(id).stream().toList();
    }

    public MovieOrSeries findByMsId(long id){
        return movieOrSeriesRepository.findByMsId(id);
    }

    public MovieOrSeries createMovie(MovieOrSeries movie){
        return movieOrSeriesRepository.save(movie);
    }

    public MovieOrSeries updateMovie(@NotNull MovieOrSeries movieOrSeries) {
        MovieOrSeries movie = new MovieOrSeries();

        movie.setMsTitle(movieOrSeries.getMsTitle());
        movie.setClassification(movieOrSeries.getClassification());
        movie.setMsDate(movieOrSeries.getMsDate());
        movie.setMsImg(movieOrSeries.getMsImg());

        movieOrSeriesRepository.save(movie);
        return movie;
    }

    public MovieOrSeries associateCharacter(long msId, long chId){
        CharacterModel charModel = characterRepository.findByChId(chId);
        MovieOrSeries movie = movieOrSeriesRepository.findByMsId(msId);

        movie.addAssociateCharacter(charModel);
        movieOrSeriesRepository.save(movie);
        return movie;
    }

    public MovieOrSeries deleteAssociatedCharacter(long msId, long chId){
        CharacterModel charModel = characterRepository.findByChId(chId);
        MovieOrSeries movie = movieOrSeriesRepository.findByMsId(msId);

        movie.deleteAssociatedCharacter(charModel);
        movieOrSeriesRepository.save(movie);
        return movie;
    }
    public boolean deleteMovie(long id){
        movieOrSeriesRepository.findById(id).map(movieOrSeries -> {
            movieOrSeriesRepository.deleteById(id);
            return true;
        });
        return false;
    }
}
