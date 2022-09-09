package com.alkemy.disney.repositories;

import com.alkemy.disney.models.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {

    Genre findByGenId(long id);
}
