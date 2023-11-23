package com.epas.common.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.epas.common.dto.ComCdDto;
import com.epas.common.dto.UserPreferenceDto;
import com.epas.common.service.CommonService;
import com.epas.common.service.UserPreferenceService;
import com.epas.login.dto.MemberInfoDto;

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
public class UserPreferenceController {

    private final UserPreferenceService userPreferenceService;
    private final CommonService commonService;
    
    @GetMapping("/pref/userPrefInfo")
	public ModelAndView userPrefInfo(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("common/userPrefInfoList");
		
		HttpSession session = request.getSession();
		MemberInfoDto vo = (MemberInfoDto)session.getAttribute("loginMember");
		
		if(vo != null) {
			String userId = vo.getUsrId();
			String locale = vo.getUsrLang();
			
			if("".equals(userId)) {
				return mav;
			}
	
			if("".equals(locale)) {
				locale = "en";
			}
	
			HashMap<String, Object> map = new HashMap<String, Object>();
	        map.put("usrId", userId);
	        map.put("usrLang", locale);	
	        map.put("comCdGrpId", "USE_YN");
	        map.put("lang", locale);   
			        
			List<UserPreferenceDto> userPrefInfoList = userPreferenceService.selectUserPrefInfo(map);
			
			ArrayList<HashMap<String,String>> prefInfos = new ArrayList<HashMap<String,String>>();
			UserPreferenceDto prefInfoDto = new UserPreferenceDto();
			for(int i=0;i<userPrefInfoList.size();i++) {
				UserPreferenceDto dtoList = (UserPreferenceDto)userPrefInfoList.get(i);
				if(!"PARENT".equals(dtoList.getEnvSetId())) {
					HashMap<String, String> rowData = new HashMap<String, String>();
					
					rowData.put("envSetId", dtoList.getEnvSetId());
					rowData.put("envSetVal", dtoList.getEnvSetVal());
					rowData.put("usrId", userId);
					prefInfos.add(rowData);
				}
				
			}
			
			if(prefInfos.size() > 0) {
	    		prefInfoDto.setPrefInfos(prefInfos);    		
	    		userPreferenceService.upsertUserPrefInfo(prefInfoDto);
			}
			
			List<ComCdDto> useYnlist = commonService.selectCmmCode(map);
			JSONArray useYnList = JSONArray.fromObject(useYnlist);
			mav.addObject("useYnlist", useYnList);
			
			JSONArray schPrefInfoList = JSONArray.fromObject(userPrefInfoList);
			mav.addObject("userPrefInfoList", schPrefInfoList);
		}

		return mav;
	}	
    
    /**
     * 화면에서 사용자가 Preference 정보를 수정
     *
     * @method : updatePrefInfo
     * @date : 20230726
     * @author : kjyoo
     * @param : response
	 * HTTP 응답 메시지 생성
	 * @param : request
	 * HTTP 요청 메시지 생성
     * 
     * @return 
     * @throws 예외가 있다면 예외 클래스 및 설명
     *     << Modification History >>
     *     
     *     Date        Author       Description
     *     ---------   --------     --------------------
     *     20230726     kjyoo       신규생성
     */            
    @PostMapping("/pref/updatePrefInfo")
    @ResponseBody    
    public String updatePrefInfo(@RequestBody List<UserPreferenceDto> params, HttpServletResponse response, HttpServletRequest request) throws Exception {
    	
    	if(request == null) {
    		return null;
    	}
    	
    	JSONObject resMap = new JSONObject();
    	HttpSession session = request.getSession();
		MemberInfoDto vo = (MemberInfoDto)session.getAttribute("loginMember");
		String userId = vo.getUsrId();

		if("".equals(userId)) {
			resMap.put("res", "fail");
			return null;
		}

		ArrayList<HashMap<String,String>> prefInfos = new ArrayList<HashMap<String,String>>();
		UserPreferenceDto prefInfoDto = new UserPreferenceDto();

		for(int i=0;i<params.size();i++) {
			UserPreferenceDto dtoList = (UserPreferenceDto)params.get(i);

			if(!"PARENT".equals(dtoList.getEnvSetId())) {
				HashMap<String, String> rowData = new HashMap<String, String>();
				
				rowData.put("envSetId", dtoList.getEnvSetId());
				rowData.put("envSetVal", dtoList.getEnvSetVal());
				rowData.put("usrId", userId);
				prefInfos.add(rowData);
			}
		}
		
		if(prefInfos.size() > 0) {
    		prefInfoDto.setPrefInfos(prefInfos);    		
    		userPreferenceService.upsertUserPrefInfo(prefInfoDto);
    		
    		resMap.put("res", "success");
            resMap.put("msg", "message.inform_0001");
		}else {
			resMap.put("res", "fail");
		}

	    response.setContentType("text/html; charset=UTF-8");
	    PrintWriter out = response.getWriter();
	    out.print(resMap);
	  
	    return null;
	}    
    
    /**
     * User Preferences 리스트 조회. 
     *
     * @method : selectPrefInfoList
     * @date : 20230726
     * @author : kjyoo
     * @param : response
	 * HTTP 응답 메시지 생성
	 * @param : request
	 * HTTP 요청 메시지 생성
     * 
     * @return User Preferences 리스트
     * @throws 예외가 있다면 예외 클래스 및 설명
     *     << Modification History >>
     *     
     *     Date        Author       Description
     *     ---------   --------     --------------------
     *     20230726     kjyoo       신규생성
     */
    @PostMapping("/pref/PrefInfoList")
    @ResponseBody    
    public ModelAndView selectPrefInfoList(HttpServletResponse response, HttpServletRequest request) throws Exception {
    	HttpSession session = request.getSession();
		MemberInfoDto vo = (MemberInfoDto)session.getAttribute("loginMember");
		
		ModelAndView mav = new ModelAndView("jsonView");
		
		if(vo != null) {
			String userId = vo.getUsrId();
			String locale = vo.getUsrLang();
			
			if("".equals(userId)) {
				return mav;
			}
	
			if("".equals(locale)) {
				locale = "en";
			}
	
			HashMap<String, Object> map = new HashMap<String, Object>();
	        map.put("usrId", userId);
	        map.put("usrLang", locale);	
	        map.put("comCdGrpId", "USE_YN");
	        map.put("lang", locale);   
			        
			List<UserPreferenceDto> userPrefInfoList = userPreferenceService.selectUserPrefInfo(map);
			mav.addObject("userPrefInfoList", userPrefInfoList);
			
			mav.addObject("data", userPrefInfoList);
			mav.addObject("userPrefInfoList", userPrefInfoList);
			
		}
	  
	    return mav;
	}
}