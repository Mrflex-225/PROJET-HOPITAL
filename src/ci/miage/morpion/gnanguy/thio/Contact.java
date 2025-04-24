package ci.miage.morpion.gnanguy.thio;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Contact {
    private SimpleStringProperty name = new SimpleStringProperty("");
    private SimpleStringProperty tel = new SimpleStringProperty("");

    public Contact(String name, String tel) {
        this.name = new SimpleStringProperty(name);
        this.tel = new SimpleStringProperty(tel);
    }



    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public String  getTel() {
        return tel.get();
    }

    public void setTel(String Tel) {
        this.tel.set(Tel);
    }

    public SimpleStringProperty TelProperty() {
        return tel;
    }
}
