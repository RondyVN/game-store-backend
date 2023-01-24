package com.api.gameshop.controller;

import com.api.gameshop.DTO.GameDTO;
import com.api.gameshop.entity.GameEntity;
import com.api.gameshop.model.GameModel;
import com.api.gameshop.service.GameService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/game")
public class GameController {
    @Autowired
    private GameService gameService;

    @PostMapping(
        consumes = {
            MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE
        }
    )
    public ResponseEntity<GameEntity> addGame(
        @ModelAttribute GameDTO GameDTO,
        @RequestParam(value = "file", required = false) MultipartFile file) throws Exception {
        return new ResponseEntity<>(gameService.addGame(GameDTO), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<GameModel>> getGames(
        @RequestParam(required = false) Integer count,
        @RequestParam(defaultValue = "1", required = false) Integer page
    ) {
        List<GameModel> games = gameService.getAllGames(count, page);
        if (games.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(games, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<GameModel> getGameById(@PathVariable Long id) {
        return new ResponseEntity<>(gameService.getGameById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GameEntity> deleteGameById(@PathVariable Long id) {
        return gameService.deleteGameById(id);
    }

    @PutMapping("/{gameId}/genre/{genreId}")
    public ResponseEntity<GameEntity> addGenreToGame(@PathVariable Long gameId, @PathVariable Long genreId) {
        return gameService.addGenre(gameId, genreId);
    }

}
