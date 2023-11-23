package com.epas.admin.controller;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.epas.admin.dto.UsrGroupInfoDto;
import com.epas.admin.service.UsrGroupInfoService;
import com.epas.common.dto.UsrMenuRoleDto;
import com.epas.common.service.CommonService;
import com.epas.common.utl.EpasUtil;
import com.epas.common.utl.LocaleUtil;
import com.epas.login.dto.MemberInfoDto;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import net.sf.json.JSONArray;


/**
 * 사용자 그룹 Info Controller Class
 *
 * @since 2023. 6. 26.
 * @author choih
 * @see <pre>
 *  Class Name : UsrGroupInfoController.java
 *  Description : Menu Info Management
 *
 *  << Modification History >>
 *  
 *  Date              Modifier           Description
 *  ----------        -----------        ----------------------
 *  2023.06.26        choih              initial
 *
 © Hitachi High-Tech Corporation.  2023. All rights reserved.
 *  </pre>
 */
@Controller
@RequiredArgsConstructor
public class UsrGroupInfoController {

	@Autowired
    private UsrGroupInfoService usrGroupInfoService;	
	
	@Autowired
    private CommonService commonService;
	
    @Autowired
    MessageSource messageSource;
	
    /**
    * Group Management 화면 호출
    *
    * @method : userGroupInfoList
    * @date : 2023.06.26
    * @author : choih
    * @param : request
    * HttpServletRequest
    * @return ModelAndView
    * @throws 
    *     << Modification History >>
    *     
    *     Date        Author       Description
    *     ---------   --------     --------------------
    *     2023.06.26  choih        initial
    */
	@GetMapping("/admin/userGroupInfoList")
	public ModelAndView userGroupInfoList(HttpServletRequest request){
		ModelAndView mav = new ModelAndView("admin/userGroupInfoList");

		if(request == null)
		{
			return mav;
		}

		//메뉴화면 권한조회
		UsrMenuRoleDto menuRole = commonService.getMenuRole(request);
		JSONArray MenuRole = JSONArray.fromObject(menuRole);		
		mav.addObject("menuRole", MenuRole);		
		return mav;
	}	

    /**
    * Group List 조회
    *
    * @method : getUsrGroupInfoList
    * @date : 2023.06.26
    * @author : choih
    * @param : request
    * HttpServletRequest
    * @return ModelAndView
    * @throws 
    *     << Modification History >>
    *     
    *     Date        Author       Description
    *     ---------   --------     --------------------
    *     2023.06.26  choih        initial
    */
	@PostMapping("/admin/getUsrGroupInfoList")
	public ModelAndView getUsrGroupInfoList(Model model, HttpServletRequest request) {

		if(request == null)
		{
			return null;
		}

        String schVal	= request.getParameter("schVal");
        String pageOffset 	= request.getParameter("skip");
        String pageSize 	= request.getParameter("take");
        String orderby 		= EpasUtil.replaceXssRemoveSqlInjection(request.getParameter("orderby"));	//그리드 헤더 클릭 소팅
        
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("schVal", schVal);
        map.put("pageOffset", pageOffset);
        map.put("pageSize"	, pageSize);
        map.put("orderby", orderby);
        
		List<UsrGroupInfoDto> list = usrGroupInfoService.selectUsrGroupInfoList(map);
        int totalCount = usrGroupInfoService.selectUsrGroupInfoTotalCount(map);
		
		ModelAndView mav = new ModelAndView("jsonView");
		mav.addObject("data", list);
		mav.addObject("totalCount", totalCount);
		return mav;
	}

    /**
    * Group Insert
    *
    * @method : insertUsrGroupInfo
    * @date : 2023.06.26
    * @author : choih
    * @param : request
    * HttpServletRequest
    * @param : response
    * HttpServletResponse
    * @param : params
    * ResponseBody UsrGroupInfoDto
    * @return String
    * @throws 
    *     << Modification History >>
    *     
    *     Date        Author       Description
    *     ---------   --------     --------------------
    *     2023.06.26  choih        initial
    */
    @PostMapping("/admin/insertUsrGroupInfo")
    @ResponseBody
    public String insertUsrGroupInfo(@RequestBody UsrGroupInfoDto params, HttpServletResponse response, HttpServletRequest request) throws Exception {

		if(request == null)
		{
			return null;
		}

    	JSONObject resMap = new JSONObject();
		HttpSession session = request.getSession();
		MemberInfoDto vo = (MemberInfoDto)session.getAttribute("loginMember");
		String loginId = vo.getUsrId();
		
    	try {
    		params.setCreatedUsrId(loginId);
    		params.setUpdatedUsrId(loginId);    		
    		usrGroupInfoService.insertUsrGroupInfo(params);
            resMap.put("res", "success");
            resMap.put("msg", messageSource.getMessage("message.common.insert",null,LocaleUtil.getLocale(request)));
    	}catch(Exception e) {
    		
    	}

	  response.setContentType("text/html; charset=UTF-8");
	  PrintWriter out = response.getWriter();
	  out.print(resMap);
	  
	  return null;
	}

    /**
    * Group Update
    *
    * @method : updateUsrGroupInfo
    * @date : 2023.06.26
    * @author : choih
    * @param : request
    * HttpServletRequest
    * @param : response
    * HttpServletResponse
    * @param : params
    * ResponseBody UsrGroupInfoDto
    * @return String
    * @throws 
    *     << Modification History >>
    *     
    *     Date        Author       Description
    *     ---------   --------     --------------------
    *     2023.06.26  choih        initial
    */   
    @PostMapping("/admin/updateUsrGroupInfo")
    @ResponseBody    
    public String updateUsrGroupInfo(@RequestBody UsrGroupInfoDto params, HttpServletResponse response, HttpServletRequest request) throws Exception {

		if(request == null)
		{
			return null;
		}

    	JSONObject resMap = new JSONObject();
		HttpSession session = request.getSession();
		MemberInfoDto vo = (MemberInfoDto)session.getAttribute("loginMember");
		String loginId = vo.getUsrId();
		
    	try {
    		params.setUpdatedUsrId(loginId);      		
    		usrGroupInfoService.updateUsrGroupInfo(params);
            resMap.put("res", "success");
            resMap.put("msg", messageSource.getMessage("message.common.update",null,LocaleUtil.getLocale(request)));
    	}catch(Exception e) {
		
    	}

	  response.setContentType("text/html; charset=UTF-8");
	  PrintWriter out = response.getWriter();
	  out.print(resMap);
	  
	  return null;
	}
    
    /**
    * Group Delete
    *
    * @method : deleteUsrGroupInfo
    * @date : 2023.06.26
    * @author : choih
    * @param : request
    * HttpServletRequest
    * @param : response
    * HttpServletResponse
    * @param : params
    * ResponseBody UsrGroupInfoDto
    * @return String
    * @throws 
    *     << Modification History >>
    *     
    *     Date        Author       Description
    *     ---------   --------     --------------------
    *     2023.06.26  choih        initial
    */     
    @PostMapping("/admin/deleteUsrGroupInfo")
    @ResponseBody
    public String deleteUsrGroupInfo(@RequestBody UsrGroupInfoDto params, HttpServletResponse response, HttpServletRequest request) throws Exception {

		if(request == null)
		{
			return null;
		}

    	JSONObject resMap = new JSONObject();

		usrGroupInfoService.deleteUsrGroupInfo(params);
        resMap.put("res", "success");
        resMap.put("msg", messageSource.getMessage("message.common.delete",null,LocaleUtil.getLocale(request)));

        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(resMap);
	  
        return null;
	}

    /**
    * Group 명 중복체크
    *
    * @method : checkUsrGroupNm
    * @date : 2023.06.26
    * @author : choih
    * @param : response
    * HttpServletResponse
    * @param : params
    * ResponseBody UsrGroupInfoDto
    * @return String
    * @throws 
    *     << Modification History >>
    *     
    *     Date        Author       Description
    *     ---------   --------     --------------------
    *     2023.06.26  choih        initial
    */ 
    @PostMapping("/admin/checkUsrGroupNm")
    @ResponseBody
    public String checkUsrGroupNm(@RequestBody UsrGroupInfoDto params, HttpServletResponse response) throws Exception {
    	JSONObject resMap = new JSONObject();

		String chkUsrGroupNm = params.getUsrGroupNm();
		int chkCnt = usrGroupInfoService.countByUsrGroupNm(chkUsrGroupNm);
        resMap.put("res", "success");
        resMap.put("chkCnt", chkCnt);

        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(resMap);
	  
        return null;
	}

}
