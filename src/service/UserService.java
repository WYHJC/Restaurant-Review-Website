package service;

import model.User;

public interface UserService {
	public User login(String user_name, String password);   //�û���¼
	public int register(User user);                         //�û�ע��
}
