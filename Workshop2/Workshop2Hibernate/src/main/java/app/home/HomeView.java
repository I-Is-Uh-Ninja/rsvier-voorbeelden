/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.home;

import java.util.Scanner;

/**
 *
 * @author Ian
 */
public class HomeView {
    
    private final Scanner input;
    private final HomeController homeController;
    
    public HomeView(HomeController hc, Scanner in){
        input = in;
        homeController = hc;
    }
    
    public void printHoofdMenu(){
        System.out.println("Type het nummer van uw keuze in.");
        System.out.println("1: Ga naar klant-menu.");
        System.out.println("2: Ga naar account-menu.");
        System.out.println("3: Afsluiten.");
        int keuze = input.nextInt();
        switch(keuze){
            case 1:
                homeController.goToKlantMenu();
                break;
            case 2:
                homeController.goToAccountMenu();
                break;
            case 3:
                homeController.stop();
                break;
            default:
                printOngeldigeKeuze();
                printHoofdMenu();
        }
    }
    
    public void printOngeldigeKeuze(){
        System.out.println("Ongeldige keuze!");
    }
}
