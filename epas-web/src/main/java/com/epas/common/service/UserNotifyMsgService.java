package com.epas.common.service;

import java.util.HashMap;

/**
* User Notigy Msg Service
* 
* @since 20230725
* @author kjyoo
* @see <pre>
*  Class Name : UserNotifyMsgService.java
*  Description : 
*
*  << Modification History >>
*  
 *  Date              Author             Description
*  ----------        -----------        ----------------------
*  20230725           kjyoo              initial
*
© Hitachi High-Tech Corporation.  2023. All rights reserved.
*  </pre>
*/
public interface UserNotifyMsgService {
		
    /**
     * User Notify Msg Create
     *
     * @method : insertUserNotifyMsgInfo
     * @date : 20230719
     * @author : kjyoo
     * @param : map
 	 *  
 	 * 
     * @return User Notify Msg Create
     * @throws 예외가 있다면 예외 클래스 및 설명
     *     << Modification History >>
     *     
     *     Date        Author       Description
     *     ---------   --------     --------------------
     *     20230719     kjyoo       신규생성
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