package ro.sda.final_project.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String description;
    private Double price;
    private String imageUrl;
    @Enumerated(EnumType.STRING)
    private Category category;
}
