package com.epas.admin.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.epas.admin.dto.RoleMenuInfoDto;
import com.epas.admin.dto.UsrRoleInfoDto;


/**
* 사용자 Role 정보 Mapper Class
* 
* @since 2023.06.19
* @author choih
* @see <pre>
*  Class Name : UsrRoleInfoMapper.java
*  Description : 
*
*  << Modification History >>
*  
*  Date              Author             Description
*  ----------        -----------        ----------------------
*  2023.06.19        choih              initial
*
© Hitachi High-Tech Corporation.  2023. All rights reserved.
*  </pre>
*/

@Mapper
public interface UsrRoleInfoMapper {
		
	/**
	* 주어진 조건에 따른 사용자 Role 정보 list를 불러온다.
	*
	* @method : selectUsrRoleInfoList
	* @date : 2023.06.26
	* @author : choih
	* @param : map
	* 
	* @return List<UsrRoleInfoDto>
	* @throws 예외가 있다면 예외 클래스 및 설명
	*     << Modification History >>
	*     
	*     Date        Author       Description
	*     ---------   --------     --------------------
	*     2023.06.26  choih        initial
	*/
	public List<UsrRoleInfoDto> selectUsrRoleInfoList(HashMap<String, Object> map);

	/**
	* 주어진 조건에 따른 Role 정보 paging list를 불러온다.
	*
	* @method : selectUsrRoleInfoPagingList
	* @date : 2023.06.26
	* @author : choih
	* @param : map
	* 
	* @return List<UsrRoleInfoDto>
	* @throws 예외가 있다면 예외 클래스 및 설명
	*     << Modification History >>
	*     
	*     Date        Author       Description
	*     ---------   --------     --------------------
	*     2023.06.26  choih        initial
	*/  
	public List<UsrRoleInfoDto> selectUsrRoleInfoPagingList(HashMap<String, Object> map);

	/**
	* 주어진 조건에 따른 Role 정보 total Count를 불러온다.
	*
	* @method : selectUsrRoleInfoPagingCount
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
	public int selectUsrRoleInfoPagingCount(HashMap<String, Object> map);
	
	/**
	* 사용자 Role 정보 insert
	*
	* @method : insertUsrRoleInfo
	* @date : 2023.06.26
	* @author : choih
	* @param : UsrRoleInfoDto params
	* 상기 파라미터1 설명
	* @return void
	* @throws 예외가 있다면 예외 클래스 및 설명
	*     << Modification History >>
	*     
	*     Date        Author       Description
	*     ---------   --------     --------------------
	*     2023.06.26  choih        initial
	*/	
	void insertUsrRoleInfo(UsrRoleInfoDto params);
	
	/**
	* 사용자 Role 정보 update
	*
	* @method : updateUsrRoleInfo
	* @date : 2023.06.26
	* @author : choih
	* @param : UsrRoleInfoDto params
	* 상기 파라미터1 설명
	* @return void
	* @throws 예외가 있다면 예외 클래스 및 설명
	*     << Modification History >>
	*     
	*     Date        Author       Description
	*     ---------   --------     --------------------
	*     2023.06.26  choih        initial
	*/	
	void updateUsrRoleInfo(UsrRoleInfoDto params);
	
	/**
	* 사용자 Role 정보 delete(물리적 삭제)
	*
	* @method : deleteUsrRoleInfo
	* @date : 2023.06.26
	* @author : choih
	* @param : UsrRoleInfoDto params
	* 상기 파라미터1 설명
	* @return void
	* @throws 예외가 있다면 예외 클래스 및 설명
	*     << Modification History >>
	*     
	*     Date        Author       Description
	*     ---------   --------     --------------------
	*     2023.06.26  choih        initial
	*/	
	void deleteUsrRoleInfo(UsrRoleInfoDto params);
	
	/**
	* 사용자 Role 정보 delete(논리적 삭제 use_yn = 'N' 처리)
	*
	* @method : delUsrRoleInfo
	* @date : 2023.06.26
	* @author : choih
	* @param : UsrRoleInfoDto params
	* 상기 파라미터1 설명
	* @return void
	* @throws 예외가 있다면 예외 클래스 및 설명
	*     << Modification History >>
	*     
	*     Date        Author       Description
	*     ---------   --------     --------------------
	*     2023.06.26  choih        initial
	*/		
	void delUsrRoleInfo(UsrRoleInfoDto params);
	
	/**
	* Role명 건수 리턴(Role명 중복 체크)
	*
	* @method : countByUsrRoleNm
	* @date : 2023.06.26
	* @author : choih
	* @param : roleNm
	* 
	* @return count
	* @throws 예외가 있다면 예외 클래스 및 설명
	*     << Modification History >>
	*     
	*     Date        Author       Description
	*     ---------   --------     --------------------
	*     2023.06.26  choih        initial
	*/	
	public int countByUsrRoleNm(String roleNm);
	
	/**
	* Role별 메뉴권한 정보 list를 불러온다.
	*
	* @method : selectRoleMenuInfoList
	* @date : 2023.06.26
	* @author : choih
	* @param : map
	* 상기 파라미터1 설명
	* @return List<RoleMenuInfoDto>
	* @throws 예외가 있다면 예외 클래스 및 설명
	*     << Modification History >>
	*     
	*     Date        Author       Description
	*     ---------   --------     --------------------
	*     2023.06.26  choih        initial
	*/
	public List<RoleMenuInfoDto> selectRoleMenuInfoList(HashMap<String, Object> map);
	
	/**
	* 사용자 Role별 Menu권한 정보 insert
	*
	* @method : insertMstRoleMenuInfo
	* @date : 2023.06.26
	* @author : choih
	* @param : RoleMenuInfoDto params
	* 상기 파라미터1 설명
	* @return void
	* @throws 예외가 있다면 예외 클래스 및 설명
	*     << Modification History >>
	*     
	*     Date        Author       Description
	*     ---------   --------     --------------------
	*     2023.06.26  choih        initial
	*/	
	void insertMstRoleMenuInfo(RoleMenuInfoDto params);
	
	/**
	* 사용자 Role별 Menu권한 정보 update
	*
	* @method : updateMstRoleMenuInfo
	* @date : 2023.06.26
	* @author : choih
	* @param : RoleMenuInfoDto params
	* 상기 파라미터1 설명
	* @return void
	* @throws 예외가 있다면 예외 클래스 및 설명
	*     << Modification History >>
	*     
	*     Date        Author       Description
	*     ---------   --------     --------------------
	*     2023.06.26  choih        initial
	*/	
	void updateMstRoleMenuInfo(RoleMenuInfoDto params);
	
	/**
	* roleSeq 리턴(신규로 채번된 Role_Seq 조회)
	*
	* @method : selectRoleSeq
	* @date : 2023.06.26
	* @author : choih
	* @param : map
	* 
	* @return roleSeq
	* @throws 예외가 있다면 예외 클래스 및 설명
	*     << Modification History >>
	*     
	*     Date        Author       Description
	*     ---------   --------     --------------------
	*     2023.06.26  choih        initial
	*/
	public long selectRoleSeq(HashMap<String, Object> map);	
   
}
