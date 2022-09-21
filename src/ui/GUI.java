package ui;
import domain.RecipeService;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class GUI {

    private RecipeService recipeService;

    // Start menu frame
    JFrame myFrame = new JFrame("Start menu - Recipes");
    JPanel myPanel = new JPanel();
    JLabel myLabel = new JLabel();
    JButton myStartButton = new JButton("Recipes!");

    // Frame of collected recipes
    JFrame myRecipeFrame;

    JButton myAddButton = new JButton("Add new recipe");

    JFrame myAddFrame = new JFrame("Add recipe");
    JPanel myAddPanel = new JPanel();
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

    JScrollPane myScrollpaneIngredients;
    JScrollPane myScrollpaneInstructions;

    public GUI(RecipeService recipeService) {

        this.recipeService = recipeService;

        // Start menu
        myFrame.add(myPanel);
        myPanel.add(myLabel);
        myPanel.add(myStartButton);
        //myStartButton.addActionListener(new StartButtonActionListener());

        // Options
        myStartButton.addActionListener(this::actionPerformed);
        myFrame.setSize(400, 400);
        myFrame.setVisible(true);

        // Recipe window
        myRecipeFrame = makeNewRecipeFrame(recipeService);

        // With new class:
        //myAddButton.addActionListener(new AddButtonActionListener());
        // Lambda expression
        //myAddButton.addActionListener( a -> addAction());
        //myAddButton.addActionListener( this::addAction());
        // Anonyme Klasse:
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addAction();
            }
        };
        myAddButton.addActionListener(al);


        // Add recipe window
        myAddFrame.setSize(400, 600);
        myAddFrame.add(myAddPanel);
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
        gbc.gridx = 1;
        gbc.gridy = 4;
        myAddPanel.add(myScrollpaneInstructions, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        myAddPanel.add(mySaveButton, gbc);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        myAddPanel.add(myAddLabel1, gbc);
        //mySaveButton.addActionListener(new SaveButtonActionListener());
        mySaveButton.addActionListener( a -> saveAction());

        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myAddFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // Options:
    private void startActionLambda(ActionEvent e) {
        myFrame.setVisible(false);
        myRecipeFrame.setVisible(true);
    }
    public void actionPerformed(ActionEvent e){
        startAction();
    }
    private void startAction() {
        myFrame.setVisible(false);
        myRecipeFrame.setVisible(true);
    }

    public void addAction() {
        myFrame.setVisible(false);
        myAddFrame.setVisible(true);
    }

    public void saveAction() {

        String name = myTextName.getText();
        String time = myTextTime.getText();
        String ingredients = myTextIngredients.getText();
        String instructions = myTextInstructions.getText();

        recipeService.addRecipeToCollectionAndFile(name, time, ingredients, instructions);
        recipeService.printCollection();
        myAddFrame.setVisible(false);

        // myRecipeFrame erneuern
        myRecipeFrame.setVisible(false);
        myRecipeFrame = makeNewRecipeFrame(recipeService);
        myRecipeFrame.setVisible(true);

    }

    /*
    private class StartButtonActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            startAction();
        }
    }

    private class AddButtonActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            addAction();
        }
    }

    private class SaveButtonActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                saveAction();
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
    */

    private JFrame makeNewRecipeFrame(RecipeService recipeService) {

        JFrame myRecipeFrame = new JFrame("Recipes");
        JPanel myRecipePanel = new JPanel();
        JLabel myRecipeLabel = new JLabel();

        myRecipeFrame.setSize(600, 400);

        myRecipeFrame.add(myRecipePanel);
        myRecipePanel.add(myRecipeLabel);
        String [] recipeNamesListStringArray = recipeService.getRecipeNames().toArray(String[]::new);
        JList<String> myRecipeNamesList = new JList<>(recipeNamesListStringArray);
        myRecipePanel.add(myRecipeNamesList);
        myRecipePanel.add(myAddButton);
        myRecipeLabel.setText(("Recipe"));

        myRecipeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        return myRecipeFrame;
    }
}