/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import app.account.AccountController;
import app.home.HomeController;
import app.klant.KlantController;
import java.util.Scanner;

/**
 *
 * @author Ian
 */
public class Dispatcher {

    private static Dispatcher instance;
    private final HomeController homeController;
    private final KlantController klantController;
    private final AccountController accountController;
    private final Scanner input;
    
    private Dispatcher(){
        input = new Scanner(System.in);
        homeController = new HomeController(input);
        klantController = new KlantController(input);
        accountController = new AccountController(input);
    }
    
    public static void main(String[] args) {
        instance = new Dispatcher();
        instance.homeController.start();
    }
    
    public static HomeController getHomeController(){
        return instance.homeController;
    }
    
    public static KlantController getKlantController(){
        return instance.klantController;
    }
    
    public static AccountController getAccountController(){
        return instance.accountController;
    }
    
}
