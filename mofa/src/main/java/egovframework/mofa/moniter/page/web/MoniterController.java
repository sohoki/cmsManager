package egovframework.mofa.moniter.page.web;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import egovframework.mofa.moniter.page.service.StaticMoniterManagerService;
import egovframework.mofa.moniter.page.vo.StateInfo;



@RestController
@RequestMapping("/sub")
public class MoniterController {

	
private static final Logger LOGGER = LoggerFactory.getLogger(MoniterController.class);
	
	

	@Autowired
	private StaticMoniterManagerService staticService;
	
	@RequestMapping(value="page00")
	public ModelAndView selectMoniterPage00(@ModelAttribute("stateinfo") StateInfo stateinfo    )throws Exception {
		
		ModelAndView model = new ModelAndView("/sub/page00");
		try{
			model.setViewName("/sub/page00");
			LOGGER.debug("page00 넘기기");
		}catch(Exception e){
			LOGGER.error("error:" + e.toString());
		}
		
		return model;
	}
	@RequestMapping(value="page01")
	public ModelAndView selectMoniterPage01(@ModelAttribute("stateinfo") StateInfo stateinfo    )throws Exception {
		ModelAndView model = new ModelAndView("/sub/page01");
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy- MM-dd/HH:mm");
		String datestr = sdf.format(cal.getTime());
		String[] dayInfo = datestr.split("/");
		
		model.addObject("pagelist", staticService.selectPageUni01());
		model.addObject("today", dayInfo[0]);
		model.addObject("nowTime", dayInfo[1]);
		return model;
	}
	
	@RequestMapping(value="page02")
	public ModelAndView selectMoniterPage02(@ModelAttribute("stateinfo") StateInfo stateinfo    )throws Exception {
		ModelAndView model = new ModelAndView("/sub/page02");
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy- MM-dd/HH:mm");
		String datestr = sdf.format(cal.getTime());
		String[] dayInfo = datestr.split("/");
		
		model.addObject("pagelist", staticService.selectPageUni02());
		model.addObject("today", dayInfo[0]);
		model.addObject("nowTime", dayInfo[1]);
		
		return model;
	}
	@RequestMapping(value="page03")
	public ModelAndView selectMoniterPage03(@ModelAttribute("stateinfo") StateInfo stateinfo    )throws Exception {
		ModelAndView model = new ModelAndView("/sub/page03");
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy- MM-dd/HH:mm");
		String datestr = sdf.format(cal.getTime());
		String[] dayInfo = datestr.split("/");
		
		model.addObject("pagelist", staticService.selectPageUni07());
		model.addObject("today", dayInfo[0]);
		model.addObject("nowTime", dayInfo[1]);
		
		return model;
	}
	@RequestMapping(value="page04")
	public ModelAndView selectMoniterPage04(@ModelAttribute("stateinfo") StateInfo stateinfo    )throws Exception {
		ModelAndView model = new ModelAndView("/sub/page04");
		
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy- MM-dd/HH:mm");
		String datestr = sdf.format(cal.getTime());
		String[] dayInfo = datestr.split("/");
		
		model.addObject("pagelist", staticService.selectPageUni08());
		model.addObject("today", dayInfo[0]);
		model.addObject("nowTime", dayInfo[1]);
		return model;
	}
	@RequestMapping(value="page05")
	public ModelAndView selectMoniterPage05(@ModelAttribute("stateinfo") StateInfo stateinfo    )throws Exception {
		ModelAndView model = new ModelAndView("/sub/page05");
		
		model.addObject("pagelist", staticService.selectPageUni05());
		return model;
	}
	@RequestMapping(value="page06")
	public ModelAndView selectMoniterPage06(@ModelAttribute("stateinfo") StateInfo stateinfo    )throws Exception {
		ModelAndView model = new ModelAndView("/sub/page06");
		
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy- MM-dd/HH:mm");
		String datestr = sdf.format(cal.getTime());
		String[] dayInfo = datestr.split("/");
		
		model.addObject("pagelist", staticService.selectPageUni06());
		model.addObject("today", dayInfo[0]);
		model.addObject("nowTime", dayInfo[1]);
		return model;
	}
	
	@RequestMapping(value="pageMini")
	public ModelAndView selectMoniterPage05(@ModelAttribute("stateinfo") StateInfo stateinfo ,
			                                                     HttpServletRequest request)throws Exception {
		ModelAndView model = new ModelAndView("/sub/mini_state");
		String agentid = request.getParameter("agentid") != null ? request.getParameter("agentid") : "";
		if (!agentid.equals("")){
			model.addObject("pageInfo", staticService.selectPageUni03(agentid));
			model.addObject("agentState", staticService.selectPageUni04());
			model.addObject("status", "SUCCESS");
		}else {
			model.addObject("status", "ID FAIL");	
		}
		
		return model;
	}
	
	@RequestMapping(value="pageTest")
	public ModelAndView selectMoniterBatch(@ModelAttribute("stateinfo") StateInfo stateinfo    )throws Exception {
		ModelAndView model = new ModelAndView("jsonView");
		LOGGER.debug("--------------------------------------------------------------------------------");
		model = staticService.batchTest();
		model.addObject("status",  model);
		return model;
	}
}
