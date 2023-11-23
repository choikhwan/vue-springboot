package com.epas.login.config;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;

import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.epas.login.dto.MemberInfoDto;
import com.epas.login.service.MemberService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import lombok.extern.slf4j.Slf4j;

/**
* Web Session Listener Class. 
* 
* @since 20230626
* @author kjyoo
* @see <pre>
*  Class Name : WebSessionListener.java
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
@Slf4j
@Component
public class WebSessionListener implements HttpSessionListener {

    public static WebSessionListener sessionListener = null;
    @SuppressWarnings("rawtypes")
	private static Hashtable loginSessionList = new Hashtable();
    
    /**
     * 로그인 화면으로 이동. 
     *
     * @method : WebSessionListener getInstance()
     * @date : 20230626
     * @author : kjyoo
     * @param : 
 	 * 
 	 * 
     * @return sessionListener 생성
     * @throws 예외가 있다면 예외 클래스 및 설명
     *     << Modification History >>
     *     
     *     Date        Author       Description
     *     ---------   --------     --------------------
     *     20230626     kjyoo       신규생성
     */
    public static synchronized WebSessionListener getInstance() {
        if(sessionListener == null) {
            sessionListener = new WebSessionListener();
        }
        return sessionListener;
    }

    /**
     * session.setAttribute 실행 되는 순간 같은 sessionId 일경우 1회만 실행. 
     *
     * @method : sessionCreated
     * @date : 20230626
     * @author : kjyoo
     * @param : HttpSessionEvent
 	 * 세션 이벤트
 	 * 
     * @return 
     * @throws 예외가 있다면 예외 클래스 및 설명
     *     << Modification History >>
     *     
     *     Date        Author       Description
     *     ---------   --------     --------------------
     *     20230626     kjyoo       신규생성
     */
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        log.info("sessionCreated -> {}", httpSessionEvent.getSession().getAttribute("userId"));
        
    }

    /**
     * session 이 소멸되는 시점에 실행, 기본 단위는 초(1분 미만은 설정할 수 없음)
     *
     * @method : sessionDestroyed
     * @date : 20230626
     * @author : kjyoo
     * @param : HttpSessionEvent
 	 * 세션 이벤트
 	 * 
     * @return 
     * @throws 예외가 있다면 예외 클래스 및 설명
     *     << Modification History >>
     *     
     *     Date        Author       Description
     *     ---------   --------     --------------------
     *     20230626     kjyoo       신규생성
     */
    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        log.info("sessionDestroyed 실행");
        HttpSession session = httpSessionEvent.getSession();
        
        this.logConnInsert(session, "LOGOUT");

        //로그아웃 유저 삭제
        synchronized(loginSessionList){
            loginSessionList.remove(httpSessionEvent.getSession().getId());
        }

        currentSessionList();
    }
    
    /**
     * 현제 HashTable에 담겨 있는 유저 리스트, 즉 session list
     *
     * @method : currentSessionList
     * @date : 20230626
     * @author : kjyoo
     * @param : 
 	 * 세션 이벤트
 	 * 
     * @return 
     * @throws 예외가 있다면 예외 클래스 및 설명
     *     << Modification History >>
     *     
     *     Date        Author       Description
     *     ---------   --------     --------------------
     *     20230626     kjyoo       신규생성
     */
    @SuppressWarnings({ "rawtypes", "unused" })
	private static void currentSessionList(){
        Enumeration elements = loginSessionList.elements();
        HttpSession session = null;
        while (elements.hasMoreElements()){
            session = (HttpSession)elements.nextElement();

            String userId = (String)session.getAttribute("userId");
        }
    }

    /**
     * Session 생성
     *
     * @method : setSession
     * @date : 20230626
     * @author : kjyoo
     * @param : request
 	 * HTTP 요청 메시지 생성
 	 * @param : value
 	 * 유저 로그인 ID
 	 * @param : member
 	 * 로그인 유저 정보
 	 * 
     * @return 
     * @throws 예외가 있다면 예외 클래스 및 설명
     *     << Modification History >>
     *     
     *     Date        Author       Description
     *     ---------   --------     --------------------
     *     20230626     kjyoo       신규생성
     */
    @SuppressWarnings("unchecked")
	public void setSession(HttpServletRequest request, String value, MemberInfoDto member){
        //log.info("setSession 실행");
        HttpSession session = request.getSession();
        session.setAttribute("userId", value);
        session.setAttribute("loginMember", member);
        session.setMaxInactiveInterval(1800); //session 유지 시간 기본단위 - 초 : 1*60*30 = 1800 = 30분

        //HashMap에 Login에 성공한 유저 담기
        synchronized(loginSessionList){
            loginSessionList.put(session.getId(), session);
        }
        
        this.logConnInsert(session, "LOGIN");
        currentSessionList();
    }
    
    /**
     * session 삭제 -> 세션이 remove 되면 destroyed 함수 실행
     *
     * @method : removeSession
     * @date : 20230626
     * @author : kjyoo
     * @param : request
 	 * HTTP 요청 메시지 생성
 	 * 
     * @return 
     * @throws 예외가 있다면 예외 클래스 및 설명
     *     << Modification History >>
     *     
     *     Date        Author       Description
     *     ---------   --------     --------------------
     *     20230626     kjyoo       신규생성
     */
    @SuppressWarnings("rawtypes")
    public static void removeSession(HttpServletRequest request, String loginUserId){
        //log.info("removeSession 실행");
        
        if(loginUserId != null){
        
	        Enumeration elements = loginSessionList.elements();
	        HttpSession session = null;
	        while (elements.hasMoreElements()){
	        	session = (HttpSession)elements.nextElement();
	            String userId = (String)session.getAttribute("userId");
	            if(loginUserId.equals(userId) && (session.getId().equals(request.getSession().getId()))){
	            	session.invalidate();
	                synchronized(loginSessionList){
	                    loginSessionList.remove(request.getSession().getId());
	                }
	            }
	        }
        }
    }

    /**
     * 다른곳에 로그인된 session 삭제 -> 세션이 remove 되면 destroyed 함수 실행
     *
     * @method : removeOtherSession
     * @date : 20230626
     * @author : kjyoo
     * @param : request
 	 * HTTP 요청 메시지 생성
 	 * 
     * @return 
     * @throws 예외가 있다면 예외 클래스 및 설명
     *     << Modification History >>
     *     
     *     Date        Author       Description
     *     ---------   --------     --------------------
     *     20230626     kjyoo       신규생성
     */
    @SuppressWarnings("rawtypes")
    public static void removeOtherSession(HttpServletRequest request, String loginUserId){
        //log.info("removeSession 실행");
        
        if(loginUserId != null){
        
	        Enumeration elements = loginSessionList.elements();
	        HttpSession session = null;
	        while (elements.hasMoreElements()){
	        	session = (HttpSession)elements.nextElement();
	            String userId = (String)session.getAttribute("userId");
	            if(loginUserId.equals(userId) && (!session.getId().equals(request.getSession().getId()))){
	            	session.invalidate();
	                synchronized(loginSessionList){
	                    loginSessionList.remove(request.getSession().getId());
	                }
	            }
	        }
        }
    }

    /**
     * 현재 로그인한 유저가 이미 존재 하는지 확인
     *
     * @method : isLoginUser
     * @date : 20230626
     * @author : kjyoo
     * @param : request
 	 * HTTP 요청 메시지 생성
 	 * @param : loginUserId
 	 * 로그인 유저 ID
 	 * 
     * @return 
     * @throws 예외가 있다면 예외 클래스 및 설명
     *     << Modification History >>
     *     
     *     Date        Author       Description
     *     ---------   --------     --------------------
     *     20230626     kjyoo       신규생성
     */
    @SuppressWarnings("rawtypes")
	public static boolean isLoginUser(HttpServletRequest request, String loginUserId){
        Enumeration elements = loginSessionList.elements();
        HttpSession session = null;
        while (elements.hasMoreElements()){
            session = (HttpSession)elements.nextElement();
            String userId = (String)session.getAttribute("userId");
            if(loginUserId.equals(userId) && (!session.getId().equals(request.getSession().getId()))){
            	//session.invalidate();
                return true;
            }
        }
        return false;
    }
    
    /**
     * session 및 sessionList 초기화
     *
     * @method : testInitSession
     * @date : 20230822
     * @author : kjyoo
     * @param : session
 	 * HTTP 요청 메시지 생성
 	 * 
     * @return 
     * @throws 예외가 있다면 예외 클래스 및 설명
     *     << Modification History >>
     *     
     *     Date        Author       Description
     *     ---------   --------     --------------------
     *     20230626     kjyoo       신규생성
     */
    public static void testInitSession(HttpSession session){
    	session.invalidate();
    	loginSessionList.clear();
    }
    
    /**
     * 현재 로그인한 Admin User Count
     *
     * @method : cntLoginAdminUser
     * @date : 20231006
     * @author : kjyoo
     * @param : request
 	 * HTTP 요청 메시지 생성
 	 * 
     * @return 
     * @throws 예외가 있다면 예외 클래스 및 설명
     *     << Modification History >>
     *     
     *     Date        Author       Description
     *     ---------   --------     --------------------
     *     20231006     kjyoo       신규생성
     */
    @SuppressWarnings("rawtypes")
	public static int cntLoginAdminUser(){
        Enumeration elements = loginSessionList.elements();
        HttpSession session = null;
        int userCnt = 0;
        while (elements.hasMoreElements()){
            session = (HttpSession)elements.nextElement();
            MemberInfoDto loginMember = (MemberInfoDto)session.getAttribute("loginMember");
            if(loginMember.getRoleSeq() == 4){
            	userCnt += 1;
            }
        }
        return userCnt;
    }
    
    /**
     * 현재 로그인한 일반 User Count
     *
     * @method : cntLoginGeneralUser
     * @date : 20231006
     * @author : kjyoo
     * @param : request
 	 * HTTP 요청 메시지 생성
 	 * 
     * @return 
     * @throws 예외가 있다면 예외 클래스 및 설명
     *     << Modification History >>
     *     
     *     Date        Author       Description
     *     ---------   --------     --------------------
     *     20231006     kjyoo       신규생성
     */
    @SuppressWarnings("rawtypes")
	public static int cntLoginGeneralUser(){
        Enumeration elements = loginSessionList.elements();
        HttpSession session = null;
        int userCnt = 0;
        while (elements.hasMoreElements()){
            session = (HttpSession)elements.nextElement();
            MemberInfoDto loginMember = (MemberInfoDto)session.getAttribute("loginMember");
            if(loginMember.getRoleSeq() != 4){
            	userCnt += 1;
            }
        }
        return userCnt;
    }
    
    private void logConnInsert(HttpSession session, String logAct) {
    	log.info("logConnInsert 실행");
    	
    	WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(session.getServletContext());
    	MemberService memberService = ctx.getBean("memberService", MemberService.class);
    	
    	HashMap<String, Object> map = new HashMap<String, Object>();
    	
    	String userID = (String)session.getAttribute("userId");
    	
    	if(userID != null) {
    		map.put("usrId", userID);
            map.put("usrSessionId", session.getId());	
            map.put("usrAct", logAct);
            
            memberService.insertLoginUserConn(map);
    	}
    }

}