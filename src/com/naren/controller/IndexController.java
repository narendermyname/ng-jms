/**
 * 
 */
package com.naren.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author ntanwa
 *
 */
@Controller
@RequestMapping("/")
public class IndexController {

	/**
	 * 
	 */
	public IndexController() {
	}
	
	@RequestMapping
	public String getIndexPage(){
		return "UserManagement";
	}

}
