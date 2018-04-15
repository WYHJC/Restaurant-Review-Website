package service;

import model.User;

public interface UserService {
	public User login(String user_name, String password);   //用户登录
	public int register(User user);                         //用户注册
}
