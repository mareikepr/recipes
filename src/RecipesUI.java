public class RecipesUI {

    public static void main (String args[]) {

        RecipeCollection myRecipeCollection = new RecipeCollection();
        //myRecipeCollection.recipeExistsInFile("Schokokuchen");
        myRecipeCollection.printAllCollectedRecipes();
        new GUI();

    }
}
