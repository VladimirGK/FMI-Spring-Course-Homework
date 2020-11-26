package com.homework.first.controller;


import com.homework.first.exception.InvalidEntityDataException;
import com.homework.first.model.Recipe;
import com.homework.first.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

import static com.homework.first.util.ErrorHandlingUtils.getErrors;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {
    @Autowired
    private RecipeService recipeService;

    @GetMapping
    public List<Recipe> getAllRecipes() {
        return recipeService.getAllRecipes();
    }

    @GetMapping("/{id:^[A-Fa-f0-9]{24}$}")
    public Recipe getPostById(@PathVariable("id") String id) {
        return recipeService.getRecipeById(id);
    }

    @PostMapping
    public ResponseEntity<Recipe> createPost(@Valid @RequestBody Recipe recipe, Errors errors) {
        if(errors.hasErrors()) {
            throw new InvalidEntityDataException("Invalid Recipe data: ", getErrors(errors));
        }
        Recipe created = recipeService.createRecipe(recipe);
        return ResponseEntity.created(
                ServletUriComponentsBuilder.fromCurrentRequest()
                        .pathSegment("{id}").buildAndExpand(created.getId()).toUri()
        ).body(created);
    }

    @PutMapping("/{id:^[A-Fa-f0-9]{24}$}")
    public Recipe updateRecipe(@PathVariable("id") String id,
                            @Valid @RequestBody Recipe recipe, Errors errors) {
        if(errors.hasErrors()) {
            throw new InvalidEntityDataException("Invalid Recipe data: ", getErrors(errors));
        }
        if(!id.equals(recipe.getId())) {
            throw new InvalidEntityDataException("Recipe ID:%s in the URL differs from ID:%s in the body.");
        }
        return recipeService.updateRecipe(recipe);
    }

    @DeleteMapping("/{id:^[A-Fa-f0-9]{24}$}")
    public Recipe deleteRecipe(@PathVariable("id") String id) {

        return recipeService.deleteRecipeById(id);
    }
}
