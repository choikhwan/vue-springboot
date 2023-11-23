package com.epas.common.service;

import java.util.HashMap;
import java.util.List;

import com.epas.common.dto.CmmCodeSeqDto;
import com.epas.common.dto.ComCdDto;
import com.epas.common.dto.UsrMenuRoleDto;

import jakarta.servlet.http.HttpServletRequest;


/**
 *  EPAS 서비스 인터페이스
 *
 * @since 2023. 6. 19.
 * @author choih
 * @see <pre>
 *  Class Name : CommonService.java
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
public interface CommonService {

	/**
	* 주어진 조건에 따른 공통코드를 불러온다.
	*
	* @method : selectCmmCode
	* @date : 2023.06.19
	* @author : choih
	* @param : map
	* 상기 파라미터1 설명
	* @param : 파라미터명2
	* 상기 파라미터2 설명
	* @return List<ComCdDto>
	* @throws 예외가 있다면 예외 클래스 및 설명
	*     << Modification History >>
	*     
	*     Date        Author       Description
	*     ---------   --------     --------------------
	*     2023.06.19  choih        initial
	*/
    public List<ComCdDto> selectCmmCode(HashMap<String, Object> map);
    
	/**
	* 주어진 조건에 따른 공통그룹 코드를 불러온다.
	*
	* @method : selectCmmCdGrp
	* @date : 2023.06.19
	* @author : choih
	* @param : map
	* 상기 파라미터1 설명
	* @param : 파라미터명2
	* 상기 파라미터2 설명
	* @return List<ComCdDto>
	* @throws 예외가 있다면 예외 클래스 및 설명
	*     << Modification History >>
	*     
	*     Date        Author       Description
	*     ---------   --------     --------------------
	*     2023.06.19  choih        initial
	*/  
    public List<ComCdDto> selectCmmCdGrp(HashMap<String, Object> map);
    
	/**
	* 공통코드로 사용할그룹정보를 를 불러온다.
	*
	* @method : selectUserGroup
	* @date : 2023.06.19
	* @author : choih
	* @param : vo
	* CmmCodeSeqDto
	* @param : 파라미터명2
	* 상기 파라미터2 설명
	* @return List<CmmDetailCode>
	* @throws 예외가 있다면 예외 클래스 및 설명
	*     << Modification History >>
	*     
	*     Date        Author       Description
	*     ---------   --------     --------------------
	*     2023.06.19  choih        initial
	*/
    public List<CmmCodeSeqDto> selectUserGroup(CmmCodeSeqDto vo);
    
	/**
	* 공통코드로 사용할 Role정보를 를 불러온다.
	*
	* @method : selectUserRole
	* @date : 2023.06.19
	* @author : choih
	* @param : vo
	* CmmCodeSeqDto
	* @param : 파라미터명2
	* 상기 파라미터2 설명
	* @return List<CmmDetailCode>
	* @throws 예외가 있다면 예외 클래스 및 설명
	*     << Modification History >>
	*     
	*     Date        Author       Description
	*     ---------   --------     --------------------
	*     2023.06.19  choih        initial
	*/
    public List<CmmCodeSeqDto> selectUserRole(CmmCodeSeqDto vo);    
    
	/**
	* 공통코드로 사용할 Parent Menu 정보를 를 불러온다.
	*
	* @method : selectParentMenu
	* @date : 2023.06.19
	* @author : choih
	* @param : vo
	* ComCdDto
	* @param : 파라미터명2
	* 상기 파라미터2 설명
	* @return List<ComCdDto>
	* @throws 예외가 있다면 예외 클래스 및 설명
	*     << Modification History >>
	*     
	*     Date        Author       Description
	*     ---------   --------     --------------------
	*     2023.06.19  choih        initial
	*/
    public List<ComCdDto> selectParentMenu(ComCdDto vo);
    
	/**
	* 공통코드로 사용할 Parent 코드 정보를 를 불러온다.
	*
	* @method : selectParentComCd
	* @date : 2023.06.19
	* @author : choih
	* @param : map
	* 
	* @return List<ComCdDto>
	* @throws 예외가 있다면 예외 클래스 및 설명
	*     << Modification History >>
	*     
	*     Date        Author       Description
	*     ---------   --------     --------------------
	*     2023.06.19  choih        initial
	*/
    public List<ComCdDto> selectParentComCd(HashMap<String, Object> map);
    
	/**
	* 공통코드로 사용할 Role 코드 정보를 를 불러온다.
	*
	* @method : selectUsrMenuRole
	* @date : 2023.06.19
	* @author : choih
	* @param : map
	* 
	* @return UsrMenuRoleDto
	* @throws 예외가 있다면 예외 클래스 및 설명
	*     << Modification History >>
	*     
	*     Date        Author       Description
	*     ---------   --------     --------------------
	*     2023.06.19  choih        initial
	*/
    public UsrMenuRoleDto selectUsrMenuRole(HashMap<String, Object> map);
    
	/**
	* HttpServletRequest 를 받아서 현재화면의 crud권한을 조회한다. 
	*
	* @method : getMenuRole
	* @date : 2023.06.19
	* @author : choih
	* @param : request
	* HttpServletRequest
	* @return UsrMenuRoleDto
	* @throws 예외가 있다면 예외 클래스 및 설명
	*     << Modification History >>
	*     
	*     Date        Author       Description
	*     ---------   --------     --------------------
	*     2023.06.19  choih        initial
	*/
    public UsrMenuRoleDto getMenuRole(HttpServletRequest request);
    
	/**
	* Tab Menu List를 를 불러온다.
	*
	* @method : selectTapMenuList
	* @date : 2023.10.05
	* @author : choih
	* @param : map
	* HashMap<String, Object>
	* @return List<HashMap<String, String>> 
	* @throws 
	*     << Modification History >>
	*     
	*     Date        Author       Description
	*     ---------   --------     --------------------
	*     2023.10.05  choih        initial
	*/
    public List<HashMap<String, String>> selectTapMenuList(HashMap<String, Object> map);
}
