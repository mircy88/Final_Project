package ro.sda.final_project.controllers;


import ro.sda.final_project.dto.CartDto;
import ro.sda.final_project.entities.Cart;
import ro.sda.final_project.entities.User;
import ro.sda.final_project.services.CartService;
import ro.sda.final_project.services.UserService;
import ro.sda.final_project.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/carts")
public class CartController {
    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public ResponseEntity<ApiResponse> getAllCarts() {
        List<Cart> cartList = this.cartService.findAll();
        ApiResponse response = new ApiResponse.Builder()
                .status(200)
                .message("List de cosuri")
                .data(cartList)
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/")
    public ResponseEntity<ApiResponse> createCart(@RequestBody CartDto cartDto) {
        System.out.println(cartDto.getUserId());

        try {
            Optional<User> optionalUser = this.userService.findById(cartDto.getUserId());

            if (optionalUser.isPresent()) {
                User user = optionalUser.get();

                Cart cart = new Cart();

                cart.setUser(user);
                cart.setItems(cartDto.getItems());

                ApiResponse response = new ApiResponse.Builder()
                        .status(200)
                        .message("Cos creat cu success")
                        .data(cartService.createCart(cart))
                        .build();
                return ResponseEntity.ok(response);
            } else {
                throw new Exception("Invalid user");
            }
        } catch (Exception exception) {
            ApiResponse response = new ApiResponse.Builder()
                    .status(500)
                    .message(exception.getMessage())
                    .data(null)
                    .build();
            return ResponseEntity.ok(response);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse> updateCart(@RequestBody CartDto cartDto, @PathVariable("id") Integer id) {
        try {
            Optional<User> optionalUser = this.userService.findById(cartDto.getUserId());

            if (optionalUser.isPresent()) {
                User user = optionalUser.get();

                Cart cart = new Cart();

                cart.setId(id);
                cart.setUser(user);
                cart.setItems(cartDto.getItems());

                ApiResponse response = new ApiResponse.Builder()
                        .status(200)
                        .message("Cos actualizat cu success")
                        .data(cartService.updateCart(cart))
                        .build();
                return ResponseEntity.ok(response);
            } else {
                throw new Exception("Invalid user");
            }
        } catch (Exception exception) {
            ApiResponse response = new ApiResponse.Builder()
                    .status(500)
                    .message(exception.getMessage())
                    .data(null)
                    .build();
            return ResponseEntity.ok(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCart(@PathVariable("id") Integer id) {
        cartService.deleteCartById(id);

        ApiResponse response = new ApiResponse.Builder()
                .status(200)
                .message("Cos sters cu success")
                .data(null)
                .build();
        return ResponseEntity.ok(response);
    }
}
