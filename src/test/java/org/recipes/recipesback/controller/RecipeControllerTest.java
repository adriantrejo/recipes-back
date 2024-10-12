package org.recipes.recipesback.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.recipes.recipesback.model.Recipe;
import org.recipes.recipesback.service.RecipeService;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest
public class RecipeControllerTest {

    @InjectMocks
    private RecipeController recipeController;

    @Mock
    private RecipeService recipeService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateRecipe_Failure_MissingFields() {
        Recipe recipe = new Recipe(); // Missing all fields

        ResponseEntity<Map<String, Object>> response = recipeController.createRecipe(recipe);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Recipe creation failed!", response.getBody().get("message"));
        assertEquals("title, making_time, serves, ingredients, cost", response.getBody().get("required"));
    }

    @Test
    public void testGetAllRecipes_Empty() {
        when(recipeService.getAllRecipes()).thenReturn(Arrays.asList());

        ResponseEntity<Map<String, Object>> response = recipeController.getAllRecipes();

        assertEquals(204, response.getStatusCodeValue());
    }

    @Test
    public void testGetRecipeById_NotFound() {
        when(recipeService.getRecipeById(1)).thenReturn(Optional.empty());

        ResponseEntity<Map<String, Object>> response = recipeController.getRecipeById(1);

        assertEquals(404, response.getStatusCodeValue());
        assertEquals("Recipe not found", response.getBody().get("message"));
    }

    @Test
    public void testUpdateRecipe_NotFound() {
        Recipe updatedRecipe = new Recipe();

        when(recipeService.updateRecipe(1, updatedRecipe)).thenReturn(Optional.empty());

        ResponseEntity<Map<String, Object>> response = recipeController.updateRecipe(1, updatedRecipe);

        assertEquals(404, response.getStatusCodeValue());
        assertEquals("Recipe not found", response.getBody().get("message"));
    }

    @Test
    public void testDeleteRecipe() {
        doNothing().when(recipeService).deleteRecipe(1);

        ResponseEntity<Map<String, String>> response = recipeController.deleteRecipe(1);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Recipe successfully removed!", response.getBody().get("message"));
    }
}
