package com.api.gameshop.model;

import com.api.gameshop.entity.GenreEntity;
import java.util.Date;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Builder
@Getter
@Setter
public class GameModel {
    Long id;
    String name;
    String description;
    Double price;
    String imageUrl;

    String releaseDate;
    List<GenreEntity> genres;
    Integer quantity;
}
