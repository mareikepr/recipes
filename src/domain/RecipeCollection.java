package domain;

import java.util.*;

public class RecipeCollection {

    private final List<Recipe> allRecipes;

    public RecipeCollection() {

        allRecipes = new ArrayList<Recipe>();
    }

    public void add(Recipe recipe) {

        allRecipes.add(recipe);
    }

    public void printAllCollectedRecipes() {

        System.out.println("\nAll recipes" + allRecipes);

        for (Recipe recipe : allRecipes) {  // for each

            System.out.println("\nName:         " + recipe.getRecipeName());
            System.out.println("Time:         " + recipe.getRecipeTime());
            System.out.println("Ingredients:  " + recipe.getRecipeIngredients());
            System.out.println("Instructions: " + recipe.getRecipeInstructions());
        }
    }
}
