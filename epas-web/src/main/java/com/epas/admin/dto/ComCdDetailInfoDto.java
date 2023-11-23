package com.epas.admin.dto;

import lombok.Data;
import lombok.Generated;

/**
 * 공통 코드 상세 정보 Dto Class
 * 
 * @since 2023. 6. 13.
 * @author choih
 * @see
 * 
 * 
 *      <pre>
 *  Class Name : ComCdDetailInfoDto.java
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
public class ComCdDetailInfoDto {

	private String comCdGrpId;
	private String comCdId;
	private String parentComCdId;
	private String parentComCdNm;
	private String comCdNm;
	private String comCdNmKr;
	private String comCdNmJa;
	private String comCdDesc;
	private String comCdDescKr;
	private String comCdDescJa;
	private String comCdSortOrd;
	private String comCdUseAt;
	private String createdUsrId;
	private String createdDt;
	private String updatedUsrId;
	private String updatedDt;

}
