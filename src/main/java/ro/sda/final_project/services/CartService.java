package ro.sda.final_project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.sda.final_project.entities.Cart;
import ro.sda.final_project.repositories.CartRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;
    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }
    public List<Cart> findAll() {
        return this.cartRepository.findAll();
    }
    public Optional<Cart> findById(Integer id) {
        return cartRepository.findById(id);
    }
    public Cart createCart(Cart cart) {
        return cartRepository.save(cart);
    }
    public Cart updateCart(Cart cart) {
        return cartRepository.save(cart);
    }
    public void deleteCart(Cart cart) {
        cartRepository.delete(cart);
    }
    public void deleteCartById(Integer id) {
        cartRepository.deleteById(id);
    }


}

