package ci.miage.morpion.gnanguy.thio;

import javafx.beans.property.StringProperty;

public class data {
    private String name;
    private StringProperty description;

    public data(String n , StringProperty s){
        this.name=n;
        this.description=s;
    }
}
