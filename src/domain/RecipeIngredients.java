package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class RecipeIngredients {

    private final List<RecipeIngredient> ingredientsList;

    public RecipeIngredients() {
        ingredientsList = new ArrayList<>();
    }

    public void add(RecipeIngredient ingredient) {
        ingredientsList.add(ingredient);
    }

    public Stream<RecipeIngredient> stream() {
        return ingredientsList.stream();
    }

    @Override
    public String toString() {
        return ingredientsList.toString();
    }

}
