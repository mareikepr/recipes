package domain;

public class RecipeIngredient {

    private final String amount;
    private final String name;

    public RecipeIngredient(String name, String amount) {
        this.amount = amount;
        this.name = name;
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
