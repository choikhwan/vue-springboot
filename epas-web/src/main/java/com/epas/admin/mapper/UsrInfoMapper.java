package com.epas.admin.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.epas.admin.dto.UsrInfoDto;



/**
 * 사용자 정보 Mapper Class
 *
 * @since 2023. 6. 13.
 * @author choih
 * @see <pre>
 *  Class Name : UsrInfoMapper.java
 *  Description : 
 *
 *  << Modification History >>
 *  
 *  Date              Modifier           Description
 *  ----------        -----------        ----------------------
 *
 © Hitachi High-Tech Corporation.  2023. All rights reserved.
 *  </pre>
 */
@Mapper
public interface UsrInfoMapper {
	
	/**
	* 주어진 조건에 따른 User 그룹 정보 list를 불러온다.
	*
	* @method : selectUserList
	* @date : 2023.06.26
	* @author : choih
	* @param : map
	* 상기 파라미터1 설명
	* @return List<UsrInfoDto>
	* @throws 예외가 있다면 예외 클래스 및 설명
	*     << Modification History >>
	*     
	*     Date        Author       Description
	*     ---------   --------     --------------------
	*     2023.06.26  choih        initial
	*/
	public List<UsrInfoDto> selectUserList(HashMap<String, Object> map);
  
	/**
	* 주어진 조건에 따른 User 정보 paging list를 불러온다.
	*
	* @method : selectUserInfoPagingList
	* @date : 2023.06.26
	* @author : choih
	* @param : map
	* 상기 파라미터1 설명
	* @return List<UsrInfoDto>
	* @throws 예외가 있다면 예외 클래스 및 설명
	*     << Modification History >>
	*     
	*     Date        Author       Description
	*     ---------   --------     --------------------
	*     2023.06.26  choih        initial
	*/  
	public List<UsrInfoDto> selectUserInfoPagingList(HashMap<String, Object> map);
  
	/**
	* 주어진 조건에 따른 User 정보 total Count를 불러온다.
	*
	* @method : selectUserInfoPagingCount
	* @date : 2023.06.26
	* @author : choih
	* @param : map
	* 
	* @return total count
	* @throws 예외가 있다면 예외 클래스 및 설명
	*     << Modification History >>
	*     
	*     Date        Author       Description
	*     ---------   --------     --------------------
	*     2023.06.26  choih        initial
	*/
	public int selectUserInfoPagingCount(HashMap<String, Object> map);

	/**
	* User 정보 insert
	*
	* @method : saveUser
	* @date : 2023.06.26
	* @author : choih
	* @param : UsrInfoDto params
	* 
	* @return void
	* @throws 예외가 있다면 예외 클래스 및 설명
	*     << Modification History >>
	*     
	*     Date        Author       Description
	*     ---------   --------     --------------------
	*     2023.06.26  choih        initial
	*/	
	void saveUser(UsrInfoDto params);

 
	/**
	* User 정보 update
	*
	* @method : updateUser
	* @date : 2023.06.26
	* @author : choih
	* @param : UsrInfoDto params
	* 상기 파라미터1 설명
	* @return void
	* @throws 예외가 있다면 예외 클래스 및 설명
	*     << Modification History >>
	*     
	*     Date        Author       Description
	*     ---------   --------     --------------------
	*     2023.06.26  choih        initial
	*/	
	void updateUser(UsrInfoDto params);

	/**
	* Password 초기화
	*
	* @method : initPassword
	* @date : 2023.06.26
	* @author : choih
	* @param : UsrInfoDto params
	* 
	* @return void
	* @throws 예외가 있다면 예외 클래스 및 설명
	*     << Modification History >>
	*     
	*     Date        Author       Description
	*     ---------   --------     --------------------
	*     2023.06.26  choih        initial
	*/
	void initPassword(UsrInfoDto params);
  
	/**
	* User 정보 deleteUser
	*
	* @method : deleteUser
	* @date : 2023.06.26
	* @author : choih
	* @param : UsrInfoDto params
	* 
	* @return void
	* @throws 예외가 있다면 예외 클래스 및 설명
	*     << Modification History >>
	*     
	*     Date        Author       Description
	*     ---------   --------     --------------------
	*     2023.06.26  choih        initial
	*/
  	void deleteUser(UsrInfoDto params);

	/**
	* User 정보 delete(use_yn = 'N'처리)
	*
	* @method : delUser
	* @date : 2023.08.22
	* @author : choih
	* @param : UsrInfoDto params
	* 
	* @return void
	* @throws 예외가 있다면 예외 클래스 및 설명
	*     << Modification History >>
	*     
	*     Date        Author       Description
	*     ---------   --------     --------------------
	*     2023.08.22  choih        initial
	*/		
	void delUser(UsrInfoDto params);
	
	/**
	* User ID 건수 리턴(User ID 중복 체크)
	*
	* @method : countByUsrId
	* @date : 2023.06.26
	* @author : choih
	* @param : usrId
	* 
	* @return count
	* @throws 예외가 있다면 예외 클래스 및 설명
	*     << Modification History >>
	*     
	*     Date        Author       Description
	*     ---------   --------     --------------------
	*     2023.06.26  choih        initial
	*/
  	public int countByUsrId(String usrId);

}
