package com.epas.common.mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.epas.common.dto.UserPreferenceDto;


/**
* User Preference
* 
* @since 2023.07.26
* @author yookj
* @see <pre>
*  Class Name : UserPreferenceMapper.java
*  Description : 
*
*  << Modification History >>
*  
*  Date              Author             Description
*  ----------        -----------        ----------------------
*  2023.07.26        yookj              initial
*
© Hitachi High-Tech Corporation.  2023. All rights reserved.
*  </pre>
*/

@Mapper
public interface UserPreferenceMapper {
		
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
	* @throws 예외가 있다면 예외 클래스 및 설명
	*     << Modification History >>
	*     
	*     Date        Author       Description
	*     ---------   --------     --------------------
	*     2023.07.26  yookj        initial
	*/
	void upsertUserPrefInfo(UserPreferenceDto param);
	
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
	List<UserPreferenceDto> selectUserPrefInfo(HashMap<String, Object> map);
}
