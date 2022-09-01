import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

// Event Handler class "GUI": either implements ActionListener or extends class that implements ActionListener interface
public class GUI implements ActionListener {
    // Start menu frame
    JFrame myFrame = new JFrame("Start menu - Recipes");
    JPanel myPanel = new JPanel();
    JLabel myLabel = new JLabel();
    JButton myStartButton = new JButton("Recipes!");

    // Frame of collected recipes
    JFrame myRecipeFrame = new JFrame("Recipes");
    JPanel myRecipePanel = new JPanel();
    JLabel myRecipeLabel = new JLabel();
    JButton myAddButton = new JButton("Add new recipe");

    JFrame myAddFrame = new JFrame("Add recipe");
    JPanel myAddPanel = new JPanel();
    JPanel controlPanel = new JPanel();
    JLabel myAddLabel1 = new JLabel();
    JLabel myAddLabel2 = new JLabel();
    JLabel myAddLabel3 = new JLabel();
    JLabel myAddLabel4 = new JLabel();
    JLabel myAddLabel5 = new JLabel();
    JLabel myAddLabel6 = new JLabel();
    JButton mySaveButton = new JButton("Save new recipe");

    // Textfield for new recipes

    JTextArea myTextName = new JTextArea(2,30);
    JTextArea myTextTime = new JTextArea(2,30);
    JTextArea myTextIngredients = new JTextArea(40,30);
    JTextArea myTextInstructions = new JTextArea(40,30);

    JScrollPane myScrollpaneIngredients; // scrollable textfield
    JScrollPane myScrollpaneInstructions;

    public GUI() { // Constructor: wird ausgeführt wenn neues objekt gemacht wird

        // Start menu
        myFrame.add(myPanel);
        myPanel.add(myLabel);
        myPanel.add(myStartButton);
        myStartButton.addActionListener(this);
        myFrame.setSize(400, 400);
        myFrame.setVisible(true);

        // Recipe window
        myRecipeFrame.add(myRecipePanel);
        myRecipePanel.add(myRecipeLabel);
        myRecipePanel.add(myAddButton);
        myRecipeLabel.setText(("Recipe"));
        myAddButton.addActionListener(this);
        myRecipeFrame.setSize(600, 400);

        // Add recipe window
        myAddFrame.setSize(400, 600);
        myAddPanel.setSize(400,600);

        java.awt.GridBagLayout layout = new java.awt.GridBagLayout();
        myAddPanel.setLayout(layout);
        java.awt.GridBagConstraints gbc = new java.awt.GridBagConstraints();

        myAddLabel1.setText("Enter recipe: \n");
        myAddLabel2.setText("Recipe name: \n");
        myAddLabel3.setText("Time: \n");
        myAddLabel4.setText("Ingredients (Format \"20g Mehl\"): ");
        myAddLabel5.setText("Instructions: \n");

        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.ipady = 50;
        gbc.ipadx = 50;
        gbc.fill = java.awt.GridBagConstraints.BOTH;
        gbc.insets = new Insets(2,2, 2, 2);

        gbc.gridx = 0;
        gbc.gridy = 1;
        myAddPanel.add(myAddLabel2, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        myAddPanel.add(myAddLabel3, gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        myAddPanel.add(myAddLabel4, gbc);
        gbc.gridx = 0;
        gbc.gridy = 4;
        myAddPanel.add(myAddLabel5, gbc);
        gbc.gridx = 0;
        gbc.gridy = 5;
        myAddPanel.add(myAddLabel6, gbc);

        myScrollpaneIngredients = new JScrollPane(myTextIngredients);
        myScrollpaneInstructions = new JScrollPane(myTextInstructions);

        gbc.gridx = 1;
        gbc.gridy = 1;
        myAddPanel.add(myTextName, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        myAddPanel.add(myTextTime, gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        myAddPanel.add(myScrollpaneIngredients, gbc);
        //myAddPanel.add(myTextIngredients, gbc);
        gbc.gridx = 1;
        gbc.gridy = 4;
        myAddPanel.add(myScrollpaneInstructions, gbc);
        //myAddPanel.add(myTextInstructions, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        myAddPanel.add(mySaveButton, gbc);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        myAddPanel.add(myAddLabel1, gbc);
        mySaveButton.addActionListener(this);

        myAddFrame.add(myAddPanel);

        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myRecipeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myAddFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override // annotation: Information für compiler, dass Methode der Oberklasse wird (als Absicherung, nicht erforderlich)
    public void actionPerformed(ActionEvent e) {
        // Method for reaction to action event

        // if else or switch case
        if (e.getSource() == this.myStartButton) {

            myFrame.setVisible(false);
            myRecipeFrame.setVisible(true);

            //myRecipeLabel.setText(("Start button wurde betätigt"));

        } else if (e.getSource() == this.myAddButton) {

            myFrame.setVisible(false);
            myAddFrame.setVisible(true);

        } else if (e.getSource() == this.mySaveButton) {
            // File to save and add recipes
            // New instance of class Recipe
            System.out.println("call save");

            Recipe myRecipe = new Recipe();

            String name = myTextName.getText();
            String time = myTextTime.getText();
            String ingredients = myTextIngredients.getText();
            String instructions = myTextInstructions.getText();

            try {
                boolean saved = myRecipe.saveRecipe(name, time, ingredients, instructions);
                System.out.println(saved);

                if (saved == true) {
                    myAddLabel6.setText(("Recipe saved."));
                    mySaveButton.setVisible(false);
                } else {
                    myAddLabel6.setText(("Recipe already exists."));
                }
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
