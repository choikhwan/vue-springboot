package com.epas.admin.service;

import java.util.HashMap;
import java.util.List;

import com.epas.admin.dto.UsrGroupInfoDto;


/**
 *  사용자 그룹 Info Service Class
 *
 * @since 2023. 6. 19.
 * @author choih
 * @see <pre>
 *  Class Name : UsrGroupInfoService.java
 *  Description : 
 *
 *  << Modification History >>
 *  
 *  Date              Modifier           Description
 *  ----------        -----------        ----------------------
 *  2023.06.01        choih              initial
 *
 © Hitachi High-Tech Corporation.  2023. All rights reserved.
 *  </pre>
 */
public interface UsrGroupInfoService {

	/**
	* 주어진 조건에 따른 사용자 그룹 정보 list를 불러온다.
	*
	* @method : selectUsrGroupInfoList
	* @date : 2023.06.26
	* @author : choih
	* @param : map
	* 상기 파라미터1 설명
	* @return List<UsrGroupInfoDto>
	* @throws 예외가 있다면 예외 클래스 및 설명
	*     << Modification History >>
	*     
	*     Date        Author       Description
	*     ---------   --------     --------------------
	*     2023.06.26  choih        initial
	*/
    public List<UsrGroupInfoDto> selectUsrGroupInfoList(HashMap<String, Object> map);

	/**
	* 주어진 조건에 따른 Group 정보 total Count를 불러온다.
	*
	* @method : selectUsrGroupInfoPagingCount
	* @date : 2023.06.26
	* @author : choih
	* @param : map
	* 상기 파라미터1 설명
	* @return total count
	* @throws 예외가 있다면 예외 클래스 및 설명
	*     << Modification History >>
	*     
	*     Date        Author       Description
	*     ---------   --------     --------------------
	*     2023.06.26  choih        initial
	*/	
	public int selectUsrGroupInfoTotalCount(HashMap<String, Object> map);    
    
	/**
	* 사용자 그룹 정보 insert
	*
	* @method : insertUsrGroupInfo
	* @date : 2023.06.26
	* @author : choih
	* @param : UsrGroupInfoDto params
	* 상기 파라미터1 설명
	* @return void
	* @throws 예외가 있다면 예외 클래스 및 설명
	*     << Modification History >>
	*     
	*     Date        Author       Description
	*     ---------   --------     --------------------
	*     2023.06.26  choih        initial
	*/	
    public void insertUsrGroupInfo(UsrGroupInfoDto usrGroupInfoDto);

	/**
	* 사용자 그룹 정보 update
	*
	* @method : updateUsrGroupInfo
	* @date : 2023.06.26
	* @author : choih
	* @param : UsrGroupInfoDto params
	* 상기 파라미터1 설명
	* @return void
	* @throws 예외가 있다면 예외 클래스 및 설명
	*     << Modification History >>
	*     
	*     Date        Author       Description
	*     ---------   --------     --------------------
	*     2023.06.26  choih        initial
	*/	
    public void updateUsrGroupInfo(UsrGroupInfoDto usrGroupInfoDto);

	/**
	* 사용자 그룹 정보 delete
	*
	* @method : deleteUsrGroupInfo
	* @date : 2023.06.26
	* @author : choih
	* @param : UsrGroupInfoDto params
	* 상기 파라미터1 설명
	* @return void
	* @throws 예외가 있다면 예외 클래스 및 설명
	*     << Modification History >>
	*     
	*     Date        Author       Description
	*     ---------   --------     --------------------
	*     2023.06.26  choih        initial
	*/
    public void deleteUsrGroupInfo(UsrGroupInfoDto usrGroupInfoDto);  
    
	/**
	* Group명 건수 리턴(Group명 중복 체크)
	*
	* @method : countByUsrGroupNm
	* @date : 2023.06.26
	* @author : choih
	* @param : usrGroupNm
	* 
	* @return count
	* @throws 예외가 있다면 예외 클래스 및 설명
	*     << Modification History >>
	*     
	*     Date        Author       Description
	*     ---------   --------     --------------------
	*     2023.06.26  choih        initial
	*/
	public int countByUsrGroupNm(String usrGroupNm);
  
}
