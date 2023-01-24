package com.api.gameshop.service;

import com.api.gameshop.DTO.GameDTO;
import com.api.gameshop.entity.GameEntity;
import com.api.gameshop.entity.GenreEntity;
import com.api.gameshop.exception.DuplicateGenreInGame;
import com.api.gameshop.exception.NoSuchElementFoundException;
import com.api.gameshop.model.GameModel;
import com.api.gameshop.repository.GamePagination;
import com.api.gameshop.repository.GameRepo;
import com.api.gameshop.repository.GenreRepo;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GameService {

    @Autowired
    private GameRepo gameRepo;
    @Autowired
    private GenreRepo genreRepo;
    @Autowired
    private GamePagination gamePagination;

    public GameEntity addGame(GameDTO GameDTO) throws Exception {
        if (gameRepo.findByName(GameDTO.getName()) != null) {
            System.out.println("ERROR game already exist");
            throw new Exception();
        }
        GameEntity gameEntity = new GameEntity();
        gameEntity.setName(GameDTO.getName());
        gameEntity.setReleaseDate(GameDTO.getReleaseDate());
        gameEntity.setPrice(GameDTO.getPrice());
        gameEntity.setDescription(GameDTO.getDescription());
        gameEntity.setImageUrl(GameDTO.getImageUrl());
        return gameRepo.save(gameEntity);
    }

    public List<GameModel> getAllGames(Integer count, Integer page) {
        List<GameEntity> gameList = new ArrayList<>();
        if (count == null) {
            gameRepo.findAll().forEach(gameList::add);
        } else {
            Pageable pageable = PageRequest.of(page - 1, count);
            gamePagination.findAll(pageable).forEach(gameList::add);
        }
        return gameList.stream().map(this::gameToModel).collect(Collectors.toList());
    }

    private GameModel gameToModel(GameEntity gameEntity) {
        return GameModel.builder()
            .id(gameEntity.getId())
            .description(gameEntity.getDescription())
            .genres(gameEntity.getGenres())
            .name(gameEntity.getName())
            .price(gameEntity.getPrice())
            .releaseDate(gameEntity.getReleaseDate())
            .imageUrl(gameEntity.getImageUrl())
            .quantity(gameEntity.getQuantity())
            .build();
    }

    public GameModel getGameById(Long id) {
        GameEntity gameEntity = gameRepo.findById(id)
            .orElseThrow(() -> new NoSuchElementFoundException("Not found Game with id = " + id));
        return gameToModel(gameEntity);
    }

    public ResponseEntity<GameEntity> deleteGameById(Long id) {
        GameEntity game = gameRepo.findById(id)
            .orElseThrow(() -> new NoSuchElementFoundException("Not found Game with id = " + id));
        gameRepo.delete(game);
        return new ResponseEntity<>(game, HttpStatus.OK);
    }

    public ResponseEntity<GameEntity> addGenre(Long gameId, Long genreId) {
        GameEntity game = gameRepo.findById(gameId)
            .orElseThrow(() -> new NoSuchElementFoundException("Not found Game with id = " + gameId));
        List<GenreEntity> genreList = game.getGenres();
        GenreEntity genre = genreRepo.findById(genreId)
            .orElseThrow(() -> new NoSuchElementFoundException("Not found Game with id = " + genreId));
        if (genreList.contains(genre)) {
            throw new DuplicateGenreInGame("Duplicate genre for game id - " + gameId);
        }
        genreList.add(genre);
        game.setGenres(genreList);
        gameRepo.save(game);
        return new ResponseEntity<>(game, HttpStatus.OK);
    }
}
