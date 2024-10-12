package org.recipes.recipesback.service;

import org.recipes.recipesback.model.Recipe;
import org.recipes.recipesback.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecipeServiceImpl implements RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    public Recipe createRecipe(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    public Optional<Recipe> getRecipeById(Integer id) {
        return recipeRepository.findById(id);
    }

    public Optional<Recipe> updateRecipe(Integer id, Recipe updatedRecipe) {
        if (recipeRepository.existsById(id)) {
            updatedRecipe.setId(id);
            return Optional.of(recipeRepository.save(updatedRecipe));
        }
        return Optional.empty();
    }

    public void deleteRecipe(Integer id) {
        recipeRepository.deleteById(id);
    }
}

