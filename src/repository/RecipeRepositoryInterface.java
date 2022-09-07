package repository;

import domain.RecipeCollection;

import java.util.Map;

public interface RecipeRepositoryInterface {

    public RecipeCollection loadRecipeCollectionFromFile();

    public void saveRecipeToFile(String recipeName,
                                 String recipeTime,
                                 Map<String,String> recipeIngredients,
                                 String recipeInstructions);

}
