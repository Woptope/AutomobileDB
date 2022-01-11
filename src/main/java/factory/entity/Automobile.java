package factory.entity;

public class Automobile {

    private int id_auto;
    private String model;
    private String color;
    private String transmission;
    private String car_body;
    private int price;
    private int id_manufacturer;

    public Automobile() { }

    public Automobile(String model, String color, String transmission, String car_body, int price, int id_manufacturer){
        this.model = model;
        this.color = color;
        this.transmission = transmission;
        this.car_body = car_body;
        this.price = price;
        this.id_manufacturer = id_manufacturer;
    }

    public Automobile(int id_auto, String model, String color, String transmission, String car_body, int price, int id_manufacturer){
        this.id_auto = id_auto;
        this.model = model;
        this.color = color;
        this.transmission = transmission;
        this.car_body = car_body;
        this.price = price;
        this.id_manufacturer = id_manufacturer;
    }

    public int getId_auto() {
        return id_auto;
    }

    public void setId_auto(int Id_auto) {
        this.id_auto = Id_auto;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String Model) {
        this.model = Model;
}

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String Transmission) {
        this.transmission = Transmission;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String Color) {
        this.color = Color;
    }

    public String getCar_body() {
        return car_body;
    }

    public void setCar_body(String Car_body) {
        this.car_body = Car_body;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int Price) {
        this.price = Price;
    }

    public int getId_manufacturer() {
        return id_manufacturer;
    }

    public void setId_manufacturer(int Id_manufacturer) {
        this.id_manufacturer = Id_manufacturer;
    }
}
