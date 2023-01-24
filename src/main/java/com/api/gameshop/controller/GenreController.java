package com.api.gameshop.controller;
import com.api.gameshop.entity.GenreEntity;
import com.api.gameshop.service.GenreService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/genre")
public class GenreController {
    @Autowired
    private GenreService genreService;

    @PostMapping
    public void addGenre(@RequestBody GenreEntity genre) throws Exception {
        genreService.addGenre(genre);
    }

    @GetMapping
    public ResponseEntity<List<GenreEntity>> getAllGenre() throws Exception {
        return genreService.getAllGenres();
    }

}
