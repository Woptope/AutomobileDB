package factory.entity;

public class Client {

    private int id_client;
    private String name;
    private String surname;
    private String patronymic;
    private int passport;
    private String telephone;

    public Client() { }

    public  Client(String name, String surname, String patronymic, int passport, String telephone){
        this.name =name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.passport =passport;
        this.telephone = telephone;
    }
    public  Client(int id_client, String name, String surname, String patronymic, int passport, String telephone){
        this.id_client = id_client;
        this.name =name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.passport =passport;
        this.telephone = telephone;
    }

    public int getId_client() {
        return id_client;
    }
    public void setId_client(int Id_client) {
        this.id_client = Id_client;
    }

    public String getName() {
        return name;
    }
    public void setName(String Name) {
        this.name = Name;
    }

    public String getSurname() {
        return surname;
    }
    public void setSurname(String Surname) {
        this.surname = Surname;
    }

    public String getPatronymic() {
        return patronymic;
    }
    public void setPatronymic(String Patronymic) {
        this.patronymic = Patronymic;
    }


    public String getTelephone() {
        return telephone;
    }
    public void setTelephone(String Telephone) {
        this.telephone = Telephone;
    }
    public int getPassport() {
        return passport;
    }

    public void setPassport(int Passport) {
        this.passport = Passport;
    }
}
