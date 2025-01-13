/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package esercizi.memorygamevero;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import javax.swing.*;
import javax.swing.Timer;   
/**
 *
 * @author yummycovivo
 */
public class GameLogic {
    private JButton[] gButtons = new JButton[6];
    private ImageIcon[] buttonIcons = new ImageIcon[6];
    private int gButtonsClicked = 0;
    private JButton firstButton;
    private JButton secondButton;
    private ImageIcon ICON_DESCRIPTION1, ICON_DESCRIPTION2;
    
    private boolean WINCONDITION;

    public GameLogic(JButton[] buttons) {
        this.gButtons = buttons;
        InitializeButtons();
        SetAllButtonsDEFAULT();
    }

    private void SetAllButtonsDEFAULT() {
        for (JButton button : gButtons) {
            button.setBackground(Color.BLACK);
            button.setText(null);
            button.setIcon(null);
        }
    }

    private void InitializeButtons() {
        String[] imageURLs = {"/resources/ciliega.png", "/resources/ciliega.png",
                              "/resources/mela.png", "/resources/mela.png",
                              "/resources/banana.png", "/resources/banana.png"};
        ArrayList<String> List = new ArrayList<>(Arrays.asList(imageURLs));
        Collections.shuffle(List);

        for (int i = 0; i < gButtons.length; i++) {
            buttonIcons[i] = new ImageIcon(getClass().getResource(List.get(i)));
        }
    }

    public void ButtonClicked(int buttonN) {
        JButton clickedButton = gButtons[buttonN];
        
        clickedButton.setBackground(Color.WHITE);
        
        if (gButtonsClicked == 0) {
            firstButton = clickedButton;
            ICON_DESCRIPTION1 = buttonIcons[buttonN];
            clickedButton.setIcon(ICON_DESCRIPTION1); 
            gButtonsClicked = 1;
        } else if (gButtonsClicked == 1) {
            secondButton = clickedButton;
            if (secondButton == firstButton) {
                gButtonsClicked = 1;
                
            }else{
            ICON_DESCRIPTION2 = buttonIcons[buttonN];
            clickedButton.setIcon(ICON_DESCRIPTION2);
            gButtonsClicked = 2;
            CheckForMatch();
            }
        }
    }

    private void CheckForMatch() {
        if (ICON_DESCRIPTION1.getDescription().equals(ICON_DESCRIPTION2.getDescription())) {
            firstButton.setEnabled(false);
            secondButton.setEnabled(false);
            SelectionReset();
            WinCondition();
        } else {
            System.out.println("Non uguale (DEBUG)");
            Timer timer = new Timer(500, e -> {
                resetButton(firstButton);
                resetButton(secondButton);
                SelectionReset();
            });
            timer.setRepeats(false);
            timer.start();
        }
    }

    private void SelectionReset() {
        firstButton = null;
        secondButton = null;
        gButtonsClicked = 0;
    }

    private void resetButton(JButton button) {
        button.setIcon(null);
        button.setBackground(Color.BLACK);
    }
    
    private void WinCondition() {
        boolean allMatched = true;
        for (JButton button : gButtons) {
            if (button.isEnabled()) {
                allMatched = false;
                break;
            }
        }
        if (allMatched) {
            WINCONDITION = true;
            JOptionPane.showMessageDialog(null, "HAI VINTO!");
        }
    }
}
