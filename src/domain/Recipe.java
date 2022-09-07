package domain;

import java.util.*;

public class Recipe {

    private final String recipeName;
    private final String recipeTime;
    private final String recipeInstructions;
    private final Map<String,String> recipeIngredients;  // final?, HashMap Map

    public Recipe(String recipeName,
                  String recipeTime,
                  Map<String,String> recipeIngredients,
                  String recipeInstructions) {

        this.recipeName = recipeName;
        this.recipeTime = recipeTime;
        this.recipeIngredients = recipeIngredients;
        this.recipeInstructions = recipeInstructions;
    }

    List<String> getIngredientsListString() {

        Map<String, String> ingredientsMap = this.getRecipeIngredients();
        List<String> ingredientsList = new ArrayList<>();

        for(Map.Entry m : ingredientsMap.entrySet()) {
            ingredientsList.add( m.getKey() + " ("+ m.getValue() + ")");
        }
        return ingredientsList;
    }

    String getRecipeName() {   // protected, in package, in derived class, default: in package
        return recipeName;
    }

    String getRecipeTime() {
        return recipeTime;
    }
    String getRecipeInstructions() {
        return recipeInstructions;
    }
    Map<String,String> getRecipeIngredients() {
        return recipeIngredients;
    }
}