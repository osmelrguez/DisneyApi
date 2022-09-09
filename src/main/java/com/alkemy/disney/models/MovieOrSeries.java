package com.alkemy.disney.models;

import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.File;
import java.io.Serializable;
import java.util.*;

@Getter @Setter
@NoArgsConstructor
@Entity
@Table(name = "movies_or_series")
public class MovieOrSeries implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ms_id", nullable = false, unique = true)
    private long msId;

    @Column(name = "msImg")
    private File msImg;

    @NotBlank(message = "Title is Required")
    @Column(name = "msTitle")
    private String msTitle;

    @Column(name = "msDate")
    private Date msDate;

    @Size(min = 1, max = 5)
    @Column(name = "classification")
    private int classification;

    @ManyToOne
    @JoinColumn(name = "genId")
    @JsonIgnoreProperties({"genId", "genImg" , "associatedMovies"})
    private Genre genre;

    @ManyToMany(mappedBy = "associatedMovies")
    @JsonIgnoreProperties("associatedMovies")
    private List<CharacterModel> associatedCharacters;

    public void addAssociateCharacter(CharacterModel characterModel){
        this.associatedCharacters.add(characterModel);
    }

    public void deleteAssociatedCharacter(CharacterModel characterModel){
        associatedCharacters.remove(characterModel);
        characterModel.getAssociatedMovies().remove(this);
    }
}
