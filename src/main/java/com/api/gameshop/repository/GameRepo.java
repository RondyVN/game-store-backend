package com.api.gameshop.repository;

import com.api.gameshop.entity.GameEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepo extends CrudRepository<GameEntity, Long> {
    GameEntity findByName(String gameName);
}
