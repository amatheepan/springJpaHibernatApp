package org.own.ste.admin.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
/**
 * 
 * @author Ramu Gadde
 * This is the test controller,for testing purpose developed.
 * 
 * If any one want's to fork-Enjoy the logic.
 */
@Controller
@RequestMapping(value="/testdata")
public class TestController {
    
	/**
	 * Test method.
	 * @param model contains  model data which can be use to pass to UI.
	 * @return test.jsp.
	 */
	@RequestMapping(method=RequestMethod.GET,produces={"text/html"})
	public String test(Model model){
		//TODO:change this logic in future forks.
		model.addAttribute("testdata","test data is injected");
		return "test";
	}
}
