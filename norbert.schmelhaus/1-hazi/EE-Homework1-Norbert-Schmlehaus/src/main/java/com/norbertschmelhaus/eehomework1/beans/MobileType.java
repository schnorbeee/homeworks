package com.norbertschmelhaus.eehomework1.beans;

import com.norbertschmelhaus.eehomework1.constraint.Manufacturer;
import java.util.UUID;
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
    private final String id = UUID.randomUUID().toString();
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
}
