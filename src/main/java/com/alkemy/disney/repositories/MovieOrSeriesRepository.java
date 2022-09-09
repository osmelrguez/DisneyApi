package com.alkemy.disney.repositories;

import com.alkemy.disney.models.MovieOrSeries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieOrSeriesRepository extends JpaRepository<MovieOrSeries, Long> {

    MovieOrSeries findByMsId(long id);
    List<MovieOrSeries> findByMsTitle(String title);
}
