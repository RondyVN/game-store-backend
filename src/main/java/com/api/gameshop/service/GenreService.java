package com.api.gameshop.service;
import com.api.gameshop.entity.GenreEntity;
import com.api.gameshop.repository.GenreRepo;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GenreService {
    @Autowired
    private GenreRepo genreRepo;

    public GenreEntity addGenre(GenreEntity genreEntity) throws Exception {
        if (genreRepo.findByGenreName(genreEntity.getGenreName()) != null) {
            System.out.println("ERROR genre already exist");
            throw new Exception();
        }
        return genreRepo.save(genreEntity);
    }

    public ResponseEntity<List<GenreEntity>> getAllGenres() throws Exception {
        List<GenreEntity> genreEntity = new ArrayList<>();
        genreRepo.findAll().forEach(genreEntity::add);
       if (genreEntity.isEmpty()) {
           return new ResponseEntity<>(HttpStatus.NO_CONTENT);
       }
       return new ResponseEntity<>(genreEntity, HttpStatus.OK);
    }

}
