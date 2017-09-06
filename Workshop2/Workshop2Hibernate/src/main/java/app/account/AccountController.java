/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.account;

import app.DaoFactory;
import app.Dispatcher;
import app.klant.Klant;
import java.util.Arrays;
import java.util.Scanner;
import java.util.TreeMap;

/**
 *
 * @author Ian
 */
public class AccountController {
    
    private final AccountDao accountDao;
    private final AccountView accountView;
    
    public AccountController(Scanner in){
        accountDao = (AccountDao) DaoFactory.getInstance().getDao("account");
        accountView = new AccountView(this, in);
    }
    
    public void start(){
        accountView.printStartMenu();
    }
    
    public void listAlleAccounts(){
        accountView.printAlleAccounts(accountDao.findAll());
    }
    
    public void listAccountsKeuzeMenu(){
        accountView.printAlleAccounts(accountDao.findAll());
        accountView.printAccountsKeuzeMenu();
    }
    
    public void createAccount(){
        Account account = new Account();
        setKlant(account);
        setGebruikersnaam(account);
        setWachtwoord(account);
        setAccountType(account);
        if(accountView.verifyAccountGegevens(account)){
            accountDao.create(account);
            if(account.getId() > 0){
                accountView.printSuccess();
            }
            else {
                accountView.printFailure("aanmaken account");
            }
        }
        start();
    }
    
    public void createAccount(Klant klant){
        Account account = new Account();
        account.setKlant(klant);
        setGebruikersnaam(account);
        setWachtwoord(account);
        setAccountType(account);
        if(accountView.verifyAccountGegevens(account)){
            accountDao.create(account);
            if(account.getId() > 0){
                accountView.printSuccess();
            }
            else {
                accountView.printFailure("aanmaken account");
            }
        }
        Dispatcher.getKlantController().start();
    }
    
    void setGebruikersnaam(Account account){
        account.setGebruikersnaam(accountView.vraagGebruikersnaam());
    }
    
    void setWachtwoord(Account account){
        account.setWachtwoord(accountView.vraagWachtwoord());
    }
    
    void setAccountType(Account account){
        TreeMap<Integer, String> types = new TreeMap<>();
        Arrays.asList(AccountType.values()).forEach(at -> types.put(at.ordinal(), at.toString()));
        int type = accountView.vraagAccountType(types);
        account.setAccountType(AccountType.valueOf(types.get(type)));
    }
    
    void setKlant(Account account){
        Klant klant = Dispatcher.getKlantController().selectKlantForAccount();
        account.setKlant(klant);
    }
    
    void goToHoofdMenu(){
        Dispatcher.getHomeController().start();
    }
}
