package com.api.gameshop.repository;

import com.api.gameshop.entity.GameEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface GamePagination extends PagingAndSortingRepository<GameEntity, Long> {

}
