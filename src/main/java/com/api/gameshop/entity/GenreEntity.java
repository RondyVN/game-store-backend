package com.api.gameshop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import lombok.RequiredArgsConstructor;

@Entity
@RequiredArgsConstructor
public class GenreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String genreName;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @ManyToMany(
        fetch = FetchType.LAZY,
        cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
        },
        mappedBy = "genres")
    @JsonIgnore
    private List<GameEntity> gameEntities;


    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    public List<GameEntity> getGameEntities() {
        return gameEntities;
    }

    public void setGameEntities(List<GameEntity> gameEntities) {
        this.gameEntities = gameEntities;
    }
}
