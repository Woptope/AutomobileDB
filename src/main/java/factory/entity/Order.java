package factory.entity;

import java.util.Date;

public class Order {

    private int id_order;
    private int id_client;
    private int id_employee;
    private int id_auto;
    private Date contract_date;
    private String payment_type;


    public Order() {
    }

    public Order(int id_order, int id_client, int id_employee, int id_auto, Date contract_date, String payment_type) {
        this.id_order = id_order;
        this.id_client = id_client;
        this.id_employee = id_employee;
        this.id_auto = id_auto;
        this.contract_date = contract_date;
        this.payment_type = payment_type;
    }

    public Order(int id_client, int id_employee, int id_auto, Date contract_date, String payment_type){
        this.id_client = id_client;
        this.id_employee = id_employee;
        this.id_auto = id_auto;
        this.contract_date = contract_date;
        this.payment_type =payment_type;
    }


    public int getId_order() {
        return id_order;
    }

    public void setId_order(int Id_ord) {
        this.id_order = Id_ord;
    }

    public int getId_employee() {
        return id_employee;
    }

    public void setId_employee(int Id_employee) {
        this.id_employee = Id_employee;
    }

    public int getId_auto() {
        return id_auto;
    }

    public void setId_auto(int Id_auto) {
        this.id_auto = Id_auto;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int Id_client) {
        this.id_client = Id_client;
    }

    public Date getContract_date() {
        return contract_date;
    }

    public void setContract_date(Date Contract_date) {
        this.contract_date = Contract_date;
    }

    public String getPayment_type() {
        return payment_type;
    }

    public void setPayment_type(String Payment_type) {
        this.payment_type = Payment_type;
    }
}
