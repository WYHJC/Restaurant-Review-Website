package service;

import java.util.List;

import model.Business;

public interface BusinessService {
	public Business displayBusinessInfo(String id);   //��ʾ��Ӧ�͹���Ϣ
	public List<Business> selectRecoBusinessByUser(String id);   //�����û��Ƽ�
	public List<Business> selectGeneralRecoBusiness();           //һ���Ƽ�
}
