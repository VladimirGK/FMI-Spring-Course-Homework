package com.homework.first.service.impl;

import com.homework.first.exception.EntityNotFoundException;
import com.homework.first.model.Recipe;
import com.homework.first.model.User;
import com.homework.first.repository.RecipeRepository;
import com.homework.first.service.RecipeService;
import com.homework.first.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RecipeServiceImpl implements RecipeService {
    private final RecipeRepository recipeRepository;
    private final UserService userService;

    public RecipeServiceImpl(RecipeRepository recipeRepository, UserService userService) {
        this.recipeRepository = recipeRepository;
        this.userService = userService;
    }

    @Autowired


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
        if(recipe.getUserId() == null) {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            User user = userService.getUserByUsername(username);
            recipe.setUserId(user.getId());
        }
        return recipeRepository.insert(recipe);
    }

    @Override
    public Recipe updateRecipe(Recipe recipe) {
        recipe.setModified(LocalDateTime.now());
        return recipeRepository.save(recipe);
    }

    @Override
    public Recipe deleteRecipeById(String id) {
        Recipe removed = getRecipeById(id);
        recipeRepository.deleteById(id);
        return removed;
    }
    @Override
    public long getCount() {
        return recipeRepository.count();
    }
}
