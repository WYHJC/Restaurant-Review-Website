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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import model.Business;
import service.BusinessService;

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
		
		try {
			//��business����תΪjackson json
			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writeValueAsString(businessInfo);
			modelAndView.addObject("businessInfo", json);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
				
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
		ModelAndView modelAndView = new ModelAndView("redirect:menu.html");
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
		System.out.println(data.get("business_id") + "\n" + data.get("user_id"));
		
		if(businessService.saveShareTableUser(data.get("business_id"), data.get("user_id")) == 1){
			//�����ʼ��ɹ���������Ϣ��ǰ��
	    	Map<String, Object> map = new HashMap<String, Object>();
	    	map.put("shareTableMsg", "succeed");
	    	String json = "";
	    	try{
	    	ObjectMapper mapper = new ObjectMapper();
			json = mapper.writeValueAsString(map);
			return json;
	    	} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
    	return null;
		//return businessService.saveShareTableUser(data.get("business_id"), data.get("user_id"));
	}
}
