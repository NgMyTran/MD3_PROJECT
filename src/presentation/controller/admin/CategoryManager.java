package presentation.controller.admin;

import bussiness.classes.CategoryBusiness;
import bussiness.interfaces.ICategoryDesign;
import entity.Category;
import presentation.run.Main;
import presentation.util.InputMethods;

import java.util.List;

public class CategoryManager {
    private static final ICategoryDesign categoryBusiness = new CategoryBusiness();

    public static void categoryManagementMenu() {
        while (true) {
            System.out.println("╔═══════════════════════════════════════════════╗");
            System.out.println("║ ------------------Manage Category-------------║");
            System.out.println("╠═══════════════════════════════════════════════╣");
            System.out.println("║ 1. Thêm mới                                   ║");
            System.out.println("║ 2. Hiển thị                                   ║");
            System.out.println("║ 3. Sửa tên                                    ║");
            System.out.println("║ 4. Xóa                                        ║");
            System.out.println("║ 5. Quay lại                                   ║");
            System.out.println("║ Lựa chọn của bạn :                            ║");
            System.out.println("╚═══════════════════════════════════════════════╝");

            byte choice = InputMethods.getByte();
            switch (choice) {
                case 1:
                    addNewCategory();
                    break;
                case 2:
                    showCategoryList();
                    break;
                case 3:
                    editCategory();
                    break;
                case 4:
                    deleteCategory();
                    break;
                case 5:
                    Main.menuAdmin();
                    break;
                default:
                    System.err.println("Lựa chọn ko chính xác , vui lòng nhập lại");
            }
            if (choice == 5) {
                break;
            }
        }
    }


    public static void editCategory() {
        System.out.println("Nhập mã danh mục  cần sưa ");
        int catId = InputMethods.getInteger();
        Category catEdit = categoryBusiness.findById(catId);
        if (catEdit == null) {
            System.err.println("id không tồn tại");
        } else {
            catEdit.inputData();
            categoryBusiness.update(catEdit);
            System.out.println("Cập nhật thành công");
        }
    }

    public static void addNewCategory() {
        System.out.println("Nhập số lượng cần thêm mới");
        byte n = InputMethods.getByte();
        for (int i = 0; i < n; i++) {
            System.out.println("Nhập thông tin cho danh mục thứ :" + (i + 1));
            Category newCategory = new Category(); // chứa logic tự tăng
            newCategory.inputData(); // cho nhập thông tin
            categoryBusiness.create(newCategory); // luu lại
        }
        System.out.println("Đã thêm mới thành cong " + n + " dnah mục !");
    }


    public static void showCategoryList() {
        // lấy ra danh sách
        List<Category> categories = categoryBusiness.findAll();
        if (categories.isEmpty()) {
            System.err.println("Danh sách trống !");
        } else {
            System.out.println("-------- Danh sách danh mục --------");
            for (Category cat : categories) {
                cat.displayData();
            }
        }
    }

    public static void deleteCategory() {
        System.out.println("Nhập mã danh mục  cần xóa ");
        int catId = InputMethods.getInteger();

        if (categoryBusiness.findById(catId) == null) {
            System.err.println("id không tồn tại");
        } else {
            categoryBusiness.deleteById(catId);
            System.out.println(" đã xóa thành công");
        }
    }

};
