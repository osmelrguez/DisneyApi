package com.alkemy.disney.models;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

import javax.persistence.*;
import java.io.File;
import java.io.Serializable;
import java.util.*;

@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "characters")
public class CharacterModel implements Serializable  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ch_id", unique = true, nullable = false)
    private long chId;
    @Column(name = "img")
    private File chImg;
    @Column(name = "name")
    private String chName;
    @Column(name = "age")
    private int chAge;
    @Column(name = "weight")
    private double chWeight;
    @Column(name = "history")
    private String chHistory;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "char_movie_or_series", joinColumns = {@JoinColumn(name = "ch_id")},
                                            inverseJoinColumns = {@JoinColumn(name = "ms_id")}
    )
    @JsonIgnoreProperties("associatedCharacters")
    private List<MovieOrSeries> associatedMovies;

    public void addAssociatedMovie(MovieOrSeries movieOrSeries){
        this.associatedMovies.add(movieOrSeries);
    }


}
