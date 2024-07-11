package bussiness.classes;

import bussiness.interfaces.ICategoryDesign;
import entity.Category;
import presentation.util.IOFile;

import java.util.List;

public class CategoryBusiness implements ICategoryDesign {
    public static List<Category> categories;

    public CategoryBusiness() {
        categories = IOFile.readFromFile(IOFile.CATEGORY_PATH);
    }

    @Override
    public boolean create(Category category) {
        categories.add(category);
        IOFile.writeToFile(IOFile.CATEGORY_PATH, categories);
        return true;
    }

    @Override
    public List<Category> findAll() {
        return categories;
    }

    @Override
    public boolean update(Category category) {
        categories.set(categories.indexOf(findById(category.getId())), category);
        IOFile.writeToFile(IOFile.CATEGORY_PATH, categories);
        return true;
    }

    @Override
    public boolean deleteById(Integer id) {
        categories.remove(findById(id));
        IOFile.writeToFile(IOFile.CATEGORY_PATH, categories);
        return true;
    }

    @Override
    public Category findById(Integer id) {
        for (Category cat : categories) {
            if (cat.getId() == id) {
                return cat;
            }
        }
        throw new RuntimeException("Category không có id: " + id);
    }
}
