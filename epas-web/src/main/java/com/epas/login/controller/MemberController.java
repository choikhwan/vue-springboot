package com.epas.login.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.epas.login.config.WebSessionListener;
import com.epas.login.dto.MemberInfoDto;
import com.epas.login.dto.UserConnInfoDto;
import com.epas.login.service.MemberService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import net.sf.json.JSONArray;

/**
* User Login Controller Class. 
* 
* @since 20230626
* @author kjyoo
* @see <pre>
*  Class Name : MemberController.java
*  Description : 
*
*  << Modification History >>
*  
 *  Date              Author             Description
*  ----------        -----------        ----------------------
*  20230626           kjyoo              initial
*
© Hitachi High-Tech Corporation.  2023. All rights reserved.
*  </pre>
*/
@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    
    /**
    * 로그인 화면으로 이동. 
    *
    * @method : epasLogin
    * @date : 20230626
    * @author : kjyoo
    * @param : request
	* HTTP 요청 메시지 생성 
	* 
    * @return epasLogin.jsp 화면 호출
    * @throws 예외가 있다면 예외 클래스 및 설명
    *     << Modification History >>
    *     
    *     Date        Author       Description
    *     ---------   --------     --------------------
    *     20230626     kjyoo       신규생성
    */    
    // 로그인 화면으로 이동
    @GetMapping("/login")
	public ModelAndView loginView(HttpServletRequest request) {
    	
		ModelAndView mav = new ModelAndView("login/login");
		return mav;
	}
    
    /**
    * 화면에서 로그인 시 로그인 로직. 
    *
    * @method : login
    * @date : 20230626
    * @author : kjyoo
    * @param : response
	* HTTP 응답 메시지 생성
	* @param : request
	* HTTP 요청 메시지 생성
    * 
    * @return 로그인 멤버 사용자 정보
    * @throws 예외가 있다면 예외 클래스 및 설명
    *     << Modification History >>
    *     
    *     Date        Author       Description
    *     ---------   --------     --------------------
    *     20230626     kjyoo       신규생성
    */
    @PostMapping("/epas/login")
    public ModelAndView login(HttpServletResponse response,  HttpServletRequest request) throws Exception {
    	
		ModelAndView mav = new ModelAndView("jsonView");
		
    	// 1. 회원 정보 조회
        String loginId = request.getParameter("loginId");
        String password = request.getParameter("password");
        MemberInfoDto member = memberService.login(loginId, password); // 사용자 정보 
        String expiredPswYn = memberService.expiredPassword(loginId); // 사용자 패스워드 만료여부 Y:만료, N:유효
        //var loginLimitCnt = 0;
        //var currentLoginCnt = 0;
        
        //Login 접속 사용자 IP를 호출
		String userIp = getClientIp(request);
        
        // 2.로그인한 유저가 처음 로그인 시도(ID = PW 일경우)이거나 패스워드 교체 만료일(90일)이 지나면, 패스워드 교체 알림 표시
        String encryLoginId = UserSha256.encrypt(loginId);  
        if ((member != null && member.getUsrPwd().equals(encryLoginId)) || (member != null && "Y".equals(expiredPswYn))) {
        	member.setUsrPwd(loginId);
        	
    		mav.addObject("member", member);
        	return mav;
        }else if(member != null) {        
	        // 3. 중복 로그인 체크 및 세션에 회원 정보 저장 & 세션 유지 시간 설정
	        if(WebSessionListener.isLoginUser(request, loginId)) {	      	
	        	
	        	//이전 접속IP와 현재 접속IP 가 일치하면 알림없이 이전 session정보 삭제 후 로그인
	        	if(member.getUsrIp() != null) {
		        	if(!member.getUsrIp().equals(userIp)) {
		        		//존재하는 세션 삭제
			        	WebSessionListener.removeOtherSession(request, loginId);
			    		mav.addObject("member", member);
			        	return mav;
		        	}
	        	}
	        }
	        
	        // 4. 로그인 제한 수 체크
	        // 로그인 이전에 사용자ID로 session별 가장 최근 act값이 'LOGIN'인 Data 가 있다면 현재시간으로 모두  'LOGOUT'data로 Insert
	        List<UserConnInfoDto> connInfo = memberService.selectLoginUserConn(loginId); // 사용자 정보 
	        JSONArray jsonArrayConnInfo = JSONArray.fromObject(connInfo);
	        
	        HashMap<String, Object> map = new HashMap<String, Object>();
	        
	        for(var i=0; i<jsonArrayConnInfo.size(); i++) {
		        map.put("usrSessionId", jsonArrayConnInfo.getJSONObject(0).getString("usrSessionId"));
	        }
	        
	        ArrayList<HashMap<String,String>> arrConnInfos = new ArrayList<HashMap<String,String>>();
	        //UserConnInfoDto connInfoDto = new UserConnInfoDto();
	        //System.out.println("jsonArrayConnInfo.size >>> " + jsonArrayConnInfo.size());
			for(int i=0; i<jsonArrayConnInfo.size(); i++) {				
				HashMap<String, String> rowData = new HashMap<String, String>();
				rowData.put("usrId", jsonArrayConnInfo.getJSONObject(i).getString("usrId"));
				rowData.put("usrSessionId", jsonArrayConnInfo.getJSONObject(i).getString("usrSessionId"));
				//System.out.println("jsonArrayConnInfo.usrSessionId >>> " + jsonArrayConnInfo.getJSONObject(i).getString("usrSessionId"));
				arrConnInfos.add(rowData);				
			}
			/*
			if(arrConnInfos.size() > 0) {
				connInfoDto.setConnInfos(arrConnInfos);   		
				memberService.insertUserLogoutData(connInfoDto);
			}
	        
	        if(member.getRoleSeq() == 4) {	        	
	        	loginLimitCnt = memberService.selectLoginAdminNum();
	        	currentLoginCnt = WebSessionListener.cntLoginAdminUser();
	        	//System.out.println("Admin User Limit Number(roleSeq = 4) >>> " + loginLimitCnt + " / " + currentLoginCnt);
	        }else {
	        	loginLimitCnt = memberService.selectLoginUserNum();
	        	currentLoginCnt = memberService.countLoginUserConn();
	        	//System.out.println("General User Limit Number(roleSeq != 4) >>> " + loginLimitCnt + " / " + currentLoginCnt);
	        }
	        
	        // 일반 User Login시에만 접속 제한 수 체크, loginLimitCnt 가 0이거나 값이 없으면 제한 없이 Login
	        if(member.getRoleSeq() != 4 && loginLimitCnt != 0) {
	        	if(currentLoginCnt >= loginLimitCnt) {
	        		var resMsg = "OVER_LOGIN_LIMIT" + "/" + member.getRoleSeq() + "/" + loginLimitCnt;
	        		member.setUsrPwd(resMsg);
 	        		return member;
 	        	}
	        }
	        */
	        WebSessionListener webSessionListener = new WebSessionListener();
			webSessionListener.setSession(request, loginId, member); //유저 세션 생성
	        
	        //DB를 통해 사용자 언어를 가져와서 다국어 셋팅 변경
	        HttpSession session = request.getSession(false);  
	        String usrLocale = member.getUsrLang();
	        //Locale locale = new Locale("ko");
	        session.setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, new Locale(usrLocale != null?usrLocale:"en"));

	        // 5. 쿠키를 이용한 로그인 ID 저장
	        String cId = request.getParameter("checkSaveId");
	        if("true".equals(cId)) {
	        	Cookie cookie = new Cookie("cLoginId", loginId);
	        	cookie.setPath("/");
	        	cookie.setMaxAge(1209600); //cookie 정보 유지 시간 기본단위 - 초 : 1*60*60*24*14 = 1209600(14일)
	        	response.addCookie(cookie);
	        }
	        else {
	        	Cookie cookie = new Cookie("cLoginId", "");
	        	cookie.setPath("/");
	        	cookie.setMaxAge(0); //쿠키삭제
	        	response.addCookie(cookie);
	        }
	        
	        if (member != null) {
	        	member.setUsrPwd(""); //비밀번호 빈값 셋팅
	        }
	        
	        memberService.updateUserIp(loginId, userIp);
	        
    		mav.addObject("member", member);
        	return mav;
        }else {
    		mav.addObject("member", member);
        	return mav;
        }
    }
    
    /**
     * 화면에서 로그인 시 로그인 로직. 
     *
     * @method : login
     * @date : 20230626
     * @author : kjyoo
     * @param : response
 	* HTTP 응답 메시지 생성
 	* @param : request
 	* HTTP 요청 메시지 생성
     * 
     * @return 로그인 멤버 사용자 정보
     * @throws 예외가 있다면 예외 클래스 및 설명
     *     << Modification History >>
     *     
     *     Date        Author       Description
     *     ---------   --------     --------------------
     *     20230626     kjyoo       신규생성
     */
     @PostMapping("/user/getUserInfo")
     @ResponseBody
     public MemberInfoDto getUserInfo(HttpServletResponse response, HttpServletRequest request) throws Exception {

     	// 1. 회원 정보 조회
        String loginId = request.getParameter("loginId");
        MemberInfoDto member = memberService.getUserInfo(loginId); // 사용자 정보 
 	        
        if (member != null) {
        	member.setUsrPwd(""); //비밀번호 빈값 셋팅
        }
 	        
        return member;
     }
    
    @PostMapping("/user/updateUser")
    @ResponseBody    
    public String updateUser(HttpServletResponse response, HttpServletRequest request) throws Exception {
    	
    	if(request == null) {
    		return null;
    	}
    	
    	JSONObject resMap = new JSONObject();
    	try {
    		String loginId = request.getParameter("loginId");
    		String name = request.getParameter("name");
    		String phone = request.getParameter("phone");
    		String email = request.getParameter("email");
    		String menuOrd = request.getParameter("menuOrd");
    		
    		MemberInfoDto member = memberService.updateUserInfo(loginId, name, phone, email, menuOrd);
    	    
    	    HttpSession session = request.getSession(); 
    	    session.setAttribute("loginMember", member);
    	    
            resMap.put("res", "success");
            resMap.put("msg", "message.inform_0005");
            resMap.put("usrNm", member.getUsrNm());
    	}catch(Exception e) {
    		resMap.put("res", "fail");
    		resMap.put("msg", "message.alert_0009");
    	}

	    response.setContentType("text/html; charset=UTF-8");
	    PrintWriter out = response.getWriter();
	    out.print(resMap);
	  
	    return null;
	}   
    
    @PostMapping("/user/updateUserPsw")
    @ResponseBody    
    public String updateUserPsw(HttpServletResponse response, HttpServletRequest request) throws Exception {
    	
    	if(request == null) {
    		return null;
    	}
    	
    	JSONObject resMap = new JSONObject();
    	try {
    		String loginId = request.getParameter("loginId");
    	    String password = request.getParameter("password");
    	    String current_password = request.getParameter("current_password");
    	    
    	    if(current_password != null) {
    	    	MemberInfoDto member = memberService.login(loginId, current_password); // 사용자 정보
    	    	
    	    	if(member == null) {
    	    		resMap.put("res", "fail");
    	            resMap.put("msg", "message.alert_0005");
    	            
    	            response.setContentType("text/html; charset=UTF-8");
    	      	    PrintWriter out = response.getWriter();
    	      	    out.print(resMap);
    	      	  
    	      	    return null;
    	    	}
    	    }
    	    
    	    memberService.updateUserPassword(loginId, password);
    	    
            resMap.put("res", "success");
            resMap.put("msg", "message.inform_0001");
    	}catch(Exception e) {
    		resMap.put("res", "fail");
    		resMap.put("msg", "message.alert_0007");
    	}

	  response.setContentType("text/html; charset=UTF-8");
	  PrintWriter out = response.getWriter();
	  out.print(resMap);
	  
	  return null;
	}   
    
    public static String getClientIp(HttpServletRequest request) {
        
    	if(request == null) {
    		return null;
    	}
    	
    	String clientIp = null;
        boolean isIpInHeader = false;

        List<String> headerList = new ArrayList<>();
        headerList.add("X-Forwarded-For");
        headerList.add("HTTP_CLIENT_IP");
        headerList.add("HTTP_X_FORWARDED_FOR");
        headerList.add("HTTP_X_FORWARDED");
        headerList.add("HTTP_FORWARDED_FOR");
        headerList.add("HTTP_FORWARDED");
        headerList.add("Proxy-Client-IP");
        headerList.add("WL-Proxy-Client-IP");
        headerList.add("HTTP_VIA");    
        headerList.add("IPV6_ADR");

        for (String header : headerList) {
            clientIp = request.getHeader(header);
            if (StringUtils.hasText(clientIp) && !clientIp.equals("unknown")) {
                isIpInHeader = true;
                break;
            }
        }

        if (!isIpInHeader) {
            clientIp = request.getRemoteAddr();
        }

        return clientIp;
    }
    
    /**
     * 화면에서 session정보 접근. 
     *
     * @method : getUserSession
     * @date : 20230626
     * @author : kjyoo
     * @param : response
 	* HTTP 응답 메시지 생성
 	* @param : request
 	* HTTP 요청 메시지 생성
     * 
     * @return 로그인 멤버 사용자 정보
     * @throws 예외가 있다면 예외 클래스 및 설명
     *     << Modification History >>
     *     
     *     Date        Author       Description
     *     ---------   --------     --------------------
     *     20230911     kjyoo       신규생성
     */
     @PostMapping("/user/getUserSession")
     @ResponseBody
     public MemberInfoDto getUserSession(HttpServletResponse response, HttpServletRequest request) throws Exception {

    	JSONObject resMap = new JSONObject();
    	// 1. 회원 정보 조회        
        HttpSession session = request.getSession(false); 
        
        if (session == null) {
        	resMap.put("res", "fail");
        }else {        
	        MemberInfoDto vo = (MemberInfoDto)session.getAttribute("loginMember");
	 	        
	        if (vo == null) {
	        	resMap.put("res", "fail");
	        }else {
	        	resMap.put("res", "success");
	        }
        }
 	        
        response.setContentType("text/html; charset=UTF-8");
  	    PrintWriter out = response.getWriter();
  	    out.print(resMap);
        return null;
     }
     
     /**
      * 화면에서 사용자가 직접 로그아웃. 
      *
      * @method : epasLogout
      * @date : 20230626
      * @author : kjyoo
      * @param : HttpServletRequest request
  	* HTTP 요청 메시지 생성
  	* @param : ModelAndView mav
  	* Model View 정보 생성
      * 
      * @return epasLogin.jsp 화면 호출
      * @throws 예외가 있다면 예외 클래스 및 설명
      *     << Modification History >>
      *     
      *     Date        Author       Description
      *     ---------   --------     --------------------
      *     20230626     kjyoo       신규생성
      */
      @GetMapping("/epasLogout")
      public ModelAndView epasLogout(HttpServletRequest request) {
    	  
  		ModelAndView mav = new ModelAndView("jsonView");
    	 
      	HttpSession session = request.getSession(false);
    	
       	if(session != null) {
     		String userId = (String)session.getAttribute("userId");
     		WebSessionListener.removeSession(request, userId);
 		}
     	
     	mav.addObject("message", "로그아웃 되었습니다."); //로그아웃 되었습니다.
 		return mav;
  	}       
}
