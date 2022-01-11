package factory.entity;

public class Employee {
    private int id_employee;
    private String name;
    private String surname;
    private String patronymic;
    private String position;
    private String telephone;

    public Employee() {
    }

    public Employee(String name, String surname, String patronymic, String position, String telephone) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.position = position;
        this.telephone = telephone;
    }

    public Employee(int id_employee, String name, String surname, String patronymic, String position, String telephone) {
        this.id_employee = id_employee;
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.position = position;
        this.telephone = telephone;
    }

    public int getId_employee() {
        return id_employee;
    }

    public void setId_employee(int Id_employee) {
        this.id_employee = Id_employee;
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

    public String getPosition() {
        return position;
    }

    public void setPosition(String Position) {
        this.position = Position;
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
}
