package ro.sda.final_project.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import ro.sda.final_project.controllers.ItemController;
import ro.sda.final_project.entities.Category;
import ro.sda.final_project.entities.Item;
import ro.sda.final_project.services.ItemService;
import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import java.util.Arrays;
import java.util.List;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ItemController.class)
public class ItemControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ItemService itemService;

    @Test
    public void testGetAllProducts() throws Exception {
        Item item1 = new Item();
        item1.setId(1);
        item1.setTitle("TV Samsung");
        item1.setPrice(1999.00);
        item1.setCategory(Category.TV);

        Item item2 = new Item();
        item2.setId(2);
        item2.setTitle("Iphone X");
        item2.setPrice(4000.00);
        item2.setCategory(Category.PHONE);

        List<Item> products = Arrays.asList(item1, item2);

        Mockito.when(itemService.findAll()).thenReturn(products);

        mockMvc.perform(get("/items/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data", IsCollectionWithSize.hasSize(2)))
                .andExpect(jsonPath("$.data[0].id").value(1))
                .andExpect(jsonPath("$.data[0].title").value("TV Samsung"))
                .andExpect(jsonPath("$.data[0].price").value(1999.00))
                .andExpect(jsonPath("$.data[1].id").value(2))
                .andExpect(jsonPath("$.data[1].title").value("Iphone X"))
                .andExpect(jsonPath("$.data[1].price").value(4000.00));
    }

    @Test
    public void testCreateProduct() throws Exception {
        Item item = new Item();
        item.setTitle("New Product");
        item.setPrice(3250.00);
        item.setCategory(Category.LAPTOP);

        Mockito.when(itemService.createItem(Mockito.any(Item.class)))
                .thenReturn(item);

        mockMvc.perform(post("/items/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(item)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.title").value("New Product"))
                .andExpect(jsonPath("$.data.price").value(3250.00));
    }

}
