package domain;

import java.util.*;

public class Recipe {

    private final String recipeName;
    private final String recipeTime;
    private final String recipeInstructions;
    private Map<String,String> recipeIngredients = new HashMap<String,String>();          // final?

    public Recipe(String recipeName,
                  String recipeTime,
                  Map<String,String> recipeIngredients,
                  String recipeInstructions) {

        this.recipeName = recipeName;
        this.recipeTime = recipeTime;
        this.recipeIngredients = recipeIngredients;
        this.recipeInstructions = recipeInstructions;
    }

    public String getRecipeName() {
        return recipeName;
    }
    public String getRecipeTime() {
        return recipeTime;
    }
    public String getRecipeInstructions() {
        return recipeInstructions;
    }
    public Map<String,String> getRecipeIngredients() {
        return recipeIngredients;
    }
}