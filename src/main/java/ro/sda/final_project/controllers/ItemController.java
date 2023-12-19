package ro.sda.final_project.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.sda.final_project.entities.Item;
import ro.sda.final_project.services.ItemService;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @GetMapping("/") //aceasta metoda se va apela cand vom avea un request de tipul get
    public ResponseEntity<List<Item>> getAllUsers() {
        List<Item> itemList = itemService.findAll();

        return ResponseEntity.ok(itemList); //punem "ok" daca totul a decurs bine
    }

    @PostMapping("/")
    public ResponseEntity<Item> createItem(@RequestBody Item item) { //ceea ce va primi in body il va transforma in ceva de tipul user
        Item savedItem = itemService.createItem(item); //apelam metoda createUser din service pentru a salva user-ul din baza de date
        return ResponseEntity.ok(savedItem);
    }

    @PutMapping("/")
    public ResponseEntity<Item> updateItem(@RequestBody Item item) {
        Item updatedItem = itemService.updateItem(item);
        return ResponseEntity.ok(updatedItem);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteItem(@PathVariable ("id") Integer id) {
        System.out.println("The Item with the Id" + id + " will be deleted!!!");
        itemService.deleteById(id);
        return ResponseEntity.ok("Item deleted");
    }
}

