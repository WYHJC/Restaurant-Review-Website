package dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import model.Business;

public interface BusinessDao {
	public Business selectBusinessByID(@Param("id")String id);
	public List<Business> selectRecoBusinessByUser(String id);
	public List<Business> selectGeneralRecoBusiness();
	public List<Business> selectBusinessByFuzzyQuery(String input);
	public int insertShareTable(String business_name, String user_id);
	public List<String> selectChatRoomsByUser(@Param("user_id")String user_id);
}
