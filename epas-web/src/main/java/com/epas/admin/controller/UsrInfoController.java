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

import com.epas.admin.dto.UsrInfoDto;
import com.epas.admin.service.UsrInfoService;
import com.epas.common.dto.CmmCodeSeqDto;
import com.epas.common.dto.ComCdDto;
import com.epas.common.dto.UsrMenuRoleDto;
import com.epas.common.service.CommonService;
import com.epas.common.utl.EpasUtil;
import com.epas.common.utl.LocaleUtil;
import com.epas.login.controller.UserSha256;
import com.epas.login.dto.MemberInfoDto;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import net.sf.json.JSONArray;


/**
 * User Info Controller Class
 *
 * @since 2023. 6. 13.
 * @author choih
 * @see <pre>
 *  Class Name : UsrInfoController.java
 *  Description : devexpress sample grid crud
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
public class UsrInfoController {

	@Autowired
	private UsrInfoService usrInfoService;
	
	@Autowired
    private CommonService commonService;
	
    @Autowired
    MessageSource messageSource;	
    
    /**
    * User Management 화면 호출
    *
    * @method : userList
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
	@GetMapping("/admin/userList")
	public ModelAndView userList(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("jsonView");
		
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
	//	JSONArray schTypeList = JSONArray.fromObject(schTypelist);
		mav.addObject("scrTypelist", schTypelist);

		List<CmmCodeSeqDto> userGrouplist = commonService.selectUserGroup(new CmmCodeSeqDto());
	//	JSONArray userGroupJsonList = JSONArray.fromObject(userGrouplist);
		mav.addObject("userGroupList", userGrouplist);
		
		List<CmmCodeSeqDto> userRolelist = commonService.selectUserRole(new CmmCodeSeqDto());
	//JSONArray userRoleJsonList = JSONArray.fromObject(userRolelist);
		mav.addObject("userRoleList", userRolelist);
		
        map.put("comCdGrpId", "USE_YN");
        map.put("lang", LocaleUtil.getLocale(request).toString());    
		List<ComCdDto> useYnlist = commonService.selectCmmCode(map);
	//	JSONArray useYnList = JSONArray.fromObject(useYnlist);
		mav.addObject("useYnlist", useYnlist);

		return mav;
	}	

    /**
    * User List 조회
    *
    * @method : getUserList
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
	@PostMapping("/admin/getUserList")
	public ModelAndView getUserList(Model model, HttpServletRequest request) {

		if(request == null)
		{
			return null;
		}

        String schType		= request.getParameter("schType");
        String schVal		= request.getParameter("schVal");
        String schUsrGrp	= request.getParameter("schUsrGrp");
        String schUseYn		= request.getParameter("schUseYn");
        String pageOffset 	= request.getParameter("skip");
        String pageSize 	= request.getParameter("take");
        String orderby 		= EpasUtil.replaceXssRemoveSqlInjection(request.getParameter("orderby"));	//그리드 헤더 클릭 소팅
        
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("schType", schType);
        map.put("schVal", schVal);
        map.put("schUsrGrp", schUsrGrp);
        map.put("schUseYn", schUseYn);
        map.put("pageOffset", pageOffset);
        map.put("pageSize"	, pageSize);
        map.put("orderby", orderby);
        
		List<UsrInfoDto> list = usrInfoService.selectUserList(map);
        int totalCount = usrInfoService.selectUserInfoTotalCount(map);
		
		ModelAndView mav = new ModelAndView("jsonView");
		mav.addObject("list", list);
		mav.addObject("totalCount", totalCount);
		return mav;
	}

    /**
    * User Save
    *
    * @method : saveUser
    * @date : 2023.06.26
    * @author : choih
    * @param : request
    * HttpServletRequest
    * @param : response
    * HttpServletResponse
    * @param : params
    * ResponseBody UsrInfoDto
    * @return String
    * @throws 
    *     << Modification History >>
    *     
    *     Date        Author       Description
    *     ---------   --------     --------------------
    *     2023.06.26  choih        initial
    */
    @PostMapping("/admin/saveUser")
    @ResponseBody
    public String saveUser(@RequestBody UsrInfoDto params, HttpServletResponse response, HttpServletRequest request) throws Exception {

		if(request == null)
		{
			return null;
		}

    	JSONObject resMap = new JSONObject();
    	try {
    		HttpSession session = request.getSession();
    		MemberInfoDto vo = (MemberInfoDto)session.getAttribute("loginMember");
    		String loginId = vo.getUsrId();
    		Long roleSeq = vo.getRoleSeq();
    		String encryPassword = UserSha256.encrypt(params.getUsrId());  //초기 패스워는 User ID로 넣는다.
    		String usrLang = "ko";  //초기 언어 셋팅

			// 시스템관리자가 아니면 시스템관리자 등록 불가 처리
			if(roleSeq != 4)
			{
	            resMap.put("res", "fail");
	            resMap.put("msg", messageSource.getMessage("message.alert_0012", null, LocaleUtil.getLocale(request)));
			}
			else
			{
	            params.setUsrPwd(encryPassword);
	            params.setCreatedUsrId(loginId);
	            params.setUpdatedUsrId(loginId);
	            params.setUsrLang(usrLang);
	            
	            String baseMenuOrd = ":"; //선택된 role에 정의된 메뉴 목록으로 update
	            params.setMenuOrd(baseMenuOrd);

	            usrInfoService.saveUser(params);
	            resMap.put("res", "success");
	            resMap.put("msg", messageSource.getMessage("message.common.insert",null,LocaleUtil.getLocale(request)));
			}
    	}catch(Exception e) {
    		
    	}

	  response.setContentType("text/html; charset=UTF-8");
	  PrintWriter out = response.getWriter();
	  out.print(resMap);
	  
	  return null;
	}

    /**
    * User Update
    *
    * @method : updateUser
    * @date : 2023.06.26
    * @author : choih
    * @param : request
    * HttpServletRequest
    * @param : response
    * HttpServletResponse
    * @param : params
    * ResponseBody UsrInfoDto
    * @return String
    * @throws 
    *     << Modification History >>
    *     
    *     Date        Author       Description
    *     ---------   --------     --------------------
    *     2023.06.26  choih        initial
    */      
    @PostMapping("/admin/updateUser")
    @ResponseBody    
    public String updateUser(@RequestBody UsrInfoDto params, HttpServletResponse response, HttpServletRequest request) throws Exception {

		if(request == null)
		{
			return null;
		}

    	JSONObject resMap = new JSONObject();
		HttpSession session = request.getSession();
		MemberInfoDto vo = (MemberInfoDto)session.getAttribute("loginMember");
		String loginId = vo.getUsrId();
		Long roleSeq = vo.getRoleSeq();

        params.setUpdatedUsrId(loginId);

    	try {
			// 시스템관리자가 아니면 시스템관리자 수정 불가 처리
			if(roleSeq != 4)
			{
	            resMap.put("res", "fail");
	            resMap.put("msg", messageSource.getMessage("message.alert_0012", null, LocaleUtil.getLocale(request)));
			}
			else
			{
	    		usrInfoService.updateUser(params);
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
    * Password 초기화
    *
    * @method : initPassword
    * @date : 2023.06.26
    * @author : choih
    * @param : request
    * HttpServletRequest
    * @param : response
    * HttpServletResponse
    * @return String
    * @throws 
    *     << Modification History >>
    *     
    *     Date        Author       Description
    *     ---------   --------     --------------------
    *     2023.06.26  choih        initial
    */       
    @PostMapping("/admin/initPassword")
    @ResponseBody    
    public String initPassword(HttpServletResponse response, HttpServletRequest request) throws Exception {

		if(request == null)
		{
			return null;
		}

		HttpSession session = request.getSession();
		MemberInfoDto vo = (MemberInfoDto)session.getAttribute("loginMember");
		String loginId = vo.getUsrId();
		Long roleSeq = vo.getRoleSeq();
		
    	JSONObject resMap = new JSONObject();
    	UsrInfoDto params = new UsrInfoDto();

    	//패스워드 초기화 대상 user ID
        String usrId = request.getParameter("usrId");
        
    	try {
			// 시스템관리자가 아니면 시스템관리자 패스워드 초기화 불가 처리
			if(roleSeq != 4)
			{
	            resMap.put("res", "fail");
	            resMap.put("msg", messageSource.getMessage("message.alert_0012", null, LocaleUtil.getLocale(request)));
			}
			else
				
			{
	    		String encryPassword = UserSha256.encrypt(usrId);
	            params.setUsrPwd(encryPassword);
	            params.setUsrId(usrId);
	            params.setUpdatedUsrId(loginId);            
	    		usrInfoService.initPassword(params);
	            resMap.put("res", "success");
	            
	            //System.out.println(messageSource.getMessage("message.common.checkMaxlength", new String[]{"User Id","32"}, usrlcal));
	            resMap.put("msg", messageSource.getMessage("message.common.passwordResetComplete",null,LocaleUtil.getLocale(request)));
			}
    	}catch(Exception e) {
		
    	}

	  response.setContentType("text/html; charset=UTF-8");
	  PrintWriter out = response.getWriter();
	  out.print(resMap);
	  
	  return null;
	}
    
    /**
    * User Delete
    *
    * @method : deleteUser
    * @date : 2023.06.26
    * @author : choih
    * @param : request
    * HttpServletRequest
    * @param : response
    * HttpServletResponse
    * @param : params
    * ResponseBody UsrInfoDto
    * @return String
    * @throws 
    *     << Modification History >>
    *     
    *     Date        Author       Description
    *     ---------   --------     --------------------
    *     2023.06.26  choih        initial
    */   
    @PostMapping("/admin/deleteUser")
    @ResponseBody
    public String deleteUser(@RequestBody UsrInfoDto params, HttpServletResponse response, HttpServletRequest request) throws Exception {

		if(request == null)
		{
			return null;
		}

    	JSONObject resMap = new JSONObject();

		usrInfoService.deleteUser(params);
        resMap.put("res", "success");
        resMap.put("msg", messageSource.getMessage("message.common.delete",null,LocaleUtil.getLocale(request)));

        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(resMap);
	  
        return null;
	}  

    /**
    * User Delete(use_yn = 'N'처리)
    *
    * @method : delUser
    * @date : 2023.08.22
    * @author : choih
    * @param : request
    * HttpServletRequest
    * @param : response
    * HttpServletResponse
    * @param : params
    * ResponseBody UsrInfoDto
    * @return String
    * @throws 
    *     << Modification History >>
    *     
    *     Date        Author       Description
    *     ---------   --------     --------------------
    *     2023.08.22  choih        initial
    */
    @PostMapping("/admin/delUser")
    @ResponseBody
    public String delUser(@RequestBody UsrInfoDto params, HttpServletResponse response, HttpServletRequest request) throws Exception {

		if(request == null)
		{
			return null;
		}

    	JSONObject resMap = new JSONObject();
    	try {
    		HttpSession session = request.getSession();
    		MemberInfoDto vo = (MemberInfoDto)session.getAttribute("loginMember");
    		String loginId = vo.getUsrId();
    		Long roleSeq = vo.getRoleSeq();
    		
    		params.setUpdatedUsrId(loginId);

			// 시스템관리자가 아니면 시스템관리자 사용불가 불가 처리
			if(roleSeq != 4)
			{
	            resMap.put("res", "fail");
	            resMap.put("msg", messageSource.getMessage("message.alert_0012", null, LocaleUtil.getLocale(request)));
			}
			else
			{    		
	    		usrInfoService.delUser(params);
	            resMap.put("res", "success");
	            resMap.put("msg", messageSource.getMessage("message.common.delete",null,LocaleUtil.getLocale(request)));
			}
    	} catch(Exception e) {
    		
    	}

	  response.setContentType("text/html; charset=UTF-8");
	  PrintWriter out = response.getWriter();
	  out.print(resMap);
	  
	  return null;
	}
    
    /**
    * User ID 중복체크
    *
    * @method : checkUserId
    * @date : 2023.06.26
    * @author : choih
    * @param : response
    * HttpServletResponse
    * @param : params
    * ResponseBody UsrInfoDto
    * @return String
    * @throws 
    *     << Modification History >>
    *     
    *     Date        Author       Description
    *     ---------   --------     --------------------
    *     2023.06.26  choih        initial
    */     
    @PostMapping("/admin/checkUserId")
    @ResponseBody
    public String checkUserId(@RequestBody UsrInfoDto params, HttpServletResponse response) throws Exception {
    	JSONObject resMap = new JSONObject();

		String chkUsrId = params.getUsrId();
		int chkCnt = usrInfoService.countByUsrId(chkUsrId);
        resMap.put("res", "success");
        resMap.put("chkCnt", chkCnt);

        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(resMap);
	  
        return null;
	}
}
