
import java.util.*;  // implements Java Collection framework
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
//extends RecipeCollection
public class Recipe {

    // Static: Class attribute, not object-specific attribute
    // Private: only used in class itself
    private static int numberRecipes;
    // static

    // Private: used inside class, can be accessed via getter/setter methods
    private String recipeName;
    private String recipeTime; // or collected steps
    private String recipeInstructions;
    private int numberIngredients;
    // HashMap Ingredient + amount map
    private Map<String,String> ingredientsMap = new HashMap<String,String>();
    //var ingredientsMap = new HashMap<String,String>(); ?

    OutputStreamWriter writer;
    InputStreamReader reader;
    public String fileName = "MyRecipes.txt"; // RecipeColl, doppelt
    public String fileContent; // RecipeColl doppelt

    public Recipe(String recipeName, String recipeTime, String recipeInstructions) {
        // Constructor for collected recipes
    }
    public Recipe() {
        // Constructor for newly saved recipe
    }

    public boolean saveRecipe (String name, String time, String ingredients, String instructions) throws Exception {
        // save recipe to file
        // void: no return
        // throws: Exception handling

        // Exceptions try catch (finally)
        try {
            writer = new OutputStreamWriter(new FileOutputStream("MyRecipes.txt", true), "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // assign to class object attributes
        recipeName = name;
        recipeTime = time;
        ingredientsMap = ingredientsToMap(ingredients);
        recipeInstructions = instructions;

        // Save contents of text areas to file
        //if (false == false) {
        if (recipeExistsInFile(name) == false) {
            writer.write("\n ### ");
            writer.write("\nName:             " + recipeName);
            writer.write("\nPreparation time: " + recipeTime);
            writer.write("\nIngredients:      " + ingredientsMap);
            writer.write("\nInstructions:     " + recipeInstructions);
            writer.write("\n");
            writer.close();
            //addRecipe(this); // add recipe to collection
            return true;
        } else {
            return false;
        }
    }

    // Private methods
    private Map<String,String> ingredientsToMap(String ingredients) {
        // Parse ingredients String and sort to a map with name and number

        String[] splitIngredients = ingredients.split("\n");
        numberIngredients = splitIngredients.length;
        String ingredientAmount;
        String ingredientName;

        for (int i = 0; i < numberIngredients; i++) {
            // split amount and name of ingredient
            ingredientAmount = splitIngredients[i].split("\s")[0];
            ingredientName = splitIngredients[i].split("\s")[1];
            ingredientsMap.put(ingredientName, ingredientAmount); // put method from Collection (key,value)
        }

        /*
        // Set is from java.util and extends collection interface, it contains methods inherited from Collection interface
        Set set = ingredientsMap.entrySet();
        Iterator i = set.iterator();  // from Iterable interface that is

        // while
        while(i.hasNext()) { // hasNext Java Scanner class,
            Map.Entry m = (Map.Entry)i.next();
            System.out.print(m.getKey() + ": " + m.getValue());
        }
        // for each (iterable)
        for (Map.Entry m : ingredientsMap.entrySet()){
            System.out.println("for each " + m.getValue() + " " + m.getKey());
        }*/

        //for (var m : ingredientsMap.entrySet()){  // var: not specified type
        //    System.out.println(m.getKey() + " " + m.getValue());
        //}
        return ingredientsMap;
    }

    // Setter und Getter
    public void setRecipeName(String name) { recipeName = name; }
    public void setRecipeTime(String time) { recipeTime = time; }
    public void setRecipeInstructions(String instructions) { recipeInstructions = instructions; }
    public void setIngredientsMap(Map<String,String> ingredients) { ingredientsMap = ingredients; }
    public String getRecipeName() { return recipeName; }
    public String getRecipeTime() { return recipeTime; }
    public String getRecipeInstructions() { return recipeInstructions; }
    public Map<String,String> getIngredientsMap() { return ingredientsMap; }



    // ? Nur einmal definieren
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
        boolean recipeContained = fileContent.contains(" " + keyword + " ");
        if (recipeContained == true) {System.out.println("Recipe \"" + keyword + "\" is contained.");}

        return recipeContained;
    }
}