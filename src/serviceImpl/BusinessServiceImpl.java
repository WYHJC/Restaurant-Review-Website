package serviceImpl;

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
}
