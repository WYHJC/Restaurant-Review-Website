package dao;

import org.apache.ibatis.annotations.Param;

import model.Business;

public interface BusinessDao {
	public Business selectBusinessByID(@Param("business_id")String business_id);
}
