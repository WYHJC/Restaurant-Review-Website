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
	 * 根据餐馆id显示餐馆详细信息
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("displayBusinessInfo")
	public ModelAndView displayBusinessInfo(@RequestParam String id){
		ModelAndView modelAndView = new ModelAndView("redirect:infofR.html");
		Business businessInfo = businessService.displayBusinessInfo(id);
		
		try {
			//将business对象转为jackson json
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
	 * 传递用户输入查询内容到餐馆界面
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
	 * 模糊查询
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
	 * 存储拼桌用户id和餐馆id
	 * 
	 * @param data
	 * @return
	 */
	@RequestMapping("saveShareTableUser")
	@ResponseBody
	public String saveShareTableUser(@RequestBody Map<String, String> data){
		System.out.println(data.get("business_id") + "\n" + data.get("user_id"));
		
		if(businessService.saveShareTableUser(data.get("business_id"), data.get("user_id")) == 1){
			//发送邮件成功，传输消息给前端
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
