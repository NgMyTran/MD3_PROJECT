package bussiness.interfaces;

import entity.Product;

public interface IProductDesign extends IGenericDesign<Product, Integer> {
    Product findByName(String name);

    boolean existByCategoryId(Integer catId);
}
