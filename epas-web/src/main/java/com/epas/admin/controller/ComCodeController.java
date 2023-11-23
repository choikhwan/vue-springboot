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

import com.epas.admin.dto.ComCdDetailInfoDto;
import com.epas.admin.dto.ComCdGrpInfoDto;
import com.epas.admin.service.CmmCodeService;
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
 * Com Code Controller Class
 *
 * @since 2023. 6. 26.
 * @author choih
 * @see <pre>
 *  Class Name : ComCodeController.java
 *  Description : Com Code Management
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
public class ComCodeController {

	@Autowired
    private CmmCodeService cmmCodeService;	

	@Autowired
    private CommonService commonService;
	
    @Autowired
    MessageSource messageSource;	
    
    /**
    * Common Code Group 화면 호출
    *
    * @method : comCdGrpInfoList
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
	@GetMapping("/admin/comCdGrpInfoList")
	public ModelAndView comCdGrpInfoList(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("admin/comCdGrpInfoList");

		if(request == null)
		{
			return mav;
		}

		//메뉴화면 권한조회
		UsrMenuRoleDto menuRole = commonService.getMenuRole(request);
		JSONArray MenuRole = JSONArray.fromObject(menuRole);		
		mav.addObject("menuRole", MenuRole);	
		
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("comCdGrpId", "USE_YN");
        map.put("lang", LocaleUtil.getLocale(request).toString());
        
		List<ComCdDto> useYnlist = commonService.selectCmmCode(map);
		JSONArray useYnList = JSONArray.fromObject(useYnlist);
		mav.addObject("useYnlist", useYnList);

		map.clear();
		map.put("comCdGrpId", "SEARCH_TYPE");
        map.put("lang", LocaleUtil.getLocale(request).toString());
		List<ComCdDto> schTypelist = commonService.selectCmmCode(map);
		JSONArray schTypeList = JSONArray.fromObject(schTypelist);
		mav.addObject("scrTypelist", schTypeList);
		
		return mav;
	}	

    /**
    * Common Code Group List 조회
    *
    * @method : getComCdGrpInfoList
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
	@PostMapping("/admin/getComCdGrpInfoList")
	public ModelAndView getComCdGrpInfoList(Model model, HttpServletRequest request) {

		if(model == null)
		{
			return null;
		}

        String schType	= request.getParameter("schType");
        String schVal	= request.getParameter("schVal");
        String schUseYn	= request.getParameter("schUseYn");
        String pageOffset 	= request.getParameter("skip");
        String pageSize 	= request.getParameter("take");
        String orderby 	= EpasUtil.replaceXssRemoveSqlInjection(request.getParameter("orderby"));	//그리드 헤더 클릭 소팅
        
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("schType", schType);
        map.put("schVal", schVal);
        map.put("schUseYn", schUseYn);
        map.put("pageOffset", pageOffset);
        map.put("pageSize"	, pageSize);
        map.put("orderby", orderby);
        
		List<ComCdGrpInfoDto> list = cmmCodeService.selectComCdGrpInfoList(map);
        int totalCount = cmmCodeService.selectComCdGrpInfoTotalCount(map);
		
		ModelAndView mav = new ModelAndView("jsonView");
		mav.addObject("data", list);
		mav.addObject("totalCount", totalCount);
		return mav;
	}

    /**
    * Common Code Group Insert
    *
    * @method : insertComCdGrpInfo
    * @date : 2023.06.26
    * @author : choih
    * @param : request
    * HttpServletRequest
    * @param : response
    * HttpServletResponse
    * @param : params
    * ResponseBody ComCdGrpInfoDto
    * @return String
    * @throws 
    *     << Modification History >>
    *     
    *     Date        Author       Description
    *     ---------   --------     --------------------
    *     2023.06.26  choih        initial
    */
    @PostMapping("/admin/insertComCdGrpInfo")
    @ResponseBody
    public String insertComCdGrpInfo(@RequestBody ComCdGrpInfoDto params, HttpServletResponse response, HttpServletRequest request) throws Exception {

		if(request == null)
		{
			return "";
		}

    	JSONObject resMap = new JSONObject();
		HttpSession session = request.getSession();
		MemberInfoDto vo = (MemberInfoDto)session.getAttribute("loginMember");
		String loginId = vo.getUsrId();
		
    	try {
    		params.setCreatedUsrId(loginId);
    		params.setUpdatedUsrId(loginId);    		
    		cmmCodeService.insertComCdGrpInfo(params);
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
    * Common Code Group Update
    *
    * @method : updateComCdGrpInfo
    * @date : 2023.06.26
    * @author : choih
    * @param : request
    * HttpServletRequest
    * @param : response
    * HttpServletResponse
    * @param : params
    * ResponseBody ComCdGrpInfoDto
    * @return String
    * @throws 
    *     << Modification History >>
    *     
    *     Date        Author       Description
    *     ---------   --------     --------------------
    *     2023.06.26  choih        initial
    */    
    @PostMapping("/admin/updateComCdGrpInfo")
    @ResponseBody    
    public String updateComCdGrpInfo(@RequestBody ComCdGrpInfoDto params, HttpServletResponse response, HttpServletRequest request) throws Exception {

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
    		cmmCodeService.updateComCdGrpInfo(params);
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
    * Common Code Group Delete
    *
    * @method : deleteComCdGrpInfo
    * @date : 2023.06.26
    * @author : choih
    * @param : request
    * HttpServletRequest
    * @param : response
    * HttpServletResponse
    * @param : params
    * ResponseBody ComCdGrpInfoDto
    * @return String
    * @throws 
    *     << Modification History >>
    *     
    *     Date        Author       Description
    *     ---------   --------     --------------------
    *     2023.06.26  choih        initial
    */ 
    @PostMapping("/admin/deleteComCdGrpInfo")
    @ResponseBody
    public String deleteComCdGrpInfo(@RequestBody ComCdGrpInfoDto params, HttpServletResponse response, HttpServletRequest request) throws Exception {

		if(request == null)
		{
			return null;
		}

    	JSONObject resMap = new JSONObject();
    		
        String comCdGrpId 	= params.getComCdGrpId();
        
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("comCdGrpId", comCdGrpId);
		int codeCount = cmmCodeService.checkComCdIdCnt(map);
		
		if(codeCount<=0) {
    		cmmCodeService.deleteComCdGrpInfo(params);
            resMap.put("res", "success");
            resMap.put("msg", messageSource.getMessage("message.common.delete",null,LocaleUtil.getLocale(request)));
		}else {
            resMap.put("res", "fail");
            resMap.put("msg", messageSource.getMessage("message.common.alert.existComCode",null,LocaleUtil.getLocale(request)));    			
		}
    		
	  response.setContentType("text/html; charset=UTF-8");
	  PrintWriter out = response.getWriter();
	  out.print(resMap);
	  
	  return null;
	}
    
    /**
    * Common Code Group ID 중복체크
    *
    * @method : checkComCdGrpId
    * @date : 2023.06.26
    * @author : choih
    * @param : response
    * HttpServletResponse
    * @param : params
    * ResponseBody ComCdGrpInfoDto
    * @return String
    * @throws 
    *     << Modification History >>
    *     
    *     Date        Author       Description
    *     ---------   --------     --------------------
    *     2023.06.26  choih        initial
    */ 
    @PostMapping("/admin/checkComCdGrpId")
    @ResponseBody
    public String checkComCdGrpId(@RequestBody ComCdGrpInfoDto params, HttpServletResponse response) throws Exception {
    	JSONObject resMap = new JSONObject();

    	String chkComCdGrpId = params.getComCdGrpId();
		int chkCnt = cmmCodeService.countByComCdGrpId(chkComCdGrpId);
		resMap.put("res", "success");
		resMap.put("chkCnt", chkCnt);
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print(resMap);
		  
		return null;
	}
    
    
    /**
    * Common Code 화면 호출
    *
    * @method : comCdDetailInfoList
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
    @GetMapping("/admin/comCdDetailInfoList")
	public ModelAndView comCdDetailInfoList(HttpServletRequest request) {

		ModelAndView mav = new ModelAndView("admin/comCdDetailInfoList");

		if(request == null)
		{
			return mav;
		}
		
		//메뉴화면 권한조회
		UsrMenuRoleDto menuRole = commonService.getMenuRole(request);
		JSONArray MenuRole = JSONArray.fromObject(menuRole);		
		mav.addObject("menuRole", MenuRole);	
		
		String lang = LocaleUtil.getLocale(request).toString();
		
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("comCdGrpId", "SEARCH_TYPE");
        map.put("lang", lang);
		List<ComCdDto> schTypelist = commonService.selectCmmCode(map);
		JSONArray schTypeList = JSONArray.fromObject(schTypelist);
		mav.addObject("scrTypelist", schTypeList);
		
		map.clear();
        map.put("comCdGrpId", "USE_YN");
        map.put("lang", lang);        
		List<ComCdDto> useYnlist = commonService.selectCmmCode(map);
		JSONArray useYnList = JSONArray.fromObject(useYnlist);
		mav.addObject("useYnlist", useYnList);

		map.clear();
        map.put("lang", lang);
		List<ComCdDto> cmmCdGrplist = commonService.selectCmmCdGrp(map);
		JSONArray cmmCdGrpList = JSONArray.fromObject(cmmCdGrplist);
		mav.addObject("cmmCdGrplist", cmmCdGrpList);
		
		List<ComCdDto> parentComCdlist = commonService.selectParentComCd(map);
		JSONArray parentComCdList = JSONArray.fromObject(parentComCdlist);
		mav.addObject("parentComCdlist", parentComCdList);		
		
		
		return mav;
	}	

    /**
    * Common Code List 조회
    *
    * @method : getComCdDetailInfoList
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
	@PostMapping("/admin/getComCdDetailInfoList")
	public ModelAndView getComCdDetailInfoList(Model model, HttpServletRequest request) {

		if(request == null)
		{
			return null;
		}

        String schGroup	= request.getParameter("schGroup");
        String schType	= request.getParameter("schType");
        String schVal	= request.getParameter("schVal");
        String schUseYn	= request.getParameter("schUseYn");
        String pageOffset 	= request.getParameter("skip");
        String pageSize 	= request.getParameter("take");
        String orderby 	= EpasUtil.replaceXssRemoveSqlInjection(request.getParameter("orderby"));	//그리드 헤더 클릭 소팅
        
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("schGroup", schGroup);
        map.put("schType", schType);
        map.put("schVal", schVal);
        map.put("schUseYn", schUseYn);
        map.put("pageOffset", pageOffset);
        map.put("pageSize"	, pageSize);
        map.put("orderby", orderby);
        
		List<ComCdDetailInfoDto> list = cmmCodeService.selectComCdDetailInfoList(map);
        int totalCount = cmmCodeService.selectComCdDetailInfoTotalCount(map);
		
		ModelAndView mav = new ModelAndView("jsonView");
		mav.addObject("data", list);
		mav.addObject("totalCount", totalCount);
		return mav;
	}

    /**
    * Common Code Insert
    *
    * @method : insertComCdDetailInfo
    * @date : 2023.06.26
    * @author : choih
    * @param : request
    * HttpServletRequest
    * @param : response
    * HttpServletResponse
    * @param : params
    * ResponseBody ComCdDetailInfoDto
    * @return String
    * @throws 
    *     << Modification History >>
    *     
    *     Date        Author       Description
    *     ---------   --------     --------------------
    *     2023.06.26  choih        initial
    */
    @PostMapping("/admin/insertComCdDetailInfo")
    @ResponseBody
    public String insertComCdDetailInfo(@RequestBody ComCdDetailInfoDto params, HttpServletResponse response, HttpServletRequest request) throws Exception {

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
    		cmmCodeService.insertComCdDetailInfo(params);
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
    * Common Code Update
    *
    * @method : updateComCdDetailInfo
    * @date : 2023.06.26
    * @author : choih
    * @param : request
    * HttpServletRequest
    * @param : response
    * HttpServletResponse
    * @param : params
    * ResponseBody ComCdDetailInfoDto
    * @return String
    * @throws 
    *     << Modification History >>
    *     
    *     Date        Author       Description
    *     ---------   --------     --------------------
    *     2023.06.26  choih        initial
    */   
    @PostMapping("/admin/updateComCdDetailInfo")
    @ResponseBody    
    public String updateComCdDetailInfo(@RequestBody ComCdDetailInfoDto params, HttpServletResponse response, HttpServletRequest request) throws Exception {

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
    		cmmCodeService.updateComCdDetailInfo(params);
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
    * Common Code Delete
    *
    * @method : deleteComCdDetailInfo
    * @date : 2023.06.26
    * @author : choih
    * @param : request
    * HttpServletRequest
    * @param : response
    * HttpServletResponse
    * @param : params
    * ResponseBody ComCdDetailInfoDto
    * @return String
    * @throws 
    *     << Modification History >>
    *     
    *     Date        Author       Description
    *     ---------   --------     --------------------
    *     2023.06.26  choih        initial
    */ 
    @PostMapping("/admin/deleteComCdDetailInfo")
    @ResponseBody
    public String deleteComCdDetailInfo(@RequestBody ComCdDetailInfoDto params, HttpServletResponse response, HttpServletRequest request) throws Exception {

		if(request == null)
		{
			return null;
		}

    	JSONObject resMap = new JSONObject();
    	
		cmmCodeService.deleteComCdDetailInfo(params);
        resMap.put("res", "success");
        resMap.put("msg", messageSource.getMessage("message.common.delete",null,LocaleUtil.getLocale(request)));
    	
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(resMap);
	  
        return null;
	}
    
    /**
    * Common Code ID 중복체크
    *
    * @method : checkComCdId
    * @date : 2023.06.26
    * @author : choih
    * @param : response
    * HttpServletResponse
    * @param : params
    * ResponseBody ComCdDetailInfoDto
    * @return String
    * @throws 
    *     << Modification History >>
    *     
    *     Date        Author       Description
    *     ---------   --------     --------------------
    *     2023.06.26  choih        initial
    */ 
    @PostMapping("/admin/checkComCdId")
    @ResponseBody
    public String checkComCdId(@RequestBody ComCdDetailInfoDto params, HttpServletResponse response) throws Exception {
    	JSONObject resMap = new JSONObject();

    	int chkCnt = cmmCodeService.countByComCdId(params);
        resMap.put("res", "success");
        resMap.put("chkCnt", chkCnt);

        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(resMap);
	  
        return null;
	}
}
