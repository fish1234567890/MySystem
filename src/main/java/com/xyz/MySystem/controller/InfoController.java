package com.xyz.MySystem.controller;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/info")
public class InfoController {

	private static final Logger logger = LoggerFactory.getLogger(InfoController.class);
	
	@RequestMapping("/gcInfo")
	public String getGCType() {
		 List<GarbageCollectorMXBean> l = ManagementFactory.getGarbageCollectorMXBeans();
	        for(GarbageCollectorMXBean b : l) {
	        	logger.info(b.getName());
	        }
	     return "common/indexDemo/index";   
	}
}
