package service;

import java.util.List;

import model.Business;

public interface BusinessService {
	public Business displayBusinessInfo(String id);              //��ʾ��Ӧ�͹���Ϣ
	public List<Business> selectRecoBusinessByUser(String id);   //�����û��Ƽ�
	public List<Business> selectGeneralRecoBusiness();           //һ���Ƽ�
	public List<Business> fuzzyQueryByInput(String input);       //ģ����ѯ
	public int saveShareTableUser(String business_name, String user_id); //�洢ƴ���û�id�Ͳ͹�id
	public List<String> getChatRooms(String user_id);   //��ȡ���û�����������
	public String ifUserShareBusiness(String business_name, String user_id); //�ж�����͹��Ƿ񱻸��û�ƴ��
	public int deleteShareTableUser(String business_name, String user_id); //ɾ��ƴ���û�id�Ͳ͹�id
}
