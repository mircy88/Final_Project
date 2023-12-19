package ro.sda.final_project.dto;

import lombok.Data;
import ro.sda.final_project.entities.Item;

import java.util.List;

@Data
public class CartDto {
    private Integer id;
    private Integer userId;
    private List<Item> items;

}
