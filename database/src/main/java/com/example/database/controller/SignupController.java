package com.example.database.controller;
import com.example.database.pojo.Cart;
import com.example.database.pojo.Shoes;
import com.example.database.pojo.User;
import com.example.database.repository.CartRepository;
import com.example.database.repository.ShoesRepository;
import com.example.database.repository.UserRepository;
import com.example.database.services.FileServices;
import com.example.database.services.PasswordEncoderUtil;
import com.example.database.services.Validation;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class SignupController {
    @Autowired
    private PasswordEncoderUtil passwordEncoderUtil;
    @Autowired
    private FileServices fileServices;
    @Value("${project.image}")
    private String path;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Validation validation;

    @Autowired
    private HttpSession session;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ShoesRepository shoesRepository;
    @CrossOrigin("http://localhost:3000/")
 @PostMapping("/upload_temp_pp")
    public Map<String,String> uploadTempPP(@RequestParam("file")MultipartFile pp) throws IOException {
     String fileName= StringUtils.cleanPath(pp.getOriginalFilename());
     String uploadDir="./Images";
     File directory= new File(uploadDir);
     if(!directory.exists()){
         directory.mkdirs();
     }
     Path filepath= Path.of(uploadDir,fileName);
     Files.copy(pp.getInputStream(),filepath, StandardCopyOption.REPLACE_EXISTING);
     Map<String,String> response=new HashMap<>();
     response.put("filename",fileName);
     return response;
    }

    @GetMapping(value = "/pp/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE)
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

    @CrossOrigin(origins = "http://localhost:3000/")
    @PostMapping(value = "/signup")
    public ResponseEntity<Object> userSignup(@RequestParam("pp") String profilePicture,@RequestParam("firstName") String firstName,@RequestParam("lastName") String lastName,@RequestParam("email") String email,@RequestParam("newPassword")String newPassword,
    @RequestParam("contactNumber") String contactNumber,@RequestParam("dob") String dob,@RequestParam("city") String city,@RequestParam("state") String state,@RequestParam("streetAddress") String streetAddress,@RequestParam("gender") String gender,@RequestParam("userType") String userType) throws ParseException {
      User data=new User();
      //user_ID
        Random random =new Random();
        long userid= (long) (random.nextInt(90000)+10000);
        data.setUserId(userid);
        //firstname
      data.setFirstName(firstName);
      //lastname
      data.setLastName(lastName);
      //email
      data.setEmail(email);
      //password
      String encryptedPassword=passwordEncoderUtil.encryptPassword(newPassword);
      data.setPassword(encryptedPassword);
      //contactNumber
      data.setPhoneNumber(contactNumber);
      //dob
      DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
      Date date=dateFormat.parse(dob);
      data.setDateOfBirth(date);
      //city
      data.setCity(city);
      //state
      data.setState(state);
      //streetAddress
      data.setStreetAddress(streetAddress);
      //Gender
      data.setGender(gender);

      //registrationDate
      Calendar calendar=Calendar.getInstance();
      data.setRegistrationDate(calendar.getTime());
       //lastLogin
      data.setLastLogin(calendar.getTime());

      //profilePicture
        data.setProfilePicture(profilePicture);

        //userType
        data.setUserType(userType);
        //UserGender
        data.setGender(gender);

     //inserting new row of user data.
      User newUser=userRepository.save(data);
      return ResponseEntity.ok(newUser);



    }

    @CrossOrigin("http://localhost:3000/")
    @PostMapping("/userLogin")
    public User userLogin(@RequestParam("email") String email, @RequestParam("password")String password){
        List<User> emailData=userRepository.findByEmail(email);
        User user=validation.validUser(emailData,password);
        if(user!=null){
            session.setAttribute("userId",user.getUserId());
            System.out.println("userid is : "+session.getAttribute("userId"));
            return user;
        }
        throw new NoSuchElementException("No user found");


    }

    @GetMapping("/isLogin")
    public Object isLogin(HttpSession session){
        System.out.println("user id is : "+session.getAttribute("userId"));
        return session.getAttribute("userId");

    }




   @CrossOrigin("http://localhost:3000/")
   @GetMapping("/getUser/{id}")
    public ResponseEntity<Object> getUser(@PathVariable("id") String id){
        int intid=Integer.parseInt(id);
       User userData=userRepository.findById((long) intid).orElse(null);
      return ResponseEntity.ok(userData);

   }



}




