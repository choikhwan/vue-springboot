package com.epas.admin.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epas.admin.dto.ComCdDetailInfoDto;
import com.epas.admin.dto.ComCdGrpInfoDto;
import com.epas.admin.mapper.CmmCodeMapper;

import lombok.RequiredArgsConstructor;

/**
 * CmmCode ServiceImpl Class
 *
 * @since 2023.06.19.
 * @author choih
 * @see <pre>
 *  Class Name : CmmCodeServiceImpl.java
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
public class CmmCodeServiceImpl implements CmmCodeService {
	@Autowired
	private CmmCodeMapper cmmCodeMapper;

	/**
	* 주어진 조건에 따른 공통 코드 그룹 정보 list를 불러온다.
	*
	* @method : selectComCdGrpInfoList
	* @date : 2023.06.26
	* @author : choih
	* @param : map
	* 상기 파라미터1 설명
	* @return List<ComCdGrpInfoDto>
	* @throws 예외가 있다면 예외 클래스 및 설명
	*     << Modification History >>
	*     
	*     Date        Author       Description
	*     ---------   --------     --------------------
	*     2023.06.26  choih        initial
	*/
    public List<ComCdGrpInfoDto> selectComCdGrpInfoList(HashMap<String, Object> map){
		return cmmCodeMapper.selectComCdGrpInfoPagingList(map);
		
	}
    
	/**
	* 주어진 조건에 따른 공통 코드 그룹 정보 total Count를 불러온다.
	*
	* @method : selectComCdGrpInfoTotalCount
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
	public int selectComCdGrpInfoTotalCount(HashMap<String, Object> map){

		int totalCount = cmmCodeMapper.selectComCdGrpInfoPagingCount(map);
		
		return totalCount;
	}
	
	/**
	* 공통 코드 그룹 정보 insert
	*
	* @method : insertComCdGrpInfo
	* @date : 2023.06.26
	* @author : choih
	* @param : ComCdGrpInfoDto params
	* 상기 파라미터1 설명
	* @return void
	* @throws 예외가 있다면 예외 클래스 및 설명
	*     << Modification History >>
	*     
	*     Date        Author       Description
	*     ---------   --------     --------------------
	*     2023.06.26  choih        initial
	*/
	public void insertComCdGrpInfo(ComCdGrpInfoDto comCdGrpInfoDto) {
		cmmCodeMapper.insertComCdGrpInfo(comCdGrpInfoDto);
	}

	/**
	* 공통 코드 그룹 정보 update
	*
	* @method : updateComCdGrpInfo
	* @date : 2023.06.26
	* @author : choih
	* @param : ComCdGrpInfoDto params
	* 상기 파라미터1 설명
	* @return void
	* @throws 예외가 있다면 예외 클래스 및 설명
	*     << Modification History >>
	*     
	*     Date        Author       Description
	*     ---------   --------     --------------------
	*     2023.06.26  choih        initial
	*/	
	public void updateComCdGrpInfo(ComCdGrpInfoDto comCdGrpInfoDto) {
		cmmCodeMapper.updateComCdGrpInfo(comCdGrpInfoDto);
	}

	/**
	* 공통 코드 그룹 정보 delete
	*
	* @method : deleteComCdGrpInfo
	* @date : 2023.06.26
	* @author : choih
	* @param : ComCdGrpInfoDto params
	* 상기 파라미터1 설명
	* @return void
	* @throws 예외가 있다면 예외 클래스 및 설명
	*     << Modification History >>
	*     
	*     Date        Author       Description
	*     ---------   --------     --------------------
	*     2023.06.26  choih        initial
	*/	
	public void deleteComCdGrpInfo(ComCdGrpInfoDto comCdGrpInfoDto) {
		cmmCodeMapper.deleteComCdGrpInfo(comCdGrpInfoDto);
	}
	
	/**
	* 주어진 조건에 따른 조회 Count를 불러온다.
	*
	* @method : countByComCdGrpId
	* @date : 2023.06.26
	* @author : choih
	* @param : cdGrpId
	* 상기 파라미터1 설명
	* @return count
	* @throws 예외가 있다면 예외 클래스 및 설명
	*     << Modification History >>
	*     
	*     Date        Author       Description
	*     ---------   --------     --------------------
	*     2023.06.26  choih        initial
	*/	
	public int countByComCdGrpId(String cdGrpId) {
		return cmmCodeMapper.countByComCdGrpId(cdGrpId);
	}
	
	/**
	* 주어진 조건에 따른 공통 코드 상세 정보 list를 불러온다.
	*
	* @method : selectComCdDetailInfoList
	* @date : 2023.06.26
	* @author : choih
	* @param : vo
	* 상기 파라미터1 설명
	* @return List<ComCdDetailInfoDto>
	* @throws 예외가 있다면 예외 클래스 및 설명
	*     << Modification History >>
	*     
	*     Date        Author       Description
	*     ---------   --------     --------------------
	*     2023.06.26  choih        initial
	*/
    public List<ComCdDetailInfoDto> selectComCdDetailInfoList(HashMap<String, Object> map){
		return cmmCodeMapper.selectComCdDetailInfoPagingList(map);
		
	}
    
	/**
	* 주어진 조건에 따른 공통 코드 정보 total Count를 불러온다.
	*
	* @method : selectComCdDetailInfoTotalCount
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
	public int selectComCdDetailInfoTotalCount(HashMap<String, Object> map){

		int totalCount = cmmCodeMapper.selectComCdDetailInfoPagingCount(map);
		
		return totalCount;
	}
	
	/**
	* 공통 코드 상세 정보 insert
	*
	* @method : insertComCdDetailInfo
	* @date : 2023.06.26
	* @author : choih
	* @param : ComCdDetailInfoDto params
	* 상기 파라미터1 설명
	* @return void
	* @throws 예외가 있다면 예외 클래스 및 설명
	*     << Modification History >>
	*     
	*     Date        Author       Description
	*     ---------   --------     --------------------
	*     2023.06.26  choih        initial
	*/
	public void insertComCdDetailInfo(ComCdDetailInfoDto comCdDetailInfoDto) {
		cmmCodeMapper.insertComCdDetailInfo(comCdDetailInfoDto);
	}

	/**
	* 공통 코드 상세 정보 update
	*
	* @method : updateComCdDetailInfo
	* @date : 2023.06.26
	* @author : choih
	* @param : ComCdDetailInfoDto params
	* 상기 파라미터1 설명
	* @return void
	* @throws 예외가 있다면 예외 클래스 및 설명
	*     << Modification History >>
	*     
	*     Date        Author       Description
	*     ---------   --------     --------------------
	*     2023.06.26  choih        initial
	*/	
	public void updateComCdDetailInfo(ComCdDetailInfoDto comCdDetailInfoDto) {
		cmmCodeMapper.updateComCdDetailInfo(comCdDetailInfoDto);
	}

	/**
	* 공통 코드 그룹 정보 delete
	*
	* @method : deleteComCdDetailInfo
	* @date : 2023.06.26
	* @author : choih
	* @param : ComCdDetailInfoDto params
	* 상기 파라미터1 설명
	* @return void
	* @throws 예외가 있다면 예외 클래스 및 설명
	*     << Modification History >>
	*     
	*     Date        Author       Description
	*     ---------   --------     --------------------
	*     2023.06.26  choih        initial
	*/	
	public void deleteComCdDetailInfo(ComCdDetailInfoDto comCdDetailInfoDto) {
		cmmCodeMapper.deleteComCdDetailInfo(comCdDetailInfoDto);
	}
	
	/**
	* 주어진 조건에 따른 조회 Count를 불러온다.
	*
	* @method : countByComCdId
	* @date : 2023.06.26
	* @author : choih
	* @param : params
	* ComCdDetailInfoDto
	* @return count
	* @throws 예외가 있다면 예외 클래스 및 설명
	*     << Modification History >>
	*     
	*     Date        Author       Description
	*     ---------   --------     --------------------
	*     2023.06.26  choih        initial
	*/
	public int countByComCdId(ComCdDetailInfoDto comCdDetailInfoDto) {
		return cmmCodeMapper.countByComCdId(comCdDetailInfoDto);
	}
	
	/**
	* 그룹코드로 등록된 공통코드 건수 리턴(코드그룹 삭제시 체크)
	*
	* @method : checkComCdIdCnt
	* @date : 2023.06.26
	* @author : choih
	* @param : map
	* 
	* @return count
	* @throws 예외가 있다면 예외 클래스 및 설명
	*     << Modification History >>
	*     
	*     Date        Author       Description
	*     ---------   --------     --------------------
	*     2023.06.26  choih        initial
	*/
	public int checkComCdIdCnt(HashMap<String, Object> map){
		return cmmCodeMapper.checkComCdIdCnt(map);
	}
	
}
