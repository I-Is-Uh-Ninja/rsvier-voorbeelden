/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import java.util.Scanner;

/**
 *
 * @author Ian
 */
public class MainMenuView {
    
    private final Scanner input;
    private final MainMenu menuController;
    
    MainMenuView(MainMenu menu, Scanner in){
        input = in;
        menuController = menu;
    }
    
    void printMainMenu(){
        System.out.println("Type het nummer van uw keuze in.");
        System.out.println("1: Ga naar klant-menu.");
        System.out.println("2: Ga naar account-menu.");
        System.out.println("3: Afsluiten.");
        int keuze = input.nextInt();
        input.nextLine();
        switch(keuze){
            case 1:
                menuController.goToKlantMenu();
                break;
            case 2:
                menuController.goToAccountMenu();
                break;
            case 3:
                menuController.stop();
                break;
            default:
                System.out.println("Ongeldige keuze!");
                printMainMenu();
        }
    }
    
}
