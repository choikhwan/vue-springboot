package com.epas.admin.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epas.admin.dto.RoleMenuInfoDto;
import com.epas.admin.dto.UsrRoleInfoDto;
import com.epas.admin.mapper.UsrRoleInfoMapper;

import lombok.RequiredArgsConstructor;

/**
 * 사용자 Role ServiceImpl Class
 *
 * @since 2023.06.19.
 * @author choih
 * @see <pre>
 *  Class Name : UsrRoleInfoServiceImpl.java
 *  Description : 
 *
 *  << Modification History >>
 *  
 *  Date              Modifier           Description
 *  ----------        -----------        ----------------------
 *  2023.06.19.       choih              initial
 *
 © Hitachi High-Tech Corporation.  2023. All rights reserved.
 *  </pre>
 */

@Service
@RequiredArgsConstructor
public class UsrRoleInfoServiceImpl implements UsrRoleInfoService {
	@Autowired
	private UsrRoleInfoMapper usrRoleInfoMapper;

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
    public List<UsrRoleInfoDto> selectUsrRoleInfoList(HashMap<String, Object> map){
		return usrRoleInfoMapper.selectUsrRoleInfoPagingList(map);
		
	}
    
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
	public int selectUsrRoleInfoTotalCount(HashMap<String, Object> map){

		int totalCount = usrRoleInfoMapper.selectUsrRoleInfoPagingCount(map);
		
		return totalCount;
	}
	
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
	public void insertUsrRoleInfo(UsrRoleInfoDto usrRoleInfoDto) {
		usrRoleInfoMapper.insertUsrRoleInfo(usrRoleInfoDto);
	}

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
	public void updateUsrRoleInfo(UsrRoleInfoDto usrRoleInfoDto) {
		usrRoleInfoMapper.updateUsrRoleInfo(usrRoleInfoDto);
	}

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
	public void deleteUsrRoleInfo(UsrRoleInfoDto usrRoleInfoDto) {
		usrRoleInfoMapper.deleteUsrRoleInfo(usrRoleInfoDto);
	}
	
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
	public void delUsrRoleInfo(UsrRoleInfoDto usrRoleInfoDto) {
		usrRoleInfoMapper.delUsrRoleInfo(usrRoleInfoDto);
	}
	
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
	public int countByUsrRoleNm(String roleNm) {
		return usrRoleInfoMapper.countByUsrRoleNm(roleNm);
	}
	
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
    public List<RoleMenuInfoDto> selectRoleMenuInfoList(HashMap<String, Object> map){
		return usrRoleInfoMapper.selectRoleMenuInfoList(map);
		
	}
    
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
	public void insertMstRoleMenuInfo(RoleMenuInfoDto roleMenuInfoDto) {
		usrRoleInfoMapper.insertMstRoleMenuInfo(roleMenuInfoDto);
	}

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
	public void updateMstRoleMenuInfo(RoleMenuInfoDto roleMenuInfoDto) {
		usrRoleInfoMapper.updateMstRoleMenuInfo(roleMenuInfoDto);
	}
	
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
	public long selectRoleSeq(HashMap<String, Object> map){

		long roleSeq = usrRoleInfoMapper.selectRoleSeq(map);
		
		return roleSeq;
	}
}
