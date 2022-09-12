package domain;

import java.util.*;

public class Recipe {

    private final String recipeName;
    private final String recipeTime;
    private final String recipeInstructions;
    private final RecipeIngredients recipeIngredients;

    public Recipe(String recipeName,
                  String recipeTime,
                  RecipeIngredients recipeIngredients,
                  String recipeInstructions) {

        this.recipeName = recipeName;
        this.recipeTime = recipeTime;
        this.recipeIngredients = recipeIngredients;
        this.recipeInstructions = recipeInstructions;
    }

    List<String> getIngredientsListString() {

        RecipeIngredients recipeIngredients = this.getRecipeIngredients();
        List<String> ingredientsList = new ArrayList<>();

        for( RecipeIngredient m : recipeIngredients.stream().toList()) {
            ingredientsList.add( m.getName() + " ("+ m.getAmount() + ")");
        }
        return ingredientsList;
    }

    String getRecipeName() {   // protected: in package, in derived class, default: in package

        return recipeName;
    }

    String getRecipeTime() {

        return recipeTime;
    }
    String getRecipeInstructions() {

        return recipeInstructions;
    }
    RecipeIngredients getRecipeIngredients() {

        return recipeIngredients;
    }
}