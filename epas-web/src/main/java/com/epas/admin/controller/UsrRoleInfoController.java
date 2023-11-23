package com.epas.admin.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
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

import com.epas.admin.dto.RoleMenuInfoDto;
import com.epas.admin.dto.UsrRoleInfoDto;
import com.epas.admin.service.UsrRoleInfoService;
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
 * 사용자 Role Info Controller Class
 *
 * @since 2023. 6. 26.
 * @author choih
 * @see <pre>
 *  Class Name : UsrRoleInfoController.java
 *  Description : User Role Info Management
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
public class UsrRoleInfoController {

	@Autowired
    private UsrRoleInfoService usrRoleInfoService;
	
	@Autowired
    private CommonService commonService;
	
    @Autowired
    MessageSource messageSource;		
	
    /**
    * Role Management 화면 호출
    *
    * @method : userRoleInfoList
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
	@GetMapping("/admin/userRoleInfoList")
	public ModelAndView userRoleInfoList(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("admin/userRoleInfoList");

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
		String roleSeq	= request.getParameter("roleSeq");
		map.put("roleSeq", roleSeq);

		List<RoleMenuInfoDto> roleMenuInfoList = usrRoleInfoService.selectRoleMenuInfoList(map);
		JSONArray roleMenuInfolist = JSONArray.fromObject(roleMenuInfoList);
		mav.addObject("roleMenuInfoList", roleMenuInfolist);
				
		return mav;
	}	

    /**
    * Role List 조회
    *
    * @method : getUsrRoleInfoList
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
	@PostMapping("/admin/getUsrRoleInfoList")
	public ModelAndView getUsrRoleInfoList(Model model, HttpServletRequest request) {

		if(request == null)
		{
			return null;
		}

        String schVal	= request.getParameter("schVal");
        String schUseYn	= request.getParameter("schUseYn");
        String pageOffset 	= request.getParameter("skip");
        String pageSize 	= request.getParameter("take");
        String orderby 		= EpasUtil.replaceXssRemoveSqlInjection(request.getParameter("orderby"));	//그리드 헤더 클릭 소팅
        
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("schVal", schVal);
        map.put("schUseYn", schUseYn);
        map.put("pageOffset", pageOffset);
        map.put("pageSize"	, pageSize);
        map.put("orderby", orderby);
        
		List<UsrRoleInfoDto> list = usrRoleInfoService.selectUsrRoleInfoList(map);
        int totalCount = usrRoleInfoService.selectUsrRoleInfoTotalCount(map);		
		
		ModelAndView mav = new ModelAndView("jsonView");
		mav.addObject("data", list);
		mav.addObject("totalCount", totalCount);
		
		return mav;
	}

    /**
    * Role Insert
    *
    * @method : insertUsrRoleInfo
    * @date : 2023.06.26
    * @author : choih
    * @param : request
    * HttpServletRequest
    * @param : response
    * HttpServletResponse
    * @param : params
    * ResponseBody UsrRoleInfoDto
    * @return String
    * @throws
    *     << Modification History >>
    *     
    *     Date        Author       Description
    *     ---------   --------     --------------------
    *     2023.06.26  choih        initial
    */
    @PostMapping("/admin/insertUsrRoleInfo")
    @ResponseBody
    public String insertUsrRoleInfo(@RequestBody UsrRoleInfoDto params, HttpServletResponse response, HttpServletRequest request) throws Exception {

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
    		usrRoleInfoService.insertUsrRoleInfo(params);
    		
    		//현재 생성된 roleSeq 조회(roleMenuInfo 키값)
    		String roleNm = params.getRoleNm();
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("roleNm", roleNm);
            long roleSeq = usrRoleInfoService.selectRoleSeq(map);
    		
    		ArrayList<RoleMenuInfoDto> roleMenuInfoList = params.getRoleMenuInfoList();
			for (int i = 0; i < roleMenuInfoList.size(); i++) {
	    		RoleMenuInfoDto roleMenuInfoDto = new RoleMenuInfoDto();
				roleMenuInfoDto = roleMenuInfoList.get(i);
				roleMenuInfoDto.setRoleSeq(roleSeq);
				roleMenuInfoDto.setCreatedUsrId(loginId);					
				roleMenuInfoDto.setUpdatedUsrId(loginId);
				usrRoleInfoService.insertMstRoleMenuInfo(roleMenuInfoDto);					
			}
			
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
    * Role Update
    *
    * @method : updateUsrRoleInfo
    * @date : 2023.06.26
    * @author : choih
    * @param : request
    * HttpServletRequest
    * @param : response
    * HttpServletResponse
    * @param : params
    * ResponseBody UsrRoleInfoDto
    * @return String
    * @throws 
    *     << Modification History >>
    *     
    *     Date        Author       Description
    *     ---------   --------     --------------------
    *     2023.06.26  choih        initial
    */        
    @PostMapping("/admin/updateUsrRoleInfo")
    @ResponseBody    
    public String updateUsrRoleInfo(@RequestBody UsrRoleInfoDto params, HttpServletResponse response, HttpServletRequest request) throws Exception {

		if(request == null)
		{
			return null;
		}

    	JSONObject resMap = new JSONObject();
		HttpSession session = request.getSession();
		MemberInfoDto vo = (MemberInfoDto)session.getAttribute("loginMember");
		String loginId = vo.getUsrId();
		Long loginRoleSeq = vo.getRoleSeq();
		
    	try {
			// 시스템관리자가 아니면 시스템관리자 권한수정 불가처리
			if(loginRoleSeq != 4)
			{
	            resMap.put("res", "fail");
	            resMap.put("msg", messageSource.getMessage("message.alert_0012", null, LocaleUtil.getLocale(request)));
			}
			else
			{
	    		params.setUpdatedUsrId(loginId);      		
	    		usrRoleInfoService.updateUsrRoleInfo(params);
	    		long roleSeq = params.getRoleSeq();
	    		
	    		ArrayList<RoleMenuInfoDto> roleMenuInfoList = params.getRoleMenuInfoList();
				for (int i = 0; i < roleMenuInfoList.size(); i++) {
		    		RoleMenuInfoDto roleMenuInfoDto = new RoleMenuInfoDto();
					roleMenuInfoDto = roleMenuInfoList.get(i);
					roleMenuInfoDto.setRoleSeq(roleSeq);
					long roleMeueSeq = roleMenuInfoDto.getRoleMenuSeq();
					if(roleMeueSeq > 0) {
						//화면에서 수정된 데이터만 업데이트 한다.
						String modfyFlag = roleMenuInfoDto.getPath();
						if("EDIT".equals(modfyFlag)) {
							roleMenuInfoDto.setUpdatedUsrId(loginId);
							usrRoleInfoService.updateMstRoleMenuInfo(roleMenuInfoDto);						
						}
					}else {
						roleMenuInfoDto.setCreatedUsrId(loginId);					
						roleMenuInfoDto.setUpdatedUsrId(loginId);
						usrRoleInfoService.insertMstRoleMenuInfo(roleMenuInfoDto);					
					}
				}
	    		
	            resMap.put("res", "success");
	            resMap.put("msg", messageSource.getMessage("message.common.update",null,LocaleUtil.getLocale(request)));
			}
    	}catch(Exception e) {
		
    	}

	  response.setContentType("text/html; charset=UTF-8");
	  PrintWriter out = response.getWriter();
	  out.print(resMap);
	  
	  return null;
	}
    
    /**
    * Role Delete
    *
    * @method : deleteUsrRoleInfo
    * @date : 2023.06.26
    * @author : choih
    * @param : request
    * HttpServletRequest
    * @param : response
    * HttpServletResponse
    * @param : params
    * ResponseBody UsrRoleInfoDto
    * @return String
    * @throws 
    *     << Modification History >>
    *     
    *     Date        Author       Description
    *     ---------   --------     --------------------
    *     2023.06.26  choih        initial
    */   
    @PostMapping("/admin/deleteUsrRoleInfo")
    @ResponseBody
    public String deleteUsrRoleInfo(@RequestBody UsrRoleInfoDto params, HttpServletResponse response, HttpServletRequest request) throws Exception {

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
    		
    		usrRoleInfoService.deleteUsrRoleInfo(params);
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
    * Role Delete(use_yn = 'N'처리)
    *
    * @method : delUsrRoleInfo
    * @date : 2023.06.26
    * @author : choih
    * @param : request
    * HttpServletRequest
    * @param : response
    * HttpServletResponse
    * @param : params
    * ResponseBody UsrRoleInfoDto
    * @return String
    * @throws 
    *     << Modification History >>
    *     
    *     Date        Author       Description
    *     ---------   --------     --------------------
    *     2023.06.26  choih        initial
    */  
    @PostMapping("/admin/delUsrRoleInfo")
    @ResponseBody
    public String delUsrRoleInfo(@RequestBody UsrRoleInfoDto params, HttpServletResponse response, HttpServletRequest request) throws Exception {

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
    		
    		usrRoleInfoService.delUsrRoleInfo(params);
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
    * Role 명 중복체크
    *
    * @method : checkUsrRoleNm
    * @date : 2023.06.26
    * @author : choih
    * @param : response
    * HttpServletResponse
    * @param : params
    * ResponseBody UsrRoleInfoDto
    * @return String
    * @throws 
    *     << Modification History >>
    *     
    *     Date        Author       Description
    *     ---------   --------     --------------------
    *     2023.06.26  choih        initial
    */  
    @PostMapping("/admin/checkUsrRoleNm")
    @ResponseBody
    public String checkUsrRoleNm(@RequestBody UsrRoleInfoDto params, HttpServletResponse response) throws Exception {
		JSONObject resMap = new JSONObject();
		
		String chkRoleNm = params.getRoleNm();
		int chkCnt = usrRoleInfoService.countByUsrRoleNm(chkRoleNm);
	    resMap.put("res", "success");
	    resMap.put("chkCnt", chkCnt);
	
	    response.setContentType("text/html; charset=UTF-8");
	    PrintWriter out = response.getWriter();
	    out.print(resMap);
	  
	    return null;
	}

    /**
     * Role Menu Info List. 
     *
     * @method : getRoleMenuInfoList
     * @date : 20230725
     * @author : choih
     * @param : response
	 * HTTP 응답 메시지 생성
	 * @param : request
	 * HTTP 요청 메시지 생성
     * 
     * @return Role Menu Info List
     * @throws 예외가 있다면 예외 클래스 및 설명
     *     << Modification History >>
     *     
     *     Date        Author       Description
     *     ---------   --------     --------------------
     *     20230725     choih       신규생성
     */    
	@PostMapping("/admin/getRoleMenuInfoList")
    @ResponseBody	
	public String getRoleMenuInfoList(HttpServletResponse response, HttpServletRequest request) throws Exception {
		JSONObject resMap = new JSONObject();
		
    	try {
    		HttpSession session = request.getSession();
    		MemberInfoDto vo = (MemberInfoDto)session.getAttribute("loginMember");

			String roleSeq	= request.getParameter("roleSeq");
	        
	        HashMap<String, Object> map = new HashMap<String, Object>();
	        map.put("roleSeq", roleSeq);

	        map.put("roleseq", vo.getRoleSeq());
	        
			List<RoleMenuInfoDto> roleMenuInfoList = usrRoleInfoService.selectRoleMenuInfoList(map);
			JSONArray roleMenuInfolist = JSONArray.fromObject(roleMenuInfoList);

			resMap.put("roleMenuInfoList", roleMenuInfolist);
            resMap.put("res", "success");
            resMap.put("msg", messageSource.getMessage("message.common.select",null,LocaleUtil.getLocale(request))); //정상 조회되었습니다.
    	}catch(Exception e) {
    		resMap.put("res", "fail");
            resMap.put("msg", e.getMessage());
    	}
    	
    	response.setContentType("text/html; charset=UTF-8");
  	  	PrintWriter out = response.getWriter();
  	  	out.print(resMap);
  	  
  	  	return null;
	}
}
