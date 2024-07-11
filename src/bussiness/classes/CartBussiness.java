//package bussiness.classes;
//
//import entity.Cart;
//import entity.Product;
//import presentation.util.IOFile;
//
//import java.util.List;
//
//public class CartBussiness {
//    public static List<Cart> carts;
//
//    public CartBussiness() {
//        carts = IOFile.readFromFile(IOFile.CART_PATH);
//    }
//
//    public boolean create(Cart cart) {
//        carts.add(cart);
//        IOFile.writeToFile(IOFile.CART_PATH, carts);
//        return true;
//    }
//
//    public List<Cart> findAll() {
//        return carts;
//    }
//
//    public Cart findById(int id) {
//        for (Cart cart : carts) {
//            if (cart.getId() == id) {
//                return cart;
//            }
//        }
//        return null;
//    }
//
//    public boolean update(Cart cart) {
//        carts.set(carts.indexOf(findById(cart.getId())), cart);
//        IOFile.writeToFile(IOFile.CART_PATH, carts);
//        return true;
//    }
//
//    public boolean deleteById(int id) {
//        carts.remove(findById(id));
//        IOFile.writeToFile(IOFile.CART_PATH, carts);
//        return true;
//    }
//}
