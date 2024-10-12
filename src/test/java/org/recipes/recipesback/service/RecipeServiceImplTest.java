package org.recipes.recipesback.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.recipes.recipesback.model.Recipe;
import org.recipes.recipesback.repository.RecipeRepository;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class RecipeServiceImplTest {
    @Mock
    private RecipeRepository recipeRepository;

    @InjectMocks
    private RecipeServiceImpl recipeService;

    private Recipe recipe;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        recipe = new Recipe();
        recipe.setId(1);
        recipe.setTitle("Tomato Soup");
        recipe.setMakingTime("15 min");
        recipe.setServes("4 people");
        recipe.setIngredients("tomato, water, seasoning");
        recipe.setCost(500);
        recipe.setCreatedAt(LocalDateTime.now());
        recipe.setUpdatedAt(LocalDateTime.now());
    }

    @Test
    public void testCreateRecipe() {
        when(recipeRepository.save(any(Recipe.class))).thenReturn(recipe);

        Recipe createdRecipe = recipeService.createRecipe(recipe);

        assertNotNull(createdRecipe);
        assertEquals(recipe.getTitle(), createdRecipe.getTitle());
        verify(recipeRepository, times(1)).save(any(Recipe.class));
    }

    @Test
    public void testGetAllRecipes() {
        when(recipeRepository.findAll()).thenReturn(Arrays.asList(recipe));

        List<Recipe> recipes = recipeService.getAllRecipes();

        assertNotNull(recipes);
        assertEquals(1, recipes.size());
        assertEquals(recipe.getTitle(), recipes.get(0).getTitle());
        verify(recipeRepository, times(1)).findAll();
    }

    @Test
    public void testGetRecipeById() {
        when(recipeRepository.findById(1)).thenReturn(Optional.of(recipe));

        Optional<Recipe> foundRecipe = recipeService.getRecipeById(1);

        assertTrue(foundRecipe.isPresent());
        assertEquals(recipe.getTitle(), foundRecipe.get().getTitle());
        verify(recipeRepository, times(1)).findById(1);
    }

    @Test
    public void testUpdateRecipe_Success() {
        when(recipeRepository.existsById(1)).thenReturn(true);
        when(recipeRepository.save(any(Recipe.class))).thenReturn(recipe);

        Optional<Recipe> updatedRecipe = recipeService.updateRecipe(1, recipe);

        assertTrue(updatedRecipe.isPresent());
        assertEquals(recipe.getTitle(), updatedRecipe.get().getTitle());
        verify(recipeRepository, times(1)).existsById(1);
        verify(recipeRepository, times(1)).save(any(Recipe.class));
    }

    @Test
    public void testUpdateRecipe_NotFound() {
        when(recipeRepository.existsById(1)).thenReturn(false);

        Optional<Recipe> updatedRecipe = recipeService.updateRecipe(1, recipe);

        assertFalse(updatedRecipe.isPresent());
        verify(recipeRepository, times(1)).existsById(1);
        verify(recipeRepository, never()).save(any(Recipe.class));
    }

    @Test
    public void testDeleteRecipe() {
        doNothing().when(recipeRepository).deleteById(1);

        recipeService.deleteRecipe(1);

        verify(recipeRepository, times(1)).deleteById(1);
    }
}
