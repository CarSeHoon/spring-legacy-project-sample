package kr.co.edu.common.controller;

import java.util.List; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.edu.common.service.CommonService;
import kr.co.edu.common.vo.Test;

@Controller
public class CommonController {

	@Autowired
	private CommonService commonService;
	
	@RequestMapping("/test/jsp")	//dispatcher가 이 부분을 찾음
	public String testJsp (Model model) {
		List<Test> testList = commonService.testSelectList();	//db에서 데이터 갖고오기
		System.out.println("/test/jsp");	
		model.addAttribute("testList", testList);		//model을 통해서 화면에 데이터 전달
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			//mapper.writeValueAsString(testList);	//java Object to JSON
			String jsonData = mapper.writeValueAsString(testList);
			System.out.println(jsonData);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "/test";		//default 값이 jsp
	}
	
	@RequestMapping("/test/jsp2")
	public ModelAndView testJsp2 () {
		List<Test> testList = commonService.testSelectList();
		ModelAndView mav = new ModelAndView("/test");
		//mav.setViewName("/test");
		mav.addObject("testList", testList);
		return mav;
	}
	
	@RequestMapping("/test/json")
	public ModelAndView testjson () {
		List<Test> testList = commonService.testSelectList();
		ModelAndView mav = new ModelAndView("jsonView");
		//mav.setViewName("/test");
		mav.addObject("testList", testList);
		return mav;
	}
}