package dataManagment;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Lens {
    private final IntegerProperty id;
    private final SimpleStringProperty lensName;
    private final IntegerProperty price;
    private final IntegerProperty focusDistance;
    private final SimpleStringProperty suitableForCanon;
    private final SimpleStringProperty suitableForNikon;

    public Lens (int id, String lensName, int price,
                 int focusDistance, String suitableForCanon,
                 String suitableForNikon){
        this.id = new SimpleIntegerProperty(id);
        this.lensName = new SimpleStringProperty(lensName);
        this.price = new SimpleIntegerProperty(price);
        this.focusDistance = new SimpleIntegerProperty(focusDistance);
        this.suitableForCanon = new SimpleStringProperty(suitableForCanon);
        this.suitableForNikon = new SimpleStringProperty(suitableForNikon);
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public int getId() {
        return id.get();
    }

    public SimpleStringProperty lensNameProperty() {
        return lensName;
    }

    public String getLensName() {
        return lensName.get();
    }

    public IntegerProperty priceProperty() {
        return price;
    }

    public IntegerProperty focusDistanceProperty() {
        return focusDistance;
    }

    public SimpleStringProperty suitableForCanonProperty() {
        return suitableForCanon;
    }

    public SimpleStringProperty suitableForNikonProperty() {
        return suitableForNikon;
    }

}
