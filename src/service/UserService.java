package service;

import model.User;

public interface UserService {
	public User login(String email, String password);   //�û���¼
	public int register(String email, String password); //�û�ע��
}
