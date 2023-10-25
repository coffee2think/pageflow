package com.erl.pageflow;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.erl.pageflow.sales.model.service.SalesService;
import com.erl.pageflow.sales.model.vo.Rank;
import com.erl.pageflow.sales.model.vo.SalesStatistics;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	private SalesService salesService;

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		return "home";
	}
//
//	@RequestMapping("main.do")
//	@ResponseBody
//	public String forwardMainView()  throws UnsupportedEncodingException {
//		
//		int year = LocalDate.now().getYear();
//		ArrayList<SalesStatistics> statsList = salesService.selectSalesForStats(year);
//		
//		JSONObject sendJson = new JSONObject();
//		JSONArray jarr = new JSONArray();
//		for(SalesStatistics statsBook : statsList) {
//			JSONObject job = new JSONObject();
//
//		    job.put("salesMonth01", statsBook.getSalesMonth01());
//		    job.put("salesMonth02", statsBook.getSalesMonth02());
//		    job.put("salesMonth03", statsBook.getSalesMonth03());
//		    job.put("salesMonth04", statsBook.getSalesMonth04());
//		    job.put("salesMonth05", statsBook.getSalesMonth05());
//		    job.put("salesMonth06", statsBook.getSalesMonth06());
//		    job.put("salesMonth07", statsBook.getSalesMonth07());
//		    job.put("salesMonth08", statsBook.getSalesMonth08());
//		    job.put("salesMonth09", statsBook.getSalesMonth09());
//		    job.put("salesMonth10", statsBook.getSalesMonth10());
//		    job.put("salesMonth11", statsBook.getSalesMonth11());
//		    job.put("salesMonth12", statsBook.getSalesMonth12());
//		    
////			job.put("statsList", statsList);
//			jarr.add(job);
//		}
//		sendJson.put("statsList", jarr);
//		logger.info("statsList : " + statsList);
//		return sendJson.toJSONString();
//	}
	@RequestMapping("main.do")
	public String forwardMainView(){
		return "member/login";
	}
}
