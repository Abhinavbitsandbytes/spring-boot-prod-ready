package com.example.productioReady.productioReady.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.envers.Audited;

@Entity
@Table(name="posts")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Audited
public class PostEntity extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private  String description;

}

// if you want to exclude any field from auditing, you can use @NotAudited annotation on that field.
// Example:
// @NotAudited
// private String description;

// because of @Audited annotation on the class level, all fields will be audited by default.
// @Audited will create 2 more tables in the database to keep track of revisions along with post table.

// 1 - posts_aud - this table will keep track of revisions
// 2 - revinfo - this table will keep track of revision info like revision number, timestamp, user who made the change etc.
