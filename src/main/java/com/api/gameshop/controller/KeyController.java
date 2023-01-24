package com.api.gameshop.controller;

import com.api.gameshop.entity.GameEntity;
import com.api.gameshop.entity.KeyEntity;
import com.api.gameshop.exception.NoSuchElementFoundException;
import com.api.gameshop.repository.GameRepo;
import com.api.gameshop.repository.KeyRepo;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/key")
public class KeyController {
    @Autowired
    private KeyRepo keyRepo;
    @Autowired
    private GameRepo gameRepo;
    @GetMapping("/{id}")
    private ResponseEntity<KeyEntity> getKeyById(@PathVariable Long id) {
        KeyEntity keys = keyRepo.findById(id)
            .orElseThrow(() -> new NoSuchElementFoundException("Not found Tag with id = " + id));
        return new ResponseEntity<>(keys, HttpStatus.OK);
    }
    @GetMapping("/game/{id}")
    private ResponseEntity<List<KeyEntity>> getAllKeysByGameId(@PathVariable Long id) {
        GameEntity game = gameRepo
            .findById(id).orElseThrow(() -> new NoSuchElementFoundException("Not found Tag with id = " + id));
        List<KeyEntity> keys = game.getKeys();
        if (keys.isEmpty()) {
            return new ResponseEntity<>(keys, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(keys, HttpStatus.OK);
    }

    @PostMapping("/{gameId}")
    private void addKeyToGame(
        @RequestBody KeyEntity keyEntity,
        @PathVariable Long gameId
    ) {
        GameEntity game = gameRepo
            .findById(gameId)
            .orElseThrow(
                () -> new NoSuchElementFoundException("Not found user with id = " + gameId + "Key is not added")
            );
        keyEntity.setGame(game);
        keyRepo.save(keyEntity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<KeyEntity> deleteGameById(@PathVariable Long id) {
        KeyEntity key = keyRepo
            .findById(id).orElseThrow(() -> new NoSuchElementFoundException("Not found game key with id = " + id));
        keyRepo.delete(key);
        return new ResponseEntity<>(key, HttpStatus.OK);
    }
}
