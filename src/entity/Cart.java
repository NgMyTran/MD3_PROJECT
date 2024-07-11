//package entity;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class Cart {
//    private int id;
//
//    private int userId;
//
//    private Double total;
//    private List<CartItem> cartItems;
//
//    public Cart(int id, Double total, List<CartItem> cartItems, int userId) {
//        this.id = id;
//        this.userId = userId;
//        this.total = total != null ? total : 0.0;
//        this.cartItems = cartItems != null ? cartItems : new ArrayList<>();
//    }
//
//
//    public Cart() {
//        this.cartItems = new ArrayList<>();
//        this.total = 0.0;
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public Double getTotal() {
//        return total;
//    }
//
//    public void setTotal(Double total) {
//        this.total = total;
//    }
//
//    public List<CartItem> getCartItems() {
//        return cartItems;
//    }
//
//    public void setCartItems(List<CartItem> cartItems) {
//        this.cartItems = cartItems;
//    }
//
//    public int getUserId() {
//        return userId;
//    }
//
//    public void setUserId(int userId) {
//        this.userId = userId;
//    }
//}
