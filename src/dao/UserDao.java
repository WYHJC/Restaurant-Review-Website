package dao;

import org.apache.ibatis.annotations.Param;

import model.User;

public interface UserDao {
	int insertUser(User user);
	User selectUserBylogin(@Param("user_name")String user_name, @Param("password")String password);
}
