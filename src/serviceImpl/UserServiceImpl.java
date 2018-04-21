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
	public User login(String email, String password){
		return userDao.selectUserBylogin(email, password);
	}
	
	@Override
	public int register(String email, String password){
		return userDao.insertUser(email, password);
	}
}
