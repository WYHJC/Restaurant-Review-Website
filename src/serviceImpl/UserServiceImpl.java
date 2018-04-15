package serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.UserDao;
import model.User;
import service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired  
    private UserDao userDao;  
    public UserDao getUserDao() {  
        return userDao;  
    }  
    public void setUserDao(UserDao userDao) {  
        this.userDao = userDao;  
    }
	
	@Override
	public User login(String user_name, String password){
		return userDao.selectUserBylogin(user_name, password);
	}
	
	@Override
	public int register(User user){
		return userDao.insertUser(user);
	}
}
