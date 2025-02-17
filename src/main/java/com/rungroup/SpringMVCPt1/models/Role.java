package com.rungroup.SpringMVCPt1.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToMany(mappedBy = "roles") //In a bidirectional many-to-many relationship, only one entity should have the mappedBy attribute, while the other side owns the relationship with the @JoinTable annotation
    private List<UserEntity> users = new ArrayList<>();
}
