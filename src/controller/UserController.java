package controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import model.User;
import service.UserService;

@Controller
public class UserController {
	@Autowired
    private UserService userService;  
    public UserService getUserService() {  
        return userService;  
    }  
    public void setUserService(UserService userService) {  
        this.userService = userService;  
    }
    
    @RequestMapping("login")
    @ResponseBody
    public User login(@RequestBody Map<String, String> data){
    	System.out.println(data.get("user_name")+data.get("password"));
    	User loginUser = userService.login(data.get("user_name"), data.get("password"));
    	return loginUser;
    }
    
    @RequestMapping("register")
    public String register(@RequestBody User user){
    	if(userService.register(user) == 1){
    		return "index";
    	}
    	return "index";
    }
}
