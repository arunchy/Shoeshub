package com.example.database.pojo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalTime;
import java.util.Date;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cartId;
    @ManyToOne
    @JoinColumn(name = "user_details")
    private User userDetails;
    @ManyToOne
    @JoinColumn(name = "shoe_details")
    private Shoes  shoes_details;

    @Column(name = "add_date")
    private Date add_date;
    @Column(name = "add_time")
    private LocalTime add_time;

}
