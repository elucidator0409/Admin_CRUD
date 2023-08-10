package com.test.admincrud.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "board")
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "nvarchar(150) not null")
    private String title;

    @Column(columnDefinition = "nvarchar(150) not null")
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Client author;
}
