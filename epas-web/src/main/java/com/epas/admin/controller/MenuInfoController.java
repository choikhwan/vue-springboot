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

import com.epas.admin.dto.MenuInfoDto;
import com.epas.admin.service.MenuInfoService;
import com.epas.common.dto.ComCdDto;
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
 * Menu Info Controller Class
 *
 * @since 2023. 6. 26.
 * @author choih
 * @see <pre>
 *  Class Name : MenuInfoController.java
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
public class MenuInfoController {

	@Autowired
    private MenuInfoService menuInfoService;	
	@Autowired
    private CommonService commonService;
    @Autowired
    MessageSource messageSource;	

    /**
    * Menu Management 화면 호출
    *
    * @method : menuInfoList
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
	@GetMapping("/admin/menuInfoList")
	public ModelAndView menuInfoList(HttpServletRequest request) {

		ModelAndView mav = new ModelAndView("admin/menuInfoList");

		if(request == null)
		{
			return mav;
		}

		//메뉴화면 권한조회
		UsrMenuRoleDto menuRole = commonService.getMenuRole(request);
		JSONArray MenuRole = JSONArray.fromObject(menuRole);		
		mav.addObject("menuRole", MenuRole);
		
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("comCdGrpId", "SEARCH_TYPE");
        map.put("lang", LocaleUtil.getLocale(request).toString());    
		List<ComCdDto> schTypelist = commonService.selectCmmCode(map);
		JSONArray schTypeList = JSONArray.fromObject(schTypelist);
		mav.addObject("scrTypelist", schTypeList);
		
		List<ComCdDto> parentlist = commonService.selectParentMenu(new ComCdDto());
		JSONArray parentList = JSONArray.fromObject(parentlist);
		mav.addObject("parentlist", parentList);
		
        map.put("comCdGrpId", "USE_YN");
        map.put("lang", LocaleUtil.getLocale(request).toString());    
		List<ComCdDto> useYnlist = commonService.selectCmmCode(map);
		JSONArray useYnList = JSONArray.fromObject(useYnlist);
		mav.addObject("useYnlist", useYnList);		
		
		return mav;
	}	

    /**
    * Menu List 조회
    *
    * @method : getMenuInfoList
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
	@PostMapping("/admin/getMenuInfoList")
	public ModelAndView getMenuInfoList(Model model, HttpServletRequest request) {

		if(request == null)
		{
			return null;
		}

		HttpSession session = request.getSession();
		MemberInfoDto vo = (MemberInfoDto)session.getAttribute("loginMember");

        String schType		= request.getParameter("schType");
        String schVal		= request.getParameter("schVal");
        String schParent	= request.getParameter("schParent");
        String schUseYn		= request.getParameter("schUseYn");
        String pageOffset 	= request.getParameter("skip");
        String pageSize 	= request.getParameter("take");
        String orderby 		= EpasUtil.replaceXssRemoveSqlInjection(request.getParameter("orderby"));	//그리드 헤더 클릭 소팅
        
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("schType", schType);
        map.put("schVal", schVal);
        map.put("schParent", schParent);
        map.put("schUseYn", schUseYn);
        map.put("pageOffset", pageOffset);
        map.put("pageSize"	, pageSize);
        map.put("orderby", orderby);

        map.put("roleseq", vo.getRoleSeq());

		List<MenuInfoDto> list = menuInfoService.selectMenuInfoList(map);
        int totalCount = menuInfoService.selectMenuInfoTotalCount(map);
		ModelAndView mav = new ModelAndView("jsonView");

		mav.addObject("data", list);
		mav.addObject("totalCount", totalCount);
		return mav;
	}

    /**
    * Menu Insert
    *
    * @method : insertMenuInfo
    * @date : 2023.06.26
    * @author : choih
    * @param : request
    * HttpServletRequest
    * @param : response
    * HttpServletResponse
    * @param : params
    * ResponseBody MenuInfoDto
    * @return String
    * @throws 
    *     << Modification History >>
    *     
    *     Date        Author       Description
    *     ---------   --------     --------------------
    *     2023.06.26  choih        initial
    */	
    @PostMapping("/admin/insertMenuInfo")
    @ResponseBody
    public String insertMenuInfo(@RequestBody MenuInfoDto params, HttpServletResponse response, HttpServletRequest request) throws Exception {

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
    		menuInfoService.insertMenuInfo(params);
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
    * Menu Update
    *
    * @method : updateMenuInfo
    * @date : 2023.06.26
    * @author : choih
    * @param : request
    * HttpServletRequest
    * @param : response
    * HttpServletResponse
    * @param : params
    * ResponseBody MenuInfoDto
    * @return String
    * @throws 
    *     << Modification History >>
    *     
    *     Date        Author       Description
    *     ---------   --------     --------------------
    *     2023.06.26  choih        initial
    */      
    @PostMapping("/admin/updateMenuInfo")
    @ResponseBody    
    public String updateMenuInfo(@RequestBody MenuInfoDto params, HttpServletResponse response, HttpServletRequest request) throws Exception {

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
    		menuInfoService.updateMenuInfo(params);
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
    * Menu Delete
    *
    * @method : deleteMenuInfo
    * @date : 2023.06.26
    * @author : choih
    * @param : request
    * HttpServletRequest
    * @param : response
    * HttpServletResponse
    * @param : params
    * ResponseBody MenuInfoDto
    * @return String
    * @throws 
    *     << Modification History >>
    *     
    *     Date        Author       Description
    *     ---------   --------     --------------------
    *     2023.06.26  choih        initial
    */ 
    @PostMapping("/admin/deleteMenuInfo")
    @ResponseBody
    public String deleteMenuInfo(@RequestBody MenuInfoDto params, HttpServletResponse response, HttpServletRequest request) throws Exception {

		if(request == null)
		{
			return null;
		}

    	JSONObject resMap = new JSONObject();

    	menuInfoService.deleteMenuInfo(params);
        resMap.put("res", "success");
        resMap.put("msg", messageSource.getMessage("message.common.delete",null,LocaleUtil.getLocale(request)));

        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(resMap);
	  
        return null;
	}
    
    /**
    * Menu Delete(use_yn = 'N'처리)
    *
    * @method : delMenuInfo
    * @date : 2023.06.26
    * @author : choih
    * @param : request
    * HttpServletRequest
    * @param : response
    * HttpServletResponse
    * @param : params
    * ResponseBody MenuInfoDto
    * @return String
    * @throws 
    *     << Modification History >>
    *     
    *     Date        Author       Description
    *     ---------   --------     --------------------
    *     2023.06.26  choih        initial
    */  
    @PostMapping("/admin/delMenuInfo")
    @ResponseBody
    public String delMenuInfo(@RequestBody MenuInfoDto params, HttpServletResponse response, HttpServletRequest request) throws Exception {

		if(request == null)
		{
			return null;
		}

    	JSONObject resMap = new JSONObject();

    	try {
    		HttpSession session = request.getSession();
    		MemberInfoDto vo = (MemberInfoDto)session.getAttribute("loginMember");
    		String loginId = vo.getUsrId();
    		params.setUpdatedUsrId(loginId); 
    		
    		menuInfoService.delMenuInfo(params);
            resMap.put("res", "success");
            resMap.put("msg", messageSource.getMessage("message.common.delete",null,LocaleUtil.getLocale(request)));
    	}catch(Exception e) {
    		
    	}

	  response.setContentType("text/html; charset=UTF-8");
	  PrintWriter out = response.getWriter();
	  out.print(resMap);
	  
	  return null;
	}
    
    /**
    * Menu ID 중복체크
    *
    * @method : checkMenuId
    * @date : 2023.06.26
    * @author : choih
    * @param : response
    * HttpServletResponse
    * @param : params
    * ResponseBody MenuInfoDto
    * @return String
    * @throws 
    *     << Modification History >>
    *     
    *     Date        Author       Description
    *     ---------   --------     --------------------
    *     2023.06.26  choih        initial
    */ 
    @PostMapping("/admin/checkMenuId")
    @ResponseBody
    public String checkMenuId(@RequestBody MenuInfoDto params, HttpServletResponse response) throws Exception {
    	JSONObject resMap = new JSONObject();
    	
		String chkMenuId = params.getMenuId();
		int chkCnt = menuInfoService.countByMenuId(chkMenuId);
        resMap.put("res", "success");
        resMap.put("chkCnt", chkCnt);
    	
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(resMap);
	  
        return null;
	}
   
}
