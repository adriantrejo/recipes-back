package org.recipes.recipesback.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RecipeTest {
    @Test
    public void testRecipeProperties() {
        // Create a new Recipe instance
        Recipe recipe = new Recipe();

        // Set properties
        recipe.setId(1);
        recipe.setTitle("Tomato Soup");
        recipe.setMakingTime("15 min");
        recipe.setServes("4 people");
        recipe.setIngredients("tomato, water, seasoning");
        recipe.setCost(500);
        LocalDateTime now = LocalDateTime.now();
        recipe.setCreatedAt(now);
        recipe.setUpdatedAt(now);

        // Assert that the properties are set correctly
        assertEquals(1, recipe.getId());
        assertEquals("Tomato Soup", recipe.getTitle());
        assertEquals("15 min", recipe.getMakingTime());
        assertEquals("4 people", recipe.getServes());
        assertEquals("tomato, water, seasoning", recipe.getIngredients());
        assertEquals(500, recipe.getCost());
        assertEquals(now, recipe.getCreatedAt());
        assertEquals(now, recipe.getUpdatedAt());
    }
}
