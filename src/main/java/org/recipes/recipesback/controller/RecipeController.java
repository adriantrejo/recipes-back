package org.recipes.recipesback.controller;

import org.recipes.recipesback.model.Recipe;
import org.recipes.recipesback.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/recipes")
public class RecipeController {
        private final RecipeService recipeService;

        @Autowired
        public RecipeController(RecipeService recipeService) {
            this.recipeService = recipeService;
        }

    @PostMapping
    public ResponseEntity<Map<String, Object>> createRecipe(@RequestBody Recipe recipe) {
        // Check if any required fields are missing
        boolean hasMissingFields = false;

        if (recipe.getTitle() == null || recipe.getMakingTime() == null ||
                recipe.getServes() == null || recipe.getIngredients() == null ||
                recipe.getCost() == null) {

            hasMissingFields = true;
        }

        if (hasMissingFields) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("message", "Recipe creation failed!");
            errorResponse.put("required", "title, making_time, serves, ingredients, cost");
            return ResponseEntity.status(200).body(errorResponse);
        }

        // Proceed with creating the recipe
        Recipe createdRecipe = recipeService.createRecipe(recipe);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Recipe successfully created!");
        response.put("recipe", List.of(createdRecipe));
        return ResponseEntity.status(200).body(response);
    }




    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllRecipes() {
        List<Recipe> recipes = recipeService.getAllRecipes(); // Fetch the list of recipes

        // Create a response map to hold the response structure
        Map<String, Object> response = new HashMap<>();
        response.put("recipes", recipes);

        // If the recipes list is empty, return no content
        if (recipes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getRecipeById(@PathVariable Integer id) {
        Optional<Recipe> recipe = recipeService.getRecipeById(id);
        Map<String, Object> response = new HashMap<>();
        if (recipe.isPresent()) {
            response.put("message", "Recipe details by id");
            response.put("recipe", List.of(recipe.get()));
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "Recipe not found");
            return ResponseEntity.status(404).body(response);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateRecipe(@PathVariable Integer id, @RequestBody Recipe updatedRecipe) {
        Optional<Recipe> recipe = recipeService.updateRecipe(id, updatedRecipe);
        Map<String, Object> response = new HashMap<>();
        if (recipe.isPresent()) {
            response.put("message", "Recipe successfully updated!");
            response.put("recipe", List.of(updatedRecipe));
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "Recipe not found");
            return ResponseEntity.status(404).body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteRecipe(@PathVariable Integer id) {
        recipeService.deleteRecipe(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Recipe successfully removed!");
        return ResponseEntity.ok(response);
    }
}