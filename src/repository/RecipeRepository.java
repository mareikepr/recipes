package repository;

import domain.Recipe;
import domain.RecipeCollection;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class RecipeRepository implements RecipeRepositoryInterface {

    private final String fileName = "MyRecipes.txt";

    public RecipeCollection loadRecipeCollectionFromFile () {

        String fileContent = readInFile();
        return mapToRecipeCollection(fileContent);
    }

    private String readInFile () {

        try {
            InputStreamReader reader = new InputStreamReader(new FileInputStream(fileName), StandardCharsets.UTF_8);
            int bufferSize = 1024;
            char[] buffer = new char[bufferSize]; // new?
            StringBuilder out = new StringBuilder();
            for (int numRead; (numRead = reader.read(buffer, 0, buffer.length)) > 0; ) {
                out.append(buffer, 0, numRead);
            }

            return out.toString();
        } catch (IOException ex) {
            ex.printStackTrace();
            return "ERROR";
        }
    }

    private RecipeCollection mapToRecipeCollection(String fileContent) {        // Mapper Klassen ?

        RecipeCollection recipeCollection = new RecipeCollection();

        String[] recipeStringArray = splitFileToRecipes(fileContent);

        for (int i = 1; i < recipeStringArray.length; i++) {
            Recipe recipe = extractRecipeFromString(recipeStringArray[i]);
            recipeCollection.add(recipe);
        }
        return recipeCollection;
    }

    private Recipe extractRecipeFromString(String recipeString) {

        String fieldRecipeName = recipeString.split("\n")[1];
        String fieldRecipeTime = recipeString.split("\n")[2];
        String fieldRecipeIngr = recipeString.split("\n")[3];
        String fieldRecipeInst = recipeString.split("\n")[4];

        int beginIndex = 18;
        String name = fieldRecipeName.substring(beginIndex);
        String time = fieldRecipeTime.substring(beginIndex);
        String instructions = fieldRecipeInst.substring(beginIndex);
        String ingredients = fieldRecipeIngr.substring(beginIndex);
        ingredients = ingredients.replace("{","");
        ingredients = ingredients.replace("}","");

        Map<String,String> ingredientsMap = new HashMap<>();

        for (int j = 0; j < ingredients.length(); j++) {

            String[] ingredientsArray = ingredients.split(",");

            //for  (int k = 0; k < ingredientsArray.length; k++) {
            //    String key = ingredientsArray[k].split("=")[0];
            //    String value = ingredientsArray[k].split("=")[1];
            //    ingredientsMapHelper.put(key,value);
            //}
            for ( String item : ingredientsArray ) {
                ingredientsMap.put(item.split("=")[0],item.split("=")[1]);
            }
        }

        return new Recipe(name,time,ingredientsMap,instructions);
    }

    private String[] splitFileToRecipes(String fileContent) {
        return fileContent.split("###");
    }

    public void saveRecipeToFile(String recipeName,
                                 String recipeTime,
                                 Map<String, String> recipeIngredients,
                                 String recipeInstructions) {

        try {
            OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(fileName, true), StandardCharsets.UTF_8);
            writer.write("\n ### ");
            writer.write("\nName:             " + recipeName);
            writer.write("\nPreparation time: " + recipeTime);
            writer.write("\nIngredients:      " + recipeIngredients);
            writer.write("\nInstructions:     " + recipeInstructions);
            writer.write("\n");
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    /*
    public boolean recipeExistsInFile (String keyword) {
        String fileContent = readInFile();
        //System.out.println("Content of file: " + fileContent);
        boolean recipeContained = fileContent.contains(keyword);
        if (recipeContained == true) {System.out.println("domain.Recipe \"" + keyword + "\" is contained.");}

        return recipeContained;
    }
    */
}
