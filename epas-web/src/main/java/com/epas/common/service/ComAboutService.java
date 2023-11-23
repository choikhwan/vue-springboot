package com.epas.common.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.epas.common.dto.ComAboutDto;
import com.epas.common.mapper.ComAboutMapper;

import lombok.RequiredArgsConstructor;

/**
* User Preference Service Class 
* 
* @since 20230921
* @author kjyoo
* @see <pre>
*  Class Name : ComAboutService.java
*  Description : 
*
*  << Modification History >>
*  
 *  Date              Author             Description
*  ----------        -----------        ----------------------
*  20230921           kjyoo              initial
*
© Hitachi High-Tech Corporation.  2023. All rights reserved.
*  </pre>
*/
@Service
@RequiredArgsConstructor
public class ComAboutService {

	private final ComAboutMapper comAboutMapper;
    
    /**
	* About data 조회
	*
	* @method : selectComAboutList
	* @date : 2023.09.21
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
	*     2023.09.21  yookj        initial
	*/ 
    public List<ComAboutDto> selectComAboutList() {
    	return comAboutMapper.selectComAboutList();
    }   

}