package domain;

public class RecipeIngredient {

    private final String amount;
    private final String name;

    public RecipeIngredient(String name, String amount) {
        this.name = name;
        this.amount = amount;

    }

    public String getName() {
        return name;
    }

    public String getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return " " + amount +
                " " + name;
    }
}
