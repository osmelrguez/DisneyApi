package com.alkemy.disney.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.File;
import java.io.Serializable;
import java.util.List;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "genres")
public class Genre implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "genId", nullable = false, unique = true)
    private long genId;
    @Column(name = "genName")
    private String genName;
    @Column(name = "genImg")
    private File genImg;

    @OneToMany(mappedBy = "genre")
    @JsonIgnoreProperties({"associatedCharacters", "genre"})
    private List<MovieOrSeries> associatedMovies;

    public void associateMovie(MovieOrSeries movieOrSeries){
        this.associatedMovies.add(movieOrSeries);
    }
}
