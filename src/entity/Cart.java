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
//
//    public void addProduct(Product product) {
//        for (CartItem item : cartItems) {
//            if (item.getProduct().getId() == product.getId()) {
//                // If the product is already in the cart, increase the quantity
//                item.setQuantity(item.getQuantity() + 1);
//                total += product.getPrice();
//                return;
//            }
//        }
//        // If the product is not in the cart, add it
//        cartItems.add(new CartItem(product, 1));
//        total += product.getPrice();
//    }
//
//    public void increaseQuantity(int productId) {
//        for (CartItem item : cartItems) {
//            if (item.getProduct().getId() == productId) {
//                item.setQuantity(item.getQuantity() + 1);
//                total += item.getProduct().getPrice();
//                return;
//            }
//        }
//    }
//
//    public void decreaseQuantity(int productId) {
//        for (CartItem item : cartItems) {
//            if (item.getProduct().getId() == productId) {
//                if (item.getQuantity() > 1) {
//                    item.setQuantity(item.getQuantity() - 1);
//                    total -= item.getProduct().getPrice();
//                } else {
//                    // Remove the item from the cart if quantity is 1
//                    cartItems.remove(item);
//                    total -= item.getProduct().getPrice();
//                }
//                return;
//            }
//        }
//    }
//
//    public void removeProduct(int productId) {
//        for (CartItem item : cartItems) {
//            if (item.getProduct().getId() == productId) {
//                total -= item.getProduct().getPrice() * item.getQuantity();
//                cartItems.remove(item);
//                return;
//            }
//        }
//    }
//
//    public void clearCart() {
//        cartItems.clear();
//        total = 0.0;
//    }
//}
