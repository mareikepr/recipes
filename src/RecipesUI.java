import domain.RecipeService;
import domain.ShoppingListServiceInterface;
import repository.RecipeRepository;
import repository.RecipeRepositoryInterface;
import ui.ShoppingListToConsole;
import ui.GUI;

import java.util.Arrays;

public class RecipesUI {

    public static void main (String[] args) {

        RecipeRepositoryInterface recipeRepository = new RecipeRepository();

        ShoppingListServiceInterface shoppingListToConsole = new ShoppingListToConsole();
        RecipeService recipeService = new RecipeService(recipeRepository, shoppingListToConsole);

        recipeService.printShoppingList(Arrays.asList("Schokokuchen", "Quarkkuchen"));

        new GUI(recipeService);
    }
}