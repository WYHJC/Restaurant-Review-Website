package controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
			System.out.println(json);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//modelAndView.addObject("name", "hahaha");
				
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
	 * 模糊查询
	 * 
	 * @param input
	 * @return
	 */
	@RequestMapping("FuzzyQueryByInput")
	@ResponseBody
	public List<Business> FuzzyQueryByInput(@RequestParam String input){
		return businessService.FuzzyQueryByInput(input);
	}
}
