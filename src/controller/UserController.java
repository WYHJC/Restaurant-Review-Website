package controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    
    @SuppressWarnings("finally")
    @RequestMapping("addUser")  
    public void addUser(User user,HttpServletRequest request){  
        try {
        	user.setUser_id(003);
            System.out.println(user.getUser_id() + ":::::" + user.getUser_name());  
            userService.addUser(user);  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {
        	
        }  
    }
}
