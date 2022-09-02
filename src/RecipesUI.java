import domain.RecipeCollection;
import domain.RecipeService;
import repository.RecipeRepository;
import ui.GUI;

public class RecipesUI {

    public static void main (String args[]) {

        RecipeRepository recipeRepository = new RecipeRepository();
        RecipeService recipeService = new RecipeService(recipeRepository);
        new GUI(recipeService);
    }
}
