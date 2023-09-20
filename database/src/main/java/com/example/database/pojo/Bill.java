package com.example.database.pojo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "bill")
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long bill_id;
  @ManyToOne
  @JoinColumn(name = "user_details")
    private User userDetails;

   @ManyToOne
   @JoinColumn(name = "shoes_details")
    private Shoes shoes_details;

   @Column(name = "quantity")
    private int quantity;

   @Column(name = "prize")
    private int prize;

   @Column(name = "size")
    private String size;



}
