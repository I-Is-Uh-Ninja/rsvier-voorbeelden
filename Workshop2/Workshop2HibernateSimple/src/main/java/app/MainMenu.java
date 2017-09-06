/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import app.account.AccountController;
import app.klant.KlantController;
import java.util.Scanner;

/**
 *
 * @author Ian
 */
public class MainMenu {

    private static MainMenu instance;
    private final Scanner input;
    private MainMenuView view;
    private KlantController klantController;
    private AccountController accountController;
    
    private MainMenu(){
        input = new Scanner(System.in);
        view = new MainMenuView(this, input);
        klantController = new KlantController(input);
        accountController = new AccountController(input);
    }
    
    public static void main(String[] args) {
        instance = new MainMenu();
        instance.start();
    }
    
    void start(){
        view.printMainMenu();
    }
    
    void stop(){
        System.exit(0);
    }
    
    void goToKlantMenu(){
        klantController.start();
    }
    
    void goToAccountMenu(){
        accountController.start();
    }
    
    public static KlantController getKlantController(){
        return instance.klantController;
    }
    
    public static AccountController getAccountController(){
        return instance.accountController;
    }
    
    public static void goToMainMenu(){
        instance.start();
    }
    
}
