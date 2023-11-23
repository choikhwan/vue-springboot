package com.epas.admin.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epas.admin.dto.UsrInfoDto;
import com.epas.admin.mapper.UsrInfoMapper;

import lombok.RequiredArgsConstructor;


/**
 * User Info ServiceImpl Class
 *
 * @since 2023. 6. 13.
 * @author choih
 * @see <pre>
 *  Class Name : UsrInfoServiceImpl.java
 *  Description : 
 *
 *  << Modification History >>
 *  
 *  Date              Modifier           Description
 *  ----------        -----------        ----------------------
 *  2023.06.13.       choih              initial
 *
 © Hitachi High-Tech Corporation.  2023. All rights reserved.
 *  </pre>
 */
@Service
@RequiredArgsConstructor
public class UsrInfoServiceImpl implements UsrInfoService {

	@Autowired
	private UsrInfoMapper usrInfoMapper;
  
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
	public List<UsrInfoDto> selectUserList(HashMap<String, Object> map) {
		List<UsrInfoDto> userList = usrInfoMapper.selectUserInfoPagingList(map);
		return userList;
	}
	
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
	public int selectUserInfoTotalCount(HashMap<String, Object> map){
		int totalCount = usrInfoMapper.selectUserInfoPagingCount(map);
		return totalCount;
	}
  
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
	public void saveUser(UsrInfoDto usrInfoDto) {
		usrInfoMapper.saveUser(usrInfoDto);
	}

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
	public void updateUser(UsrInfoDto usrInfoDto) {
		usrInfoMapper.updateUser(usrInfoDto);
	}
  
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
	public void initPassword(UsrInfoDto usrInfoDto) {
		usrInfoMapper.initPassword(usrInfoDto);
	}  

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
	public void deleteUser(UsrInfoDto usrInfoDto) {
		usrInfoMapper.deleteUser(usrInfoDto);
	}
  
	/**
	* User 정보 delete(use_yn = 'N'처리)
	*
	* @method : delUser
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
	*     2023.08.22  choih        initial
	*/
	public void delUser(UsrInfoDto usrInfoDto) {
		usrInfoMapper.delUser(usrInfoDto);
	}
	
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
	public int countByUsrId(String usrId) {
		return usrInfoMapper.countByUsrId(usrId);
	}
	
}
