package ro.sda.final_project.service;

import ro.sda.final_project.entities.Category;
import ro.sda.final_project.entities.Item;
import ro.sda.final_project.repositories.ItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ro.sda.final_project.services.ItemService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ItemServiceTest {
    @Mock
    private ItemRepository itemRepository;

    @InjectMocks
    private ItemService itemService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllProducts() {
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

        // Mock data
        List<Item> productList = Arrays.asList(
                item1, item2
        );

        // Define the behavior of the repository mock
        when(itemRepository.findAll()).thenReturn(productList);

        // Call the service method
        List<Item> result = itemService.findAll();

        // Verify the result
        assertEquals(2, result.size());
        assertEquals("coffee", result.get(0).getTitle());
        assertEquals("cola", result.get(1).getTitle());
    }

    @Test
    public void testGetProductById() {
        // Mock data
        Item item1 = new Item();
        item1.setId(1);
        item1.setTitle("TV Samsung");
        item1.setPrice(1999.00);
        item1.setCategory(Category.TV);


        // Define the behavior of the repository mock
        when(itemRepository.findById(1)).thenReturn(Optional.of(item1));

        // Call the service method
        Optional<Item> result = itemService.getById(1);

        // Verify the result
        assertEquals(true, result.isPresent());
        assertEquals("coffee", result.get().getTitle());
    }

    @Test
    public void testSaveProduct() {
        // Mock data
        Item item1 = new Item();
        item1.setTitle("TV Samsung");
        item1.setPrice(1999.00);
        item1.setCategory(Category.TV);

        // Call the service method
        itemService.createItem(item1);

        // Verify that the repository's save method was called
        verify(itemRepository, times(1)).save(item1);
    }

    @Test
    public void testDeleteProduct() {
        // Call the service method
        itemService.deleteItem(1);

        // Verify that the repository's deleteById method was called
        verify(itemRepository, times(1)).deleteById(1);
    }
}
