package com.epas.admin.dto;

import lombok.Data;
import lombok.Generated;

/**
 * 공통 코드 그룹 정보 Dto Class
 * 
 * @since 2023. 6. 13.
 * @author choih
 * @see
 * 
 * 
 *      <pre>
 *  Class Name : ComCdGrpInfoDto.java
 *  Description : 

 *  << Modification History >>
 *  
 *  Date              Modifier           Description
 *  ----------        -----------        ----------------------
 *  2023.06.13.       choih              initial
 *
 © Hitachi High-Tech Corporation.  2023. All rights reserved.
 *      </pre>
 */

@Data
@Generated
public class ComCdGrpInfoDto {

	private String comCdGrpId;
	private String comCdGrpNm;
	private String comCdGrpNmKr;
	private String comCdGrpNmJa;
	private String comCdGrpDesc;
	private String comCdGrpDescKr;
	private String comCdGrpDescJa;
	private String comCdGrpSortOrd;
	private String comCdUseAt;
	private long comCdCnt;
	private String createdUsrId;
	private String createdDt;
	private String updatedUsrId;
	private String updatedDt;
}
