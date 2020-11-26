package com.homework.first.service.impl;

import com.homework.first.exception.EntityNotFoundException;
import com.homework.first.model.Recipe;
import com.homework.first.repository.RecipeRepository;
import com.homework.first.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeServiceImpl implements RecipeService {
    private final RecipeRepository recipeRepository;

    @Autowired
    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    @Override
    public Recipe getRecipeById(String id) {
        return recipeRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(String.format("Recipe with id=%s not found", id)));
    }

    @Override
    public Recipe createRecipe(Recipe recipe) {
        //String username = SecurityContextHolder.getContext().getAuthentication().getName();
        //User user = userService.getUserByUsername(username);
        //recipe.setUserId(user.getId());
        return recipeRepository.insert(recipe);
    }

    @Override
    public Recipe updateRecipe(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    @Override
    public Recipe deleteRecipeById(String id) {
        Recipe removed = getRecipeById(id);
        recipeRepository.deleteById(id);
        return removed;
    }
}
