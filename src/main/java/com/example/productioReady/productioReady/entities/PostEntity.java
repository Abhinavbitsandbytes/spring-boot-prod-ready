package com.example.productioReady.productioReady.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="posts")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PostEntity extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private  String description;

}

// If you want to make any entity as auditable you just need to extend that entity with AuditableEntity class.
