package com.api.gameshop.DTO;

import java.util.Date;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;
import org.springframework.format.annotation.DateTimeFormat;

@Jacksonized
@RequiredArgsConstructor
@Getter
@Setter
public class GameDTO {
    String name;
    String description;
    Double price;
    String  releaseDate;
    String imageUrl;

}
