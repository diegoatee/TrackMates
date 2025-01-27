package com.rungroup.SpringMVCPt1.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

//The below 4 annotations are lombok
@Data //Generates all getters, setters, toString(), equals(), and hashCode()
@AllArgsConstructor //Generates a constructor with all arguments
@NoArgsConstructor //Generates a constructor with no arguments
@Builder //Allows you to instantiate an Object differently
@Entity //Tells Spring JPA that the class below is an entity to be mapped to a database
@Table(name = "clubs") //Will create a table called "clubs" in the database that's specified in application.properties
public class Club {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Column(length = 20000) //Since URLs are long, and by default, 255 is too small
    private String photoURL;
    @Column(length = 20000) //Since this might be about as long as your typical blog page
    private String content;
    @CreationTimestamp
    private LocalDateTime createdOn;
    @UpdateTimestamp
    private LocalDateTime updatedOn;
}
