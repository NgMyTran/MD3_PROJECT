package entity;

import presentation.util.InputMethods;

import java.io.Serializable;

public class Category implements IManagement, Serializable {
    private static int autoId = 0;
    private int id;
    private String name;
    private boolean status;

    public Category(int id, String name, boolean status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public Category() {
        this.id = ++autoId;
        this.status = true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public void inputData() {
        System.out.println("Nhập tên danh mục :");
        this.name = InputMethods.getString();
    }

    @Override
    public void displayData() {
        System.out.printf("|ID : %-4s | Name: %-15s | Status : %10s |\n", id, name, status ? "Active" : "InActive");
    }
}
