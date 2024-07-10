package bussiness.interfaces;

import entity.Product;

public interface ICartDesign {
    void addProductToCart(Product product, int quantity);
    void removeProductFromCart( Product product);
    void displayCart();
    void checkOut();
    void displayOrderHistory();
}
