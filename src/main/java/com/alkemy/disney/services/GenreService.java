package com.alkemy.disney.services;

import com.alkemy.disney.models.CharacterModel;
import com.alkemy.disney.models.Genre;
import com.alkemy.disney.models.MovieOrSeries;
import com.alkemy.disney.repositories.GenreRepository;
import com.alkemy.disney.repositories.MovieOrSeriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreService {

    @Autowired
    GenreRepository genreRepository;
    @Autowired
    MovieOrSeriesRepository movieOrSeriesRepository;

    public List<Genre> getGenreList(){
        return genreRepository.findAll();
    }
    public Object createGenre(Genre genre){
        return genreRepository.save(genre);
    }

    public Genre associateMovie(long genId, long msId){
        MovieOrSeries movie = movieOrSeriesRepository.findByMsId(msId);
        Genre genre = genreRepository.findByGenId(genId);

        genre.associateMovie(movie);
        genreRepository.save(genre);
        return genre;
    }
}
