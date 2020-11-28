package com.homework.first.service;

import com.homework.first.model.Recipe;

import java.util.List;

public interface RecipeService {
    List<Recipe> getAllRecipes();
    Recipe getRecipeById(String id);
    Recipe createRecipe(Recipe recipe);
    Recipe updateRecipe(Recipe recipe);
    Recipe deleteRecipeById(String id);
    long getCount();
}
