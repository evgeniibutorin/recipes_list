package com.example.demo.controller;


import com.example.demo.domain.Recipe;
import com.example.demo.repos.RecipeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Controller
public class MainController {

    @Autowired
    private RecipeRepo recipeRepo;


    @GetMapping("/")
    public String greeting(Map<String, Object> model) {

        return "greeting";
    }

    @GetMapping("/main")
    public String main(Map<String, Object> model) {
        Iterable<Recipe> recipes = recipeRepo.findAll();
        model.put("recipes", recipes);
        return "main";
    }

    @PostMapping("newRecipe")
    public String add(@RequestParam String name, @RequestParam String description, Map<String, Object> model) {
        Recipe recipe = new Recipe(name, description);
        recipeRepo.save(recipe);
        Iterable<Recipe> recipes = recipeRepo.findAll();
        model.put("recipes", recipes);
        return "main";
    }

    @PostMapping("filter")
    public String filter(@RequestParam String filter, Map<String, Object> model) {
        Iterable<Recipe> recipes;
        if (filter != null && !filter.isEmpty()) {
            recipes = recipeRepo.findByName(filter);
        } else {
            recipes = recipeRepo.findAll();
        }
        model.put("recipes", recipes);
        return "main";

    }
}
