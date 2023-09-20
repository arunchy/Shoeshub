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
@Table(name = "shoes")
public class Shoes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shoe_id")
    private Long shoeId;
    @Column(name = "shoe_name")
    private String shoeName;

    @ManyToOne
    @JoinColumn(name = "shoe_brand",nullable = false)
    private Brands shoeBrand;

    @Column(name = "shoe_description",nullable = false)
    private String shoeDescription;

    @Column(name = "shoe_color",nullable = false)
    private String shoeColor;

    @Column(name = "shoe_price",nullable = false)
    private int shoePrice;

    @Column(name = "shoe_type",nullable = false)
    private String shoeType;

    @Column(name = "size_S",nullable = true)
    private int shoe_size_S;

    @Column(name = "shoe_X",nullable = true)
    private int shoe_size_M;

    @Column(name = "shoe_L",nullable = true)
    private int shoe_size_L;

    @Column(name="shoe_XL",nullable = true)
    private int shoe_size_XL;

    @Column(name="shoe_XXL",nullable = true)
    private int shoe_size_XXL;

    @Column(name = "shoe_display_picture",nullable = false)
    private String shoeDisplayPicture;

    @Column(name = "shoe_extra_picture_1",nullable = false)
    private String shoeExtraPicture1;

    @Column(name = "shoe_extra_picture_2",nullable = true)
    private String ShoeExtraPicture2;

    @Column(name = "shoe_extra_picture_3",nullable = true)
    private String shoeExtraPicture3;

    @Column(name = "shoe_update_date",nullable = false)
    private Date shoeUpdateDate;

    @Column(name = "shoe_update_time",nullable = false)
    private LocalTime shoe_update_time;

    @Column(name="shoe_active")
    private boolean shoe_active;



}
