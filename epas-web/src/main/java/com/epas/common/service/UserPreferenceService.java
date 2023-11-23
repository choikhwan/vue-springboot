package com.epas.common.service;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.epas.common.dto.UserPreferenceDto;
import com.epas.common.mapper.UserPreferenceMapper;

/**
* User Preference Service Class 
* 
* @since 20230726
* @author kjyoo
* @see <pre>
*  Class Name : UserPreferenceService.java
*  Description : 
*
*  << Modification History >>
*  
 *  Date              Author             Description
*  ----------        -----------        ----------------------
*  20230726           kjyoo              initial
*
© Hitachi High-Tech Corporation.  2023. All rights reserved.
*  </pre>
*/
@Service
@RequiredArgsConstructor
public class UserPreferenceService {

	private final UserPreferenceMapper userPreferenceMapper;
    
	/**
	* TRN_USR_ENV_INFO data 생성 or 수정
	*
	* @method : upsertUserPrefInfo
	* @date : 2023.07.26
	* @author : yookj
	* @param : userNotifyMsgDto
	* Insert할 trn_usr_notify_msg_info 정보
	 * @return 
	 * @return 
	* @return 
	* @throws 예외가 있다면 예외 클래스 및 설명
	*     << Modification History >>
	*     
	*     Date        Author       Description
	*     ---------   --------     --------------------
	*     2023.07.26  yookj        initial
	*/
    public void upsertUserPrefInfo(UserPreferenceDto param) {
    	userPreferenceMapper.upsertUserPrefInfo(param);
    }

    /**
	* TRN_USR_ENV_INFO data 조회
	*
	* @method : selectUserPrefInfo
	* @date : 2023.07.26
	* @author : yookj
	* @param : map
	* 조회할 조건 정보
	 * @return 
	* @return 
	* @throws 예외가 있다면 예외 클래스 및 설명
	*     << Modification History >>
	*     
	*     Date        Author       Description
	*     ---------   --------     --------------------
	*     2023.07.26  yookj        initial
	*/ 
    public List<UserPreferenceDto> selectUserPrefInfo(HashMap<String, Object> map) {
    	return userPreferenceMapper.selectUserPrefInfo(map);
    }   

}