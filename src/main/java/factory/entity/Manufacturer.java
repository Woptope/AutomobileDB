package factory.entity;

public class Manufacturer {
    private int id_manufacturer;
    private String title;
    private String country;
    private String city;
    private String phone;

    public Manufacturer() { }

    public Manufacturer(int id_manufacturer, String title, String country, String city, String phone) {
       this.id_manufacturer = id_manufacturer;
        this.title = title;
        this.country = country;
        this.city = city;
        this.phone = phone;
    }
    public Manufacturer(int id_manufacturer) {
        this.id_manufacturer = id_manufacturer;
    }

    public Manufacturer( String title, String country, String city, String phone) {
        this.title = title;
        this.country = country;
        this.city = city;
        this.phone = phone;
    }

    public int getId_manufacturer() {
        return id_manufacturer;
    }
    public void setId_manufacturer(int Id_manufacturer) {
        this.id_manufacturer = Id_manufacturer;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String Title) {
        this.title = Title;
    }

    public String getCountry() {
        return country;
    }
    public void setCountry(String Country) {
        this.country = Country;
    }

    public String getCity() {
        return city;
    }
    public void setCity(String City) {
        this.city = City;
    }

    public String getPhone() {
        return phone;
    }
    public void setPhone(String Phone) {
        this.phone = Phone;
    }

}
