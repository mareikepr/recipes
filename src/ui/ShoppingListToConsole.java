package ui;

import domain.ShoppingListServiceInterface;

import java.util.List;

public class ShoppingListToConsole implements ShoppingListServiceInterface {

    @Override
    public void printShoppingList(List<String> shoppingList) {

        System.out.println("\n Shopping list: " + shoppingList);
    }
}
