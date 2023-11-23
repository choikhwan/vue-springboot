package com.epas.admin.dto;

import lombok.Data;
import lombok.Generated;

/**
 * 사용자 그룹 정보 Dto Class
 * 
 * @since 2023. 6. 13.
 * @author choih
 * @see
 * 
 * 
 *      <pre>
 *  Class Name : UsrGroupInfoDto.java
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
public class UsrGroupInfoDto {

	private long usrGroupSeq;
	private String usrGroupNm;
	private String usrGroupDesc;
	private long usrCnt;
	private String createdUsrId;
	private String createdDt;
	private String updatedUsrId;
	private String updatedDt;

}
