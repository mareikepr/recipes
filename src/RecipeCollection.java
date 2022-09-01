import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class RecipeCollection {

    public int numberRecipes = 0;
    public String fileName = "MyRecipes.txt";
    public String fileContent;
    List<Recipe> allRecipes = new ArrayList<Recipe>();

    public RecipeCollection() {
        fileContent = readInFile();
        recipeCollecting();
    }

    public void addRecipe(Recipe recipe) {
        // called when new recipe is saved to file in Recipe.java
        //allRecipes.add(recipe);
        readInFile();
        //numberRecipes += 1;
    }

    InputStreamReader reader;

    public String readInFile () {
        // Read in file
        try {
            reader = new InputStreamReader(new FileInputStream(fileName), "UTF-8");
            int bufferSize = 1024;
            char[] buffer = new char[bufferSize]; // new?
            StringBuilder out = new StringBuilder();
            for (int numRead; (numRead = reader.read(buffer, 0, buffer.length)) > 0; ) {
                out.append(buffer, 0, numRead);
            }
            fileContent = out.toString();
            //System.out.println("Content of file: " + fileContent);
            return fileContent;
        } catch (IOException ex) {
            ex.printStackTrace();
            return "ERROR";
        }
    }

    public boolean recipeExistsInFile (String keyword) {
        String fileContent = readInFile();
        //System.out.println("Content of file: " + fileContent);
        boolean recipeContained = fileContent.contains(keyword);
        if (recipeContained == true) {System.out.println("Recipe \"" + keyword + "\" is contained.");}

        return recipeContained;
    }
    public void recipeCollecting() {
        // read in and separate recipe
        String[] splitCollection = fileContent.split("###");
        //System.out.println(fileContent);

        for (int i = 1; i <= splitCollection.length - 1; i++) {
            //System.out.println("Entry " + iC + " " + splitCollection[iC]);
            String fieldRecipeName = splitCollection[i].split("\n")[1];
            String fieldRecipeTime = splitCollection[i].split("\n")[2];
            String fieldRecipeIngr = splitCollection[i].split("\n")[3];
            String fieldRecipeInst = splitCollection[i].split("\n")[4];

            String name = fieldRecipeName.substring(18);
            String time = fieldRecipeTime.substring(18);
            String ingredients = fieldRecipeIngr.substring(18);
            ingredients = ingredients.replace("{","");
            ingredients = ingredients.replace("}","");

            Map<String,String> ingredientsMapHelper = new HashMap<String,String>();

            for (int j = 0; j < ingredients.length(); j++) {

                String[] ingredientsArray = ingredients.split(",");

                for  (int k = 0; k < ingredientsArray.length; k++) {
                    String key = ingredientsArray[k].split("=")[0];
                    String value = ingredientsArray[k].split("=")[1];
                    ingredientsMapHelper.put(key,value);
                }
            }

            String instructions = fieldRecipeInst.substring(18);

            // create Recipe instances
            Recipe containedRecipe = new Recipe(name,time,instructions);
            containedRecipe.setRecipeName(name);
            containedRecipe.setRecipeInstructions(instructions);
            containedRecipe.setRecipeTime(time);
            containedRecipe.setIngredientsMap(ingredientsMapHelper);

            allRecipes.add(containedRecipe); // add to ArrayList which contains Recipe objects

        }
        //System.out.println("All recipes" + allRecipes); // automatisch .toString()
    }

    public void printAllCollectedRecipes() {

        System.out.println("\nAll recipes" + allRecipes);

        for (int i = 0; i < allRecipes.size(); i++) {
            Recipe recipe = allRecipes.get(i);
            System.out.println("\nName: " + recipe.getRecipeName());
            System.out.println("Time: " + recipe.getRecipeTime());
            System.out.println("Ingredients: " + recipe.getIngredientsMap());
            System.out.println("Instructions: " + recipe.getRecipeInstructions());
        }
    }
}
