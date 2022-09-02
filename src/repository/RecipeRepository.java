package repository;

import domain.Recipe;
import domain.RecipeCollection;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class RecipeRepository {

    private final String fileName = "MyRecipes.txt";

    public RecipeCollection loadRecipeCollection () {

        String fileContent = readInFile(fileName);
        return mapToRecipeCollection(fileContent);
    }

    private String readInFile (String fileName) {

        try {
            InputStreamReader reader = new InputStreamReader(new FileInputStream(fileName), "UTF-8");
            int bufferSize = 1024;
            char[] buffer = new char[bufferSize]; // new?
            StringBuilder out = new StringBuilder();
            for (int numRead; (numRead = reader.read(buffer, 0, buffer.length)) > 0; ) {
                out.append(buffer, 0, numRead);
            }
            String fileContent = out.toString();

            return fileContent;
        } catch (IOException ex) {
            ex.printStackTrace();
            return "ERROR";
        }
    }

    private RecipeCollection mapToRecipeCollection(String fileContent) {

        // too long method
        RecipeCollection recipeCollection = new RecipeCollection();

        String[] splitCollection = fileContent.split("###");

        for (int i = 1; i < splitCollection.length; i++) {

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

            recipeCollection.add(new Recipe(name,time,ingredientsMapHelper,instructions)); // add to ArrayList
        }
        return recipeCollection;
    }

    public void saveRecipeToFile(String recipeName,
                                 String recipeTime,
                                 Map<String,String> recipeIngredients,
                                 String recipeInstructions) throws FileNotFoundException {

        try {
            OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(fileName, true), "UTF-8");
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
