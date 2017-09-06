/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.home;

import app.Dispatcher;
import java.util.Scanner;

/**
 *
 * @author Ian
 */
public class HomeController {
    private final HomeView view;
    
    public HomeController(Scanner in){
        view = new HomeView(this, in);
    }
    
    public void start(){
        view.printHoofdMenu();
    }
    
    void goToKlantMenu(){
        Dispatcher.getKlantController().start();
    }
    
    void goToAccountMenu(){
        Dispatcher.getAccountController().start();
    }
    
    public void stop(){
        System.exit(0);
    }
    
}
