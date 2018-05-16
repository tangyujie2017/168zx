package cn.tz.www.customer.controller.web;

import org.junit.Ignore;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestIndexController {
	
	
	@RequestMapping("/web/hello")
    public String helloHtml() {
      
        return "/index";
    }

}
