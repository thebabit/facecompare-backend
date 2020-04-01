package com.example.demo.Controller;



import com.example.demo.creadencial.Picture;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user/")
public class UserController {

    UserService userService ;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/test", produces = MediaType.TEXT_PLAIN_VALUE)
    public String test(){
        return "/hello this is hoang le check ";
    }


    @PostMapping(value = "/compare", produces = MediaType.APPLICATION_JSON_VALUE)
    public String compare(@RequestBody Picture a){
        List<String> s = userService.compare(a.getPic1(),a.getPic2(),a.getPic3());
        String out = "";
        for(int i = 0 ; i < s.size(); i++){
            out = out + s.get(i) +"\n";
        }

        return  out;
    }
}
