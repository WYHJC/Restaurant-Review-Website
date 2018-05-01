package controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import model.User;
import service.UserService;
import util.MD5Util;
import util.SendMailUtil;

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
    	//加密后发送数据库验证
    	String email = MD5Util.encodeToHex(data.get("email"));
    	String password = MD5Util.encodeToHex(data.get("password"));
    	User loginUser = userService.login(email, password);
    	return loginUser;
    }
    
    @RequestMapping("registerVerification")
    public String registerVerification(@RequestParam String email, @RequestParam String password){
    	if(userService.register(email, password) == 1){
    		return "register_succeed";
    	}
    	
    	
    	return "register_failed";
    }
    
    @RequestMapping("register")
    @ResponseBody
    public String register(@RequestBody Map<String, String> data){
    	String email = data.get("email");
    	String password = data.get("password");
        StringBuffer content = new StringBuffer("<h2>Please click the link below to activate the account. "
        		+ "The link can only be used once. Please activate it as soon as possible!</h2>");
        //用户信息加密（邮箱和密码）
        String emailMD5 = MD5Util.encodeToHex(email);
        password = MD5Util.encodeToHex(password); 
        content.append("<a style='font-size:16px;' href='http://localhost:8080/Resturant_Review_Website/registerVerification.do?")
        .append("email=" + emailMD5 + "&password=" + password +"'>")
        .append("http://localhost:8080/Resturant_Review_Website/registerVerification.do?")  
        .append("email=" + emailMD5 + "&password=" + password + "</a><br/><br/>");
    	
    	SendMailUtil.send(email, content.toString());
    	
    	//发送邮件成功，传输消息给前端
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("regMsg", "succeed");
    	String json = "";
    	try{
    	ObjectMapper mapper = new ObjectMapper();
		json = mapper.writeValueAsString(map);
		return json;
    	} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return null;
    }
}
