package presentation.controller.admin;

import presentation.run.Main;
import presentation.util.InputMethods;
import bussiness.classes.CategoryBusiness;
import bussiness.classes.ProductBussiness;
import entity.Product;

import java.util.List;

public class ProductManager {
    public static CategoryBusiness categoryBusiness = new CategoryBusiness();
    public static ProductBussiness productBussiness = new ProductBussiness();

    public static void productManagementMenu() {
        while (true) {
            System.out.println("╔═══════════════════════════════════════════════╗");
            System.out.println("║ ------------------Manage Product----------------║");
            System.out.println("╠═══════════════════════════════════════════════╣");
            System.out.println("║ 1. Thêm mới                                   ║");
            System.out.println("║ 2. Hiển thị                                   ║");
            System.out.println("║ 3. Sửa tên                                    ║");
            System.out.println("║ 4. Xóa                                        ║");
            System.out.println("║ 5. Tìm kiếm                                   ║");
            System.out.println("║ 6. Quay lại                                   ║");
            System.out.println("║ Lựa chọn của bạn :                            ║");
            System.out.println("╚═══════════════════════════════════════════════╝");
            byte choice = InputMethods.getByte();
            switch (choice) {
                case 1:
                    addNewProduct();
                    break;
                case 2:
                    showProductList();
                    break;
                case 3:
                    editProduct();
                    break;
                case 4:
                    deleteProduct();
                    break;
                case 5:
                    findProductByName();
                    break;
                case 6:
                    Main.menuAdmin();
                    break;
                default:
                    System.err.println("Lựa chọn ko chính xác , vui lòng nhập lại");
                    break;
            }
//            if (choice == 6) {
//                break;
//            }
        }
    }

//
//    private static void addNewProduct() {
//        if (categoryBusiness.findAll().isEmpty()) {
//            System.err.println("Chưa có danh mục , chọn '6. Quay lại' để  thêm danh mục trước khi thêm sản phẩm");
//            return;
//        }
//        System.out.println("Nhập số lượng cần thêm mới");
//        byte n = InputMethods.getByte();
//        for (int i = 0; i < n; i++) {
//            System.out.println("Nhập thông tin cho sp thứ :" + (i + 1));
//            Product newProduct = new Product(); // chứa logic tự tăng
//            newProduct.inputData(); // cho nhập thông tin
//            productBussiness.create(newProduct); // luu lại
//        }
//        // thông báo thành công
//        System.out.println("Đã thêm mới thành cong " + n + " sản phẩm !");
//    }
private static void addNewProduct() {
    if (categoryBusiness.findAll().isEmpty()) {
        System.err.println("Chưa có danh mục. Chọn '6. Quay lại' để thêm danh mục trước khi thêm sản phẩm.");
        return;
    }
    System.out.println("Nhập số lượng sản phẩm cần thêm mới:");
    byte n = InputMethods.getByte();
    for (int i = 0; i < n; i++) {
        System.out.println("Nhập thông tin cho sản phẩm thứ " + (i + 1) + ":");
        Product newProduct = new Product(); // Logic tự tăng ID
        newProduct.inputData(); // Nhập thông tin cho sản phẩm
        productBussiness.create(newProduct); // Lưu sản phẩm vào cơ sở dữ liệu
    }
    // Thông báo thành công
    System.out.println("Đã thêm mới thành công " + n + " sản phẩm!");
}

    public static void showProductList() {
        // lấy ra danh sách
        List<Product> products = productBussiness.findAll();
        if (products.isEmpty()) {
            System.err.println("Danh sách trống !");
        } else {
            System.out.println("-------- Danh sách sản phẩm --------");
            for (Product pro : products) {
                pro.displayData();
            }
        }
    }

    private static void deleteProduct() {
        System.out.println("Nhập mã sản phẩm  cần xóa ");
        int proId = InputMethods.getInteger();
        // kiểm tra tồn tại
        if (productBussiness.findById(proId) == null) {
            System.err.println("id không tồn tại");
        } else {
            productBussiness.deleteById(proId);
            System.out.println(" đã xóa thành công");
        }
    }

    private static void editProduct() {
        System.out.println("Nhập mã san pham cần sưa ");
        int proId = InputMethods.getInteger();
        Product proEdit = productBussiness.findById(proId);
        if (proEdit == null) {
            System.out.println(" SP k ton tai ");
        } else {
            proEdit.inputData();
            productBussiness.update(proEdit);
            System.out.println("Cập nhật thành công");
        }
    }

    private static void findProductByName() {
        System.out.println("Nhập tên sản phẩm cần tìm: ");
        String searchName = InputMethods.getString();
        Product foundProduct = productBussiness.findByName(searchName);
        if (foundProduct != null) {
            System.out.println("Sản phẩm được tìm thấy:");
            foundProduct.displayData();
        } else {
            System.out.println("Không tìm thấy sản phẩm có tên '" + searchName + "'");
        }
    }
};
