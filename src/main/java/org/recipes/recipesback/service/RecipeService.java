package org.recipes.recipesback.service;

import org.recipes.recipesback.model.Recipe;

import java.util.List;
import java.util.Optional;

public interface RecipeService {

    Recipe createRecipe(Recipe recipe);

    List<Recipe> getAllRecipes();

    Optional<Recipe> getRecipeById(Integer id);

    Optional<Recipe> updateRecipe(Integer id, Recipe updatedRecipe);

    void deleteRecipe(Integer id);
}
