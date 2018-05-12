package service;

import java.util.List;

import model.Business;

public interface BusinessService {
	public Business displayBusinessInfo(String id);              //显示相应餐馆信息
	public List<Business> selectRecoBusinessByUser(String id);   //根据用户推荐
	public List<Business> selectGeneralRecoBusiness();           //一般推荐
	public List<Business> fuzzyQueryByInput(String input);       //模糊查询
	public int saveShareTableUser(String business_name, String user_id); //存储拼桌用户id和餐馆id
	public List<String> getChatRooms(String user_id);   //获取该用户所有聊天室
}
