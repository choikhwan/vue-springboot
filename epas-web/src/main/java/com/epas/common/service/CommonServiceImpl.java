package com.epas.common.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epas.common.dto.CmmCodeSeqDto;
import com.epas.common.dto.ComCdDto;
import com.epas.common.dto.UsrMenuRoleDto;
import com.epas.common.mapper.CommonMapper;
import com.epas.login.dto.MemberInfoDto;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

/**
 * CommonServiceImpl Class
 *
 * @since 2023.06.19.
 * @author choih
 * @see <pre>
 *  Class Name : CommonServiceImpl.java
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
public class CommonServiceImpl implements CommonService {
	@Autowired
	private CommonMapper commonMapper;


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
	@Override	
    public List<ComCdDto> selectCmmCode(HashMap<String, Object> map){
    	return commonMapper.selectCmmCode(map);
    }

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
	@Override
    public List<ComCdDto> selectCmmCdGrp(HashMap<String, Object> map){
    	return commonMapper.selectCmmCdGrp(map);    	
    }
    
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
	@Override
    public List<CmmCodeSeqDto> selectUserGroup(CmmCodeSeqDto vo){
    	return commonMapper.selectUserGroup(vo);
    }

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
	@Override
    public List<CmmCodeSeqDto> selectUserRole(CmmCodeSeqDto vo){
    	return commonMapper.selectUserRole(vo);
    }
  
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
	@Override
    public List<ComCdDto> selectParentMenu(ComCdDto vo){
    	return commonMapper.selectParentMenu(vo);
    }	

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
	@Override
    public List<ComCdDto> selectParentComCd(HashMap<String, Object> map){
    	return commonMapper.selectParentComCd(map);
    }	
	
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
    public UsrMenuRoleDto selectUsrMenuRole(HashMap<String, Object> map){
    	return commonMapper.selectUsrMenuRole(map);
	}	
    
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
    public UsrMenuRoleDto getMenuRole(HttpServletRequest request) {

        HttpSession session = request.getSession(); 
		MemberInfoDto vo = (MemberInfoDto)session.getAttribute("loginMember");
		String loginId = vo.getUsrId();
        String requsturi = request.getRequestURI();   //프로젝트경로부터 파일까지의 경로값을 얻어옴 (/test/index.jsp)

        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("usrId", loginId);
        map.put("url", requsturi);
		UsrMenuRoleDto usrMenuRole = selectUsrMenuRole(map);        
		
        return usrMenuRole;
    }
    
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
	@Override
    public List<HashMap<String, String>> selectTapMenuList(HashMap<String, Object> map){
    	return commonMapper.selectTapMenuList(map);
    }	
}
