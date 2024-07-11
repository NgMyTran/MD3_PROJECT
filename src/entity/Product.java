package entity;

import bussiness.classes.CategoryBusiness;
import bussiness.interfaces.ICategoryDesign;
import presentation.util.InputMethods;
import java.io.Serializable;
import static bussiness.classes.CategoryBusiness.categories;

public class Product implements IManagement, Serializable {
//   private static CategoryBusiness categoryBusiness = new CategoryBusiness();
    private static int autoId = 0;
    private int id;
    private String name;
    private double price;
    private String descriptions;
    private int quantity;
    private boolean status;
    private int categoryId; // quan hệ hợp thanh

//    static {
//        categories = categoryBusiness.findAll(); // Khởi tạo categories từ danh sách danh mục có sẵn
//    }

    public Product() {
        id = ++autoId;
        status = true;
    }

    public Product(int id, String name, double price, String descriptions, int quantity, boolean status, int categoryId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.descriptions = descriptions;
        this.quantity = quantity;
        this.status = status;
        this.categoryId = categoryId;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Override
//    public void inputData() {
//        System.out.println("Nhập tên sản phẩm");
//        name = InputMethods.getString();
//        System.out.println("Nhập giá");
//        price = InputMethods.getDouble();
//        System.out.println(" Nhập mô tả");
//        descriptions = InputMethods.getString();
//        System.out.println("Nhập số lượng");
//        quantity = InputMethods.getInteger();
//        // chọn danh mục
//        System.out.println("Danh sách danh mục");
//        for (int i = 1; i <= categories.size(); i++) {
//            System.out.printf("|STT : %-3s | Name : %-15s |\n", i, categories.get(i - 1).getName());
//        }
//        System.out.println("Moi ban chon danh muc cho sp");
//        while (true) {
//            int index = InputMethods.getInteger();
//            if (index >= 1 && index <= categories.size()) {
//                this.categoryId = categories.get(index - 1).getId();
//                break;
//            } else {
//                System.err.println("Nhap khong chinh xac , vui long nhap lai");
//            }
//        }
//    }
    public void inputData() {
        System.out.println("Nhập tên sản phẩm:");
        name = InputMethods.getString();
        System.out.println("Nhập giá:");
        price = InputMethods.getDouble();
        System.out.println("Nhập mô tả:");
        descriptions = InputMethods.getString();
        System.out.println("Nhập số lượng:");
        quantity = InputMethods.getInteger();
        // Chọn danh mục
        System.out.println("Danh sách danh mục:");
        for (int i = 1; i <= categories.size(); i++) {
            System.out.printf("| STT: %-3s | Name: %-15s |\n", i, categories.get(i - 1).getName());
        }
        System.out.println("Nhập số thứ tự danh mục:");
        int categoryIndex = InputMethods.getInteger() - 1; // Chuyển đổi từ STT thành chỉ số danh sách
        if (categoryIndex >= 0 && categoryIndex < categories.size()) {
            categoryId = categories.get(categoryIndex).getId();
        } else {
            System.err.println("Danh mục không hợp lệ.");
            return;
        }
    }

    @Override
    public void displayData() {
        System.out.printf("|ID : %-4s | Name: %-20s | Price : %-8s | Quantity : %-5s | CategoryName : %-15s | Status : %10s |\n", id, name, price, quantity, getCategoryName(), status ? "Active" : "InActive");
    }

    private String getCategoryName() {
        ICategoryDesign categoryDesign = new CategoryBusiness();
        Category cat = categoryDesign.findById(categoryId);
        if (cat == null) {
            throw new RuntimeException("Category không có id: " + id);
        }
        return cat.getName();
    }


}

