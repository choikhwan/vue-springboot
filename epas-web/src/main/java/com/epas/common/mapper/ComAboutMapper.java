package com.epas.common.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.epas.common.dto.ComAboutDto;


/**
* ComAboutMapper 클래스
* 
* @since 2023.09.21
* @author yookj
* @see <pre>
*  Class Name : ComAboutMapper.java
*  Description : 
*
*  << Modification History >>
*  
*  Date              Author             Description
*  ----------        -----------        ----------------------
*  2023.09.21        yookj              initial
*
© Hitachi High-Tech Corporation.  2023. All rights reserved.
*  </pre>
*/

@Mapper
public interface ComAboutMapper {		
	
    /**
     * About Msg List 조회
     *
     * @method : selectComAboutList
     * @date : 20230921
     * @author : yookj
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
    List<ComAboutDto> selectComAboutList();
	
}
