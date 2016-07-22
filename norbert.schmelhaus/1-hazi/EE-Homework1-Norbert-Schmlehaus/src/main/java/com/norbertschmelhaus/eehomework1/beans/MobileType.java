package com.norbertschmelhaus.eehomework1.beans;

import com.norbertschmelhaus.eehomework1.constraint.Manufacturer;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author norbeee
 */
@Manufacturer
public class MobileType {

    @NotNull
    @Size(min = 36, max = 36)
    private String id = "ddbb07d5-eafa-4565-8886-648c34d61322";
    @NotNull
    private ManufacturerEnum manufacturer;
    @NotNull
    @Size(min = 3)
    private String type;
    @NotNull
    @Min(1)
    private int price;
    @NotNull
    private Coin coin;
    @NotNull
    private Color color;
    
    public MobileType() {
        //Empty constructor
    }

    public MobileType(ManufacturerEnum manufacturer, String type, int price, Coin coin, Color color) {
        this.manufacturer = manufacturer;
        this.type = type;
        this.price = price;
        this.coin = coin;
        this.color = color;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ManufacturerEnum getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(ManufacturerEnum manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Coin getCoin() {
        return coin;
    }

    public void setCoin(Coin coin) {
        this.coin = coin;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;

        if (this.getClass() != obj.getClass())
            return false;
        
        MobileType mt = (MobileType) obj;
        int size = 0;
        boolean isTrue = false;
        if (mt.manufacturer.equals(this.manufacturer)) {
            size++;
        }
        if (mt.type.equals(this.type)) {
            size++;
        }
        if (mt.price == this.price) {
            size++;
        }
        if (mt.coin.equals(this.coin)) {
            size++;
        }
        if (mt.color.equals(this.color)) {
            size++;
        }
        if (size == 5) {
            isTrue = true;
        }
        return isTrue;
    }
    
    @Override
    public int hashCode() {
        return 1;
    }
}
