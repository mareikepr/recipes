package domain;

import repository.RecipeRepository;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

public class RecipeService {

    private RecipeCollection recipeCollection;
    private final RecipeRepository recipeRepository;

    public RecipeService(RecipeRepository recipeRepository) {

        this.recipeRepository = recipeRepository;
        loadAllRecipes();
        recipeCollection.printAllCollectedRecipes();
    }

    public void loadAllRecipes () {

        recipeCollection = recipeRepository.loadRecipeCollection();
    }

    public void saveRecipe (String recipeName,
                            String recipeTime,
                            String recipeIngredients,
                            String recipeInstructions) throws FileNotFoundException {

        Map<String, String> ingredientsMap = ingredientsStringToMap(recipeIngredients);

        Recipe recipe = new Recipe(recipeName, recipeTime, ingredientsMap, recipeInstructions);

        recipeCollection.add(recipe);
        recipeRepository.saveRecipeToFile(recipeName, recipeTime, ingredientsMap, recipeInstructions);

        recipeCollection.printAllCollectedRecipes();
    }

    private Map<String,String> ingredientsStringToMap(String ingredients) {

        HashMap<String, String> ingredientsHashMap = new HashMap<>();
        String[] splitIngredients = ingredients.split("\n");
        String ingredientAmount;
        String ingredientName;

        for (String splitIngredient : splitIngredients) {

            ingredientAmount = splitIngredient.split("\s")[0]; // amount
            ingredientName = splitIngredient.split("\s")[1]; // name
            ingredientsHashMap.put(ingredientName, ingredientAmount); // put method from Collection (key,value)
        }
        return ingredientsHashMap;
    }
}
