package serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.BusinessDao;
import model.Business;
import service.BusinessService;

@Service("businessService")
public class BusinessServiceImpl implements BusinessService{
	@Autowired  
    private BusinessDao businessDao;  
    public BusinessDao getBusinessDao() {  
        return businessDao;  
    }  
    public void setBusinessDao(BusinessDao businessDao) {  
        this.businessDao = businessDao;  
    }
    
    @Override
    public Business displayBusinessInfo(String business_id){
    	return businessDao.selectBusinessByID(business_id);
    }
    
    @Override
	public List<Business> selectRecoBusinessByUser(String id) {
	List<Business> result = new ArrayList<Business>();
	ReomveDepulicationMerge(result, businessDao.selectRecoBusinessByUser(id));
	return result;
	}
	
	private void ReomveDepulicationMerge(List<Business> list1,List<Business> list2){
		for(int i = 0;i<list2.size()&&list1.size()<=9;i++){
			boolean flag = true;
			for(int j = 0;j<list1.size();j++){
				if(list1.get(j).getID().equals(list2.get(i).getID())){
					flag = false;
					break;
				}
			}
			if(flag==true)
				list1.add(list2.get(i));
		
		}
		
		return ;
	}
	
	@Override
	public List<Business> selectGeneralRecoBusiness() {
		return businessDao.selectGeneralRecoBusiness();
		
	}
	
	@Override
	public List<Business> fuzzyQueryByInput(String input) {
		return businessDao.selectBusinessByFuzzyQuery(input);
	}
	
	@Override
	public int saveShareTableUser(String business_name, String user_id){
		return businessDao.insertShareTable(business_name, user_id);
	}
	
	@Override
	public List<String> getChatRooms(String user_id){
		
		return businessDao.selectChatRoomsByUser(user_id);
	}
	
	@Override
	public String ifUserShareBusiness(String business_name, String user_id){
		return businessDao.selectIfUserShareBusiness(business_name, user_id);
	}
	
	@Override
	public int deleteShareTableUser(String business_name, String user_id){
		return businessDao.deleteShareTable(business_name, user_id);
	}
}
