package com.example.database.pojo;
import jakarta.persistence.*;
import java.time.LocalTime;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tempFiles")
public class TempFile {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
    private Long temp_file_id;
   private String temp_file_name;
   private String file_type;
   @ManyToOne
    private User user_details;
    private Date upload_date;
    private LocalTime upload_time;




}
