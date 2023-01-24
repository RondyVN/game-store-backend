package com.api.gameshop.repository;
import com.api.gameshop.entity.GenreEntity;
import org.springframework.data.repository.CrudRepository;

public interface GenreRepo extends CrudRepository<GenreEntity, Long> {
    GenreEntity findByGenreName(String genreName);
}
