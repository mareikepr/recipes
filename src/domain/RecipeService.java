package domain;

import repository.RecipeRepositoryInterface;

import java.util.*;

public class RecipeService {

    private RecipeCollection recipeCollection;
    private final RecipeRepositoryInterface recipeRepository;

    public RecipeService(RecipeRepositoryInterface recipeRepository) {

        this.recipeRepository = recipeRepository;
        loadAllRecipes();

        recipeCollection.searchAndPrintRecipe("Schokokuchen"); // test, normalerweise über ui
        recipeCollection.printAllCollectedRecipes();
    }

    public void loadAllRecipes () {

        recipeCollection = recipeRepository.loadRecipeCollectionFromFile();
    }
    
    public void addRecipeToCollectionAndFile (String recipeName,
                            String recipeTime,
                            String recipeIngredients,
                            String recipeInstructions) {

        Map<String, String> ingredientsMap = ingredientsStringToMap(recipeIngredients);

        Recipe recipe = new Recipe(recipeName, recipeTime, ingredientsMap, recipeInstructions);


        recipeCollection.add(recipe);
        recipeRepository.saveRecipeToFile(recipeName, recipeTime, ingredientsMap, recipeInstructions);
    }

    public void printShoppingList(List<String> recipeList) {

        List<List<String>> shoppingList = new ArrayList<>();

        for (String s : recipeList) {
            Recipe recipe = recipeCollection.searchAndGetRecipe(s);
            shoppingList.add(recipe.getIngredientsListString());
        }

        System.out.println("\n Shopping list: " + shoppingList);
    }
    public List<String> getRecipeNames(){

        return recipeCollection.getRecipeNameList();
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

    public void printCollection() {
        recipeCollection.printAllCollectedRecipes();
    }
}
