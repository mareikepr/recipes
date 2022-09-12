package domain;

import java.util.*;

public class RecipeCollection {

    private final List<Recipe> allRecipes;

    public RecipeCollection() {

        allRecipes = new ArrayList<>();
    }

    public List<String> getRecipeNameList() {

        //List<String> allRecipesNamesList = allRecipes.stream().map(r -> r.getRecipeName()).toList();
        return allRecipes.stream().map(Recipe::getRecipeName).toList();
    }

    public void searchAndPrintRecipe(String recipeName) {

        //allRecipes.stream().filter( r -> compareNames(r, recipeName)).forEach(r -> printRecipe(r)); // stream filter foreach
        allRecipes.stream().filter( r -> compareRecipeNames(r, recipeName)).forEach(this::printRecipe); // method ref
    }

    public Recipe searchAndGetRecipe(String recipeName) {

        return allRecipes.stream().filter(r -> compareRecipeNames(r, recipeName)).findAny().get();
    }

    private boolean compareRecipeNames(Recipe recipe, String recipeKeyword) {

        // recipe.getRecipeName() == recipeKeyword );        == compares references not values
        // recipe.getRecipeName().equals(recipeKeyword ));   compares values

        return recipe.getRecipeName().equals(recipeKeyword);
    }

    public void add(Recipe recipe) {

        allRecipes.add(recipe);
    }

    public void printAllCollectedRecipes() {

        System.out.println("\nAll recipes" + allRecipes);

        /*
        for (Recipe recipe : allRecipes) {  // for each
            printRecipe(recipe);
        }*/

        //allRecipes.forEach(r -> printRecipe(r));
        allRecipes.forEach(this::printRecipe);
        //allRecipes.stream().forEach(r -> printRecipe(r)); // with stream
    }

    public void printRecipe(Recipe recipe) {                              // Function of Collection or Recipe ?

        System.out.println("\nName:         " + recipe.getRecipeName());
        System.out.println("Time:         " + recipe.getRecipeTime());
        System.out.println("Ingredients:  " + recipe.getRecipeIngredients());
        System.out.println("Instructions: " + recipe.getRecipeInstructions());
    }
}
