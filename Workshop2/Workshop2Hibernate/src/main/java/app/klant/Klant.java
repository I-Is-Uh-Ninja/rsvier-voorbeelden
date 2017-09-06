/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.klant;

import app.account.Account;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="klant")
public class Klant {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    
    private String voornaam;
    
    private String tussenvoegsel;
    
    private String achternaam;
    
    @OneToOne(mappedBy="klant", fetch=FetchType.LAZY) //De account-tabel heeft de foreign key voor klant, deze kant is dus non-owning
    private Account account;
    
    public Klant(){
        
    }

    public Klant(String voornaam, String tussenvoegsel, String achternaam) {
        this.voornaam = voornaam;
        this.tussenvoegsel = tussenvoegsel;
        this.achternaam = achternaam;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    public String getTussenvoegsel() {
        return tussenvoegsel;
    }

    public void setTussenvoegsel(String tussenvoegsel) {
        this.tussenvoegsel = tussenvoegsel;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
    
    public String getNaam(){
        if(tussenvoegsel == null){
            return voornaam + " " + achternaam;
        }
        return voornaam + " " + tussenvoegsel + " " + achternaam;
    }
    
    @Override
    public boolean equals(Object other){
        if(other == null) return false;
        if(other instanceof Klant){
            Klant klant = (Klant) other;
            return klant.getId() == this.getId();
        }
        else return false;
    }
    
}
