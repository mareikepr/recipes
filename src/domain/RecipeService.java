package domain;

import repository.RecipeRepositoryInterface;

import java.util.*;
import java.util.stream.Collectors;
import java.util.List;

public class RecipeService {

    private RecipeCollection recipeCollection;
    private final RecipeRepositoryInterface recipeRepository;
    private final ShoppingListServiceInterface shoppingListServiceInterface;

    public RecipeService(RecipeRepositoryInterface recipeRepository, ShoppingListServiceInterface shoppingListServiceInterface) {

        this.recipeRepository = recipeRepository;
        this.shoppingListServiceInterface = shoppingListServiceInterface;

        loadAllRecipes();

        recipeCollection.searchAndPrintRecipe("Schokokuchen"); // test
        recipeCollection.printAllCollectedRecipes();
    }

    public void loadAllRecipes () {


        recipeCollection = recipeRepository.loadRecipeCollectionFromFile();

    }

    public void addRecipeToCollectionAndFile (String recipeName,
                            String recipeTime,
                            String recipeIngredients,
                            String recipeInstructions) {

        RecipeIngredients ingredientsList = ingredientsStringToList(recipeIngredients);

        Map<String, String> ingredientsMap = ingredientsList.stream().collect(Collectors.toMap(RecipeIngredient::getName, RecipeIngredient::getAmount));

        Recipe recipe = new Recipe(recipeName, recipeTime, ingredientsList, recipeInstructions);

        recipeCollection.add(recipe);
        recipeRepository.saveRecipeToFile(recipeName, recipeTime, ingredientsMap, recipeInstructions);
    }


    public void printShoppingList(List<String> recipeList) {

        // delete duplicates, sum up amounts
        List<String> shoppingList = new ArrayList<>();

        for (String s : recipeList) {
            Optional<Recipe> recipe = recipeCollection.searchAndGetRecipe(s);

            if (recipe.isPresent()) {
                shoppingList.add(recipe.get().getIngredientsListString().toString());
            } else {
                System.out.println("Recipe " + s + " not found.");
            }
        }

        shoppingListServiceInterface.printShoppingList(shoppingList);
    }

    public List<String> getRecipeNames(){

        return recipeCollection.getRecipeNameList();
    }

    private RecipeIngredients ingredientsStringToList(String ingredients) {

        RecipeIngredients ingredientsList = new RecipeIngredients();
        String[] splitIngredients = ingredients.split("\n");

        for (String splitIngredient : splitIngredients) {

            String name = splitIngredient.split("\s")[1];
            String amount = splitIngredient.split("\s")[0];
            ingredientsList.add(new RecipeIngredient(name, amount));
        }

        return ingredientsList;
    }

    public void printCollection() {

        recipeCollection.printAllCollectedRecipes();
    }
}
