package dao;

import org.apache.ibatis.annotations.Param;

import model.User;

public interface UserDao {
	int insertUser(@Param("email")String email, @Param("password")String password);
	User selectUserBylogin(@Param("email")String email, @Param("password")String password);
}
