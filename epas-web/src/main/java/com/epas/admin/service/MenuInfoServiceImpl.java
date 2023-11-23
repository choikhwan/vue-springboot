package com.epas.admin.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epas.admin.dto.MenuInfoDto;
import com.epas.admin.mapper.MenuInfoMapper;

import lombok.RequiredArgsConstructor;

/**
 * Menu ServiceImpl Class
 *
 * @since 2023.06.19.
 * @author choih
 * @see <pre>
 *  Class Name : MenuInfoServiceImpl.java
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
public class MenuInfoServiceImpl implements MenuInfoService {
	@Autowired
	private MenuInfoMapper menuInfoMapper;

	/**
	* 주어진 조건에 따른 Menu 정보 list를 불러온다.
	*
	* @method : selectMenuInfoList
	* @date : 2023.06.26
	* @author : choih
	* @param : map
	* 상기 파라미터1 설명
	* @return List<MenuInfoDto>
	* @throws 예외가 있다면 예외 클래스 및 설명
	*     << Modification History >>
	*     
	*     Date        Author       Description
	*     ---------   --------     --------------------
	*     2023.06.26  choih        initial
	*/
    public List<MenuInfoDto> selectMenuInfoList(HashMap<String, Object> map){
    	return menuInfoMapper.selectMenuInfoPagingList(map);
		
	}
	
	/**
	* 주어진 조건에 따른 Menu 정보 total Count를 불러온다.
	*
	* @method : selectMenuInfoPagingCount
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
	public int selectMenuInfoTotalCount(HashMap<String, Object> map){

		int totalCount = menuInfoMapper.selectMenuInfoPagingCount(map);
		
		return totalCount;
	}
	
	/**
	* Menu 정보 insert
	*
	* @method : insertMenuInfo
	* @date : 2023.06.26
	* @author : choih
	* @param : MenuInfoDto params
	* 상기 파라미터1 설명
	* @return void
	* @throws 예외가 있다면 예외 클래스 및 설명
	*     << Modification History >>
	*     
	*     Date        Author       Description
	*     ---------   --------     --------------------
	*     2023.06.26  choih        initial
	*/	
	public void insertMenuInfo(MenuInfoDto menuInfoDto) {
		menuInfoMapper.insertMenuInfo(menuInfoDto);
	}

	/**
	* Menu 정보 update
	*
	* @method : updateMenuInfo
	* @date : 2023.06.26
	* @author : choih
	* @param : MenuInfoDto params
	* 상기 파라미터1 설명
	* @return void
	* @throws 예외가 있다면 예외 클래스 및 설명
	*     << Modification History >>
	*     
	*     Date        Author       Description
	*     ---------   --------     --------------------
	*     2023.06.26  choih        initial
	*/
	public void updateMenuInfo(MenuInfoDto menuInfoDto) {
		menuInfoMapper.updateMenuInfo(menuInfoDto);
	}

	/**
	* Menu 정보 delete
	*
	* @method : deleteMenuInfo
	* @date : 2023.06.26
	* @author : choih
	* @param : MenuInfoDto params
	* 
	* @return void
	* @throws 예외가 있다면 예외 클래스 및 설명
	*     << Modification History >>
	*     
	*     Date        Author       Description
	*     ---------   --------     --------------------
	*     2023.06.26  choih        initial
	*/	
	public void deleteMenuInfo(MenuInfoDto menuInfoDto) {
		menuInfoMapper.deleteMenuInfo(menuInfoDto);
	}
	
	/**
	* Menu 정보 delete(use_yn = 'N'처리)
	*
	* @method : delMenuInfo
	* @date : 2023.06.26
	* @author : choih
	* @param : MenuInfoDto params
	* 
	* @return void
	* @throws 예외가 있다면 예외 클래스 및 설명
	*     << Modification History >>
	*     
	*     Date        Author       Description
	*     ---------   --------     --------------------
	*     2023.06.26  choih        initial
	*/
	public void delMenuInfo(MenuInfoDto menuInfoDto) {
		menuInfoMapper.delMenuInfo(menuInfoDto);
	}
	
	/**
	* Menu Id 건수 리턴(Menu Id 중복 체크)
	*
	* @method : countByMenuId
	* @date : 2023.06.26
	* @author : choih
	* @param : menuId
	* 
	* @return count
	* @throws 예외가 있다면 예외 클래스 및 설명
	*     << Modification History >>
	*     
	*     Date        Author       Description
	*     ---------   --------     --------------------
	*     2023.06.26  choih        initial
	*/
	public int countByMenuId(String menuId) {
		return menuInfoMapper.countByMenuId(menuId);
	}
}
