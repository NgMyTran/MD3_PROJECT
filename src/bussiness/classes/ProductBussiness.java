package bussiness.classes;

import bussiness.interfaces.IProductDesign;
//import entity.Cart;
import entity.Category;
import entity.Product;
import presentation.util.IOFile;
import bussiness.classes.CategoryBusiness;
import presentation.util.InputMethods;

import java.util.ArrayList;
import java.util.List;

    public class ProductBussiness implements IProductDesign {
        public static List<Product> products;

        public ProductBussiness() {
            products = IOFile.readFromFile(IOFile.PRODUCT_PATH);
        }

        @Override
        public Product findByName(String name) {
            for (Product pro : products) {
                if (pro.getName().equalsIgnoreCase(name)) {
                    return pro;
                }
            }
            return null; // Return null if no matching product is found
        }


        @Override
        public boolean create(Product product) {
            products.add(product);
            IOFile.writeToFile(IOFile.PRODUCT_PATH, products);
            return true;
        }

        @Override
        public List<Product> findAll() {
            return products;
        }

        @Override
        public boolean update(Product product) {
            products.set(products.indexOf(findById(product.getId())), product);
            IOFile.writeToFile(IOFile.PRODUCT_PATH, products);
            return true;
        }

        @Override
        public boolean deleteById(Integer id) {
            products.remove(findById(id));
            IOFile.writeToFile(IOFile.PRODUCT_PATH, products);
            return true;
        }

        @Override
        public Product findById(Integer id) {
            for (Product pro : products) {
                if (pro.getId() == id) {
                    return pro;
                }
            }
            return null;
        }

        @Override
        public boolean existByCategoryId(Integer catId) {
            for (Product pro : products) {
                if (pro.getCategoryId() == catId) {
                    return true;
                }
            }
            return false;
        }

        // Phương thức hiển thị sản phẩm theo danh mục
        public void displayProductsByCategory() {
//            ICategoryDesign categoryDesign = new CategoryBusiness();
//            List<Category> categories = categoryDesign.findAll();
            CategoryBusiness categoryBussiness = new CategoryBusiness();
            List<Category> categories = categoryBussiness.findAll();
            // Hiển thị danh sách danh mục cho người dùng chọn
            System.out.println("Danh sách danh mục:");
            for (int i = 0; i < categories.size(); i++) {
                System.out.printf("| STT: %-3d | Name: %-15s |\n", i + 1, categories.get(i).getName());
            }
            System.out.println("Chọn số thứ tự danh mục để xem sản phẩm:");
            int categoryIndex = InputMethods.getInteger() - 1;  // Chuyển đổi từ STT thành chỉ số danh sách

            if (categoryIndex >= 0 && categoryIndex < categories.size()) {
                int categoryId = categories.get(categoryIndex).getId();
                List<Product> filteredProducts = new ArrayList<>();
                for (Product product : products) {
                    if (product.getCategoryId() == categoryId) {
                        filteredProducts.add(product);
                    }
                }
                if (filteredProducts.isEmpty()) {
                    System.out.println("Danh mục này chưa có sản phẩm.");
                } else {
                    System.out.println("Danh sách sản phẩm thuộc danh mục '" + categories.get(categoryIndex).getName() + "':");
                    for (Product product : filteredProducts) {
                        product.displayData();
                    }
                }
            } else {
                System.err.println("Danh mục không hợp lệ.");
            }
        }


    }
