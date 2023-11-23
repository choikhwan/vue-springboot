package com.epas.admin.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.epas.admin.dto.ComCdDetailInfoDto;
import com.epas.admin.dto.ComCdGrpInfoDto;


/**
* 공통코드등 전체 업무에서 공용해서 사용해야 하는 서비스를 정의하기위한 데이터 접근 클래스
* 
* @since 2023.06.19
* @author choih
* @see <pre>
*  Class Name : CmmCodeMapper.java
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
public interface CmmCodeMapper {
		
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
	public List<ComCdGrpInfoDto> selectComCdGrpInfoList(HashMap<String, Object> map);
	
	/**
	* 주어진 조건에 따른 공통 코드 그룹 정보 paging list를 불러온다.
	*
	* @method : selectComCdGrpInfoPagingList
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
	public List<ComCdGrpInfoDto> selectComCdGrpInfoPagingList(HashMap<String, Object> map);

	/**
	* 주어진 조건에 따른 공통 코드 그룹 정보 total Count를 불러온다.
	*
	* @method : selectComCdGrpInfoPagingCount
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
	public int selectComCdGrpInfoPagingCount(HashMap<String, Object> map);

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
	void insertComCdGrpInfo(ComCdGrpInfoDto params);
	
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
	void updateComCdGrpInfo(ComCdGrpInfoDto params);
	
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
	void deleteComCdGrpInfo(ComCdGrpInfoDto params);
	
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
	public int countByComCdGrpId(String cdGrpId);
	
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
	public List<ComCdDetailInfoDto> selectComCdDetailInfoList(HashMap<String, Object> map);

	/**
	* 주어진 조건에 따른 공통 코드 상세 정보 paging list를 불러온다.
	*
	* @method : selectComCdDetailInfoPagingList
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
	public List<ComCdDetailInfoDto> selectComCdDetailInfoPagingList(HashMap<String, Object> map);

	/**
	* 주어진 조건에 따른 공통 코드 정보 total Count를 불러온다.
	*
	* @method : selectComCdDetailInfoPagingCount
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
	public int selectComCdDetailInfoPagingCount(HashMap<String, Object> map);

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
	void insertComCdDetailInfo(ComCdDetailInfoDto params);
	
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
	void updateComCdDetailInfo(ComCdDetailInfoDto params);
	
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
	void deleteComCdDetailInfo(ComCdDetailInfoDto params);		

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
	public int countByComCdId(ComCdDetailInfoDto params);

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
	public int checkComCdIdCnt(HashMap<String, Object> map);
	
    
}
