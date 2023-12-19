package ro.sda.final_project.controller;


import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ro.sda.final_project.controllers.CartController;
import ro.sda.final_project.entities.Cart;
import ro.sda.final_project.entities.Item;
import ro.sda.final_project.entities.User;
import ro.sda.final_project.services.CartService;
import ro.sda.final_project.services.ItemService;
import ro.sda.final_project.services.UserService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CartController.class)
public class CartControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CartService cartService;

    @MockBean
    private UserService userService;

    @MockBean
    private ItemService itemService;

    @Test
    public void testGetAllCart() throws Exception {
        User user = new User();
        user.setUsername("user");

        Item item = new Item();
        item.setTitle("coffee");

        List<Item> items = new ArrayList<>();
        items.add(item);

        Cart cart = new Cart();
        cart.setId(1);
        cart.setUser(user);
        cart.setItems(items);

        List<Cart> cartList = Arrays.asList(
                cart
        );


        Mockito.when(cartService.findAll()).thenReturn(cartList);

        mockMvc.perform(MockMvcRequestBuilders.get("/carts/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data", IsCollectionWithSize.hasSize(1)))
                .andExpect(jsonPath("$.data.[0].id").value(1));

    }


}
