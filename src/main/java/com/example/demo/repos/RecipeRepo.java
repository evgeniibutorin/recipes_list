package com.example.demo.repos;

import com.example.demo.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RecipeRepo extends CrudRepository<Recipe, Long> {
    List<Recipe> findByName (String name);
}
