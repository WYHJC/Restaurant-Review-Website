package dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import model.Business;

public interface BusinessDao {
	public Business selectBusinessByID(@Param("id")String id);
	public List<Business> selectRecoBusinessByUser(String id);
	public List<Business> selectGeneralRecoBusiness();
}
