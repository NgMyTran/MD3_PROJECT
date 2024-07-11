//package bussiness.classes;
//
//import entity.Product;
//import presentation.util.IOFile;
//import entity.Cart;
//import java.util.ArrayList;
//import java.util.List;
//
//import static bussiness.classes.ProductBussiness.products;
//
//public class CartBussiness {
//    // Lấy dữ liệu từ file và khởi tạo giỏ hàng
//    public static List<Cart> carts;
//
//    public CartBussiness() {
//        carts= IOFile.readFromFile(IOFile.USER_PATH);
//        System.out.println();
//    };
//
//    public static void addProductToCart(Cart cart) {
//        carts.add(cart);
//        IOFile.writeToFile(IOFile.CART_PATH, carts);  // Lưu lại danh sách giỏ hàng
//    }
//
////    kiểm tra số lượng tồn kho
//    public static boolean checkQuantity(int id, int quantity) {
//        // Tìm sản phẩm dựa trên id
//        Product product = products.stream().filter(p -> p.getId()==id).findFirst().orElse(null);
//        if (product == null) {
//            return false;
//        }
//        // Kiểm tra số lượng tồn kho
//        return product.getQuantity() >= quantity;
//    }
//
//    // Xóa sản phẩm khỏi giỏ hàng
//    public static void removeProductFromCart(int id) {
//        for (Cart cart : carts) {
//            for (int i = 0; i < cart.getCartItems().size(); i++) {
//                if (cart.getCartItems().get(i).getProduct().getId()==id) {
//                    cart.getCartItems().remove(i);
//                    break;
//                }
//            }
//        }
//        // Lưu lại danh sách giỏ hàng
//        IOFile.writeToFile(IOFile.CART_PATH, carts);
//    }
//
//    //xóa giỏ hàng trong txt
//    public static void removeFromCart() {
//        carts.clear();
//        IOFile.writeToFile(IOFile.CART_PATH, carts);
//    }
//
//}
