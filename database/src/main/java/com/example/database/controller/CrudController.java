package com.example.database.controller;

import com.example.database.pojo.Brands;
import com.example.database.pojo.TempFile;
import com.example.database.pojo.User;
import com.example.database.pojo.Shoes;
import com.example.database.repository.BrandsRepository;
import com.example.database.repository.ShoesRepository;
import com.example.database.repository.TempFileRepository;
import com.example.database.repository.UserRepository;
import com.example.database.services.FileServices;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@RestController
public class CrudController{
    @Autowired
   private  FileServices fileServices;
    @Value("${project.image}")
    private String path;


@Autowired
    private UserRepository userRepository;
@Autowired
private TempFileRepository tempFileRepository;

@Autowired
private ShoesRepository shoesRepository;

@Autowired
private BrandsRepository brandsRepository;

@CrossOrigin(origins = "http://localhost:3000/")
    @PostMapping("/upload_temp_img")
    public ResponseEntity<Object> uploadTempImage(@RequestParam("file")MultipartFile tempFile) throws IOException {
        //save the file to the server
        String fileName= StringUtils.cleanPath(tempFile.getOriginalFilename());
        String uploadDir= "./Images";
        File directory=new File(uploadDir);
        //create directory if it doesn't exist
        if(!directory.exists()){
            directory.mkdirs();
        }
        //save the file to specified directory
        Path filepath=Path.of(uploadDir,fileName);
        Files.copy(tempFile.getInputStream(), filepath, StandardCopyOption.REPLACE_EXISTING);

       //save the file metadata to the database;
        TempFile savedFile=new TempFile();
        //name
        savedFile.setTemp_file_name(fileName);
        //type
        savedFile.setFile_type(tempFile.getContentType());
        //date
        Calendar calendar=Calendar.getInstance();
        savedFile.setUpload_date(calendar.getTime());
        //user
        User user=userRepository.findById(1L).orElse(null);
        savedFile.setUser_details(user);
        //Time
        LocalTime currentTime=LocalTime.now();
        savedFile.setUpload_time(currentTime);

        //insert new row
        TempFile savedEntity= tempFileRepository.save(savedFile);

        //returning json
        return ResponseEntity.ok(savedFile);

    }
     //method to return server image
     @CrossOrigin(origins = "http://localhost:3000/")
    @GetMapping(value = "/images/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE)
    public void getImage(@PathVariable("imageName") String imageName, HttpServletResponse response) throws IOException {
        InputStream resource=this.fileServices.getResource(path,imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        if(imageName.endsWith(".jpg") || imageName.endsWith(".jpeg")){
            response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        }else if(imageName.endsWith(".png")){
            response.setContentType(MediaType.IMAGE_PNG_VALUE);
        }else if(imageName.endsWith(".gif")){
            response.setContentType(MediaType.IMAGE_GIF_VALUE);
        }
        StreamUtils.copy(resource,response.getOutputStream());

    }

    @CrossOrigin("http://localhost:3000/")
    @GetMapping(value = "/getBrands")
    public ResponseEntity<Object> getBrands(){
    return ResponseEntity.ok(brandsRepository.findAll());
    }
    @CrossOrigin("http://localhost:3000/")
    @PostMapping(value = "/addShoes")
    public ResponseEntity<Object> addShoe(@RequestParam("shoeName") String shoeName, @RequestParam("shoeDescription") String shoeDescription,@RequestParam("shoeBrand") String shoeBrand,@RequestParam("shoePrice") String shoePrice,@RequestParam("S") String S,@RequestParam("M") String M,@RequestParam("L") String L,@RequestParam("XL") String XL,@RequestParam("XXL") String XXL,@RequestParam("shoeColor") String shoeColor,@RequestParam("mainImg") String mainImg,@RequestParam("extraImg1") String extraImg1,@RequestParam("extraImg2") String extraImg2,@RequestParam("extraImg3") String extraImg3,@RequestParam("shoeType") String shoeType){
       Shoes shoesData=new Shoes();
       shoesData.setShoeName(shoeName);
       shoesData.setShoeDescription(shoeDescription);
       long brandId=Long.parseLong(shoeBrand);
       Brands brand=brandsRepository.findById(brandId).orElse(null);
        shoesData.setShoeBrand(brand);
        shoesData.setShoePrice(Integer.parseInt(shoePrice));
        shoesData.setShoeColor(shoeColor);
        shoesData.setShoeType(shoeType);
        shoesData.setShoe_size_S(Integer.parseInt(S));
        shoesData.setShoe_size_M(Integer.parseInt(M));
        shoesData.setShoe_size_L(Integer.parseInt(L));
        shoesData.setShoe_size_XL(Integer.parseInt(XL));
        shoesData.setShoe_size_XXL(Integer.parseInt(XXL));
        shoesData.setShoeDisplayPicture(mainImg);
        shoesData.setShoeExtraPicture1(extraImg1);
        shoesData.setShoeExtraPicture2(extraImg2);
        shoesData.setShoeExtraPicture3(extraImg3);
        Calendar calendar=Calendar.getInstance();
        shoesData.setShoeUpdateDate(calendar.getTime());
        LocalTime currentTime=LocalTime.now();
        shoesData.setShoe_update_time(currentTime);
        shoesData.setShoe_active(true);
        Shoes savedData=shoesRepository.save(shoesData);
        return ResponseEntity.ok(savedData);

    }


    @CrossOrigin("http://localhost:3000/")
    @GetMapping("/get_latest_product")
    public List<Shoes> getLatestProduct(){
         return shoesRepository.findFirstByOrderByShoeIdDesc();
    }

   @GetMapping("/getuser")
    public List<User> getuser(){
    return userRepository.findByEmail("arunchy600@gmail.com");
       }
       @GetMapping("/getshoes/{type}")

    public List<Shoes> getshoes(@PathVariable String type){
          return  shoesRepository.findByshoeType(type);
       }


 @CrossOrigin("http://localhost:3000/")
@GetMapping("/getbycategory/{brand}")
    public List<Shoes> getByCategory(@PathVariable String brand){
        int brand_id=Integer.parseInt(brand);
        long brandId=(long) brand_id;
         Brands brands=new Brands();
         brands.setBrandId(brandId);
         List<Shoes> data = shoesRepository.findByshoeBrand(brands);
         return data;
}









}
