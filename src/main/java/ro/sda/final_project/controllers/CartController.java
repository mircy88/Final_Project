package ro.sda.final_project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.sda.final_project.dto.CartDto;
import ro.sda.final_project.entities.Cart;
import ro.sda.final_project.entities.User;
import ro.sda.final_project.services.CartService;
import ro.sda.final_project.services.UserService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public ResponseEntity<List<Cart>> getAllCarts() {
        List<Cart> cartList = cartService.findAll();
        return ResponseEntity.ok(cartList);
    }

    @PostMapping("/")
    public ResponseEntity<Cart> createCart(@RequestBody CartDto cartBody) {
        Optional<User> userOptional = userService.findById(cartBody.getUserId());

        Cart savedCart = new Cart();
        savedCart.setUser(userOptional.get());
        savedCart.setItems(cartBody.getItems());

        Cart cart = cartService.createCart(savedCart);
        return ResponseEntity.ok(cart);
    }

    @PutMapping("/")
    public ResponseEntity<Cart> updateCart(@RequestBody CartDto cartBody) {
        Optional<User> userOptional = userService.findById(cartBody.getUserId());

        Cart savedCart = new Cart();
        savedCart.setId(cartBody.getId());
        savedCart.setUser(userOptional.get());
        savedCart.setItems(cartBody.getItems());

        Cart cart = cartService.createCart(savedCart);
        return ResponseEntity.ok(cart);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCart(@PathVariable ("id") Integer id) {
        System.out.println("The Cart with the Id" + id + "will be deleted!!!");
        cartService.deleteCartById(id);
        return ResponseEntity.ok("Cart deleted successfully");
    }

}

