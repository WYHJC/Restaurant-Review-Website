package service;

import java.util.List;

import model.Business;

public interface BusinessService {
	public Business displayBusinessInfo(String id);   //显示相应餐馆信息
	public List<Business> selectRecoBusinessByUser(String id);   //根据用户推荐
	public List<Business> selectGeneralRecoBusiness();           //一般推荐
}
