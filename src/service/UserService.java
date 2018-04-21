package service;

import model.User;

public interface UserService {
	public User login(String email, String password);   //用户登录
	public int register(String email, String password); //用户注册
}
