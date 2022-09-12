package repository;

import domain.Recipe;
import domain.RecipeCollection;
import domain.RecipeIngredient;
import domain.RecipeIngredients;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class RecipeRepository implements RecipeRepositoryInterface {

    private final String fileName = "MyRecipes.txt";

    @Override
    public RecipeCollection loadRecipeCollectionFromFile () {

        String fileContent = readInFile();
        return mapFileToRecipeCollection(fileContent);
    }

    private String readInFile () {

        try {
            InputStreamReader reader = new InputStreamReader(new FileInputStream(fileName), StandardCharsets.UTF_8);
            int bufferSize = 1024;
            char[] buffer = new char[bufferSize];
            StringBuilder out = new StringBuilder();
            for (int numRead; (numRead = reader.read(buffer, 0, buffer.length)) > 0; ) {
                out.append(buffer, 0, numRead);
            }

            return out.toString();
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    private RecipeCollection mapFileToRecipeCollection(String fileContent) {

        RecipeCollection recipeCollection = new RecipeCollection();

        List<String> recipeStringList = List.of(splitFileToRecipes(fileContent));

        for (String recipeString : recipeStringList) {
            Recipe recipe = extractRecipeFromString(recipeString);
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

        RecipeIngredients ingredientsMap = new RecipeIngredients();

        String[] ingredientsArray = ingredients.split(",");

        for ( String item : ingredientsArray ) {

            String ingrName = item.split("=")[0];
            String ingrAmount = item.split("=")[1];

            ingredientsMap.add(new RecipeIngredient(ingrName, ingrAmount));
        }

        return new Recipe(name,time,ingredientsMap,instructions);
    }

    private String[] splitFileToRecipes(String fileContent) {

        String[] recipeArray = fileContent.split("###");
        recipeArray = Arrays.copyOfRange(recipeArray, 1, recipeArray.length - 1);

        return recipeArray;
    }

    @Override
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
