package controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import model.Business;
import service.BusinessService;
import util.MessageEncodeUtil;

@Controller
public class BusinessController {
	@Autowired
	private BusinessService businessService;
	public BusinessService getBusinessService(){
		return this.businessService;
	}
	
	public void setBusinessService(BusinessService businessService){
		this.businessService = businessService;
	}
	
	/**
	 * ���ݲ͹�id��ʾ�͹���ϸ��Ϣ
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("displayBusinessInfo")
	public ModelAndView displayBusinessInfo(@RequestParam String id){
		ModelAndView modelAndView = new ModelAndView("redirect:infofR.html");
		Business businessInfo = businessService.displayBusinessInfo(id);
		
		//���ù��������
		String json = MessageEncodeUtil.encode(businessInfo);
		modelAndView.addObject("businessInfo", json);
				
		return modelAndView;
	}
	
	@RequestMapping("findRecoByUser")
	@ResponseBody
	public List<Business> findRecoByUser(@RequestParam String uid){
		return businessService.selectRecoBusinessByUser(uid);
	}
	
	@RequestMapping("findGeneralReco")
	@ResponseBody
	public List<Business> findGeneralReco(){	 
		return businessService.selectGeneralRecoBusiness();
	}
	
	/**
	 * �����û������ѯ���ݵ��͹ݽ���
	 * 
	 * @param input
	 * @return
	 */
	@RequestMapping("fuzzyQueryPassInput")
	public ModelAndView fuzzyQueryPassInput(@RequestParam String input){
		ModelAndView modelAndView = new ModelAndView("redirect:Rforserach.html");
		modelAndView.addObject("input", input);
		
		return modelAndView;
	}
	
	/**
	 * ģ����ѯ
	 * 
	 * @param input
	 * @return
	 */
	@RequestMapping("fuzzyQueryByInput")
	@ResponseBody
	public List<Business> fuzzyQueryByInput(@RequestParam String input){
		//System.out.println(input);
		return businessService.fuzzyQueryByInput(input);
	}
	
	/**
	 * �洢ƴ���û�id�Ͳ͹�id
	 * 
	 * @param data
	 * @return
	 */
	@RequestMapping("saveShareTableUser")
	@ResponseBody
	public String saveShareTableUser(@RequestBody Map<String, String> data){
		//System.out.println(data.get("business_name") + "\n" + data.get("user_id"));
		
		if(businessService.saveShareTableUser(data.get("business_name"), data.get("user_id")) == 1){
	    	Map<String, Object> map = new HashMap<String, Object>();
	    	map.put("shareTableMsg", "succeed");
	    	String json = MessageEncodeUtil.encode(map);
	    	return json;
		}
		
    	return null;
		//return businessService.saveShareTableUser(data.get("business_id"), data.get("user_id"));
	}
	
	/**
	 * ɾ��ƴ���û�id�Ͳ͹�id
	 * 
	 * @param data
	 * @return
	 */
	@RequestMapping("deleteShareTableUser")
	@ResponseBody
	public String deleteShareTableUser(@RequestBody Map<String, String> data){
		//System.out.println(data.get("business_name") + "\n" + data.get("user_id"));
		
		if(businessService.deleteShareTableUser(data.get("business_name"), data.get("user_id")) == 1){
	    	Map<String, Object> map = new HashMap<String, Object>();
	    	map.put("cancelSharedMsg", "succeed");
	    	String json = MessageEncodeUtil.encode(map);
	    	return json;
		}
		
    	return null;
		//return businessService.saveShareTableUser(data.get("business_id"), data.get("user_id"));
	}
	
	/**
	 * ��ȡ���û�����ƴ���Ĳ͹ݣ�������������
	 * 
	 * @param user_id
	 * @return
	 */
	@RequestMapping("getChatRooms")
	@ResponseBody
	public List<String> getChatRooms(@RequestParam String user_id){
		return businessService.getChatRooms(user_id);
	}
	
	/**
	 * �ж�����͹��Ƿ񱻸��û�ƴ��
	 * 
	 * @param data
	 * @return
	 */
	@RequestMapping("ifUserShareBusiness")
	@ResponseBody
	public String ifUserShareBusiness(@RequestBody Map<String, String> data){
		return businessService.ifUserShareBusiness(data.get("business_name"), data.get("user_id"));
	}
}
