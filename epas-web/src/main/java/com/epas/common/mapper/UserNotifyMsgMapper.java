package com.epas.common.mapper;

import java.util.HashMap;
import org.apache.ibatis.annotations.Mapper;


/**
* EpasUtil 클래스
* 
* @since 2023.07.25
* @author yookj
* @see <pre>
*  Class Name : UserNotifyMsgMapper.java
*  Description : 
*
*  << Modification History >>
*  
*  Date              Author             Description
*  ----------        -----------        ----------------------
*  2023.07.25        yookj              initial
*
© Hitachi High-Tech Corporation.  2023. All rights reserved.
*  </pre>
*/

@Mapper
public interface UserNotifyMsgMapper {
		
	/**
	* trn_usr_notify_msg_info data 생성
	*
	* @method : insertUserNotifyMsgInfo
	* @date : 2023.07.25
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
	*     2023.07.25  yookj        initial
	*/
	void insertUserNotifyMsgInfo(HashMap<String, Object> map);
	
    /**
     * msg_no 로 msg_content 조회
     *
     * @method : selectMsgContentByMsgNo
     * @date : 20230918
     * @author : yangkw
     * @param : map
 	 *  
 	 * 
     * @return String
     * @throws 예외가 있다면 예외 클래스 및 설명
     *     << Modification History >>
     *     
     *     Date        Author       Description
     *     ---------   --------     --------------------
     *     20230918     yangkw       신규생성
     */ 
    public String selectMsgContentByMsgNo(HashMap<String, Object> map);
    
	/**
	* system setting 설정 값 조회
	*
	* @method : selectSystemValue
	* @date : 2023.10.12
	* @author : yangkw
	* @param : HashMap<String, String> param
	* @return String
	* @throws
	*     << Modification History >>
	*     
	*     Date        Author       Description
	*     ---------   --------     --------------------
	*     2023.10.12  yangkw        initial
	*/
	public String selectSystemValue(HashMap<String, String> param);
	
}
