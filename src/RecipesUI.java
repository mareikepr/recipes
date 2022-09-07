import domain.RecipeService;
import repository.RecipeRepository;
import repository.RecipeRepositoryInterface;
import ui.GUI;

import java.util.Arrays;

public class RecipesUI {

    public static void main (String[] args) {

        RecipeRepositoryInterface recipeRepository = new RecipeRepository();
        RecipeService recipeService = new RecipeService(recipeRepository);

        recipeService.printShoppingList(Arrays.asList("Schokokuchen", "Quarkkuchen")); // with interface, ui

        new GUI(recipeService);
    }
}
