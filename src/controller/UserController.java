package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String login(String user_name, String password){
    	try{
    		User loginUser = userService.login(user_name, password);
    		if(loginUser != null){
    			return "index";
    		} else{
    			return "index";
    		}
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return "index";
    }
    
    @RequestMapping("register")
    public String register(@RequestBody User user){
    	if(userService.register(user) == 1){
    		return "index";
    	}
    	return "index";
    }
}
