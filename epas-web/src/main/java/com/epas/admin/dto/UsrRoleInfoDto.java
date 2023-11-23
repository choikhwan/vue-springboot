package com.epas.admin.dto;

import java.util.ArrayList;

import lombok.Data;
import lombok.Generated;

/**
 * 사용자 Role 정보 Dto Class
 * 
 * @since 2023. 6. 13.
 * @author choih
 * @see
 * 
 * 
 *      <pre>
 *  Class Name : UsrRoleInfoDto.java
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
public class UsrRoleInfoDto {

	private long 	roleSeq;
	private String	roleNm;
	private String	roleDesc;
	private long 	usrCnt;
	private String	useYn;
	private String	createdUsrId;
	private String	createdDt;
	private String	updatedUsrId;
	private String	updatedDt;
	private ArrayList<RoleMenuInfoDto> 	roleMenuInfoList;
}
