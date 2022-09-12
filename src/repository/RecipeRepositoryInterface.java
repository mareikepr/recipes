package repository;

import domain.RecipeCollection;

import java.util.Map;

public interface RecipeRepositoryInterface {

    RecipeCollection loadRecipeCollectionFromFile();

    void saveRecipeToFile(String recipeName,
                                 String recipeTime,
                                 Map<String,String> recipeIngredients,
                                 String recipeInstructions);

}
