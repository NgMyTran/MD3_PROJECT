package bussiness.classes;

import bussiness.interfaces.IProductDesign;
import entity.Product;
import presentation.util.IOFile;

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

    }
