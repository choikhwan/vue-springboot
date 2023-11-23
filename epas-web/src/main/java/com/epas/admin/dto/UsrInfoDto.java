package com.epas.admin.dto;

import lombok.Data;
import lombok.Generated;

/**
 * 사용자 정보 Dto Class
 * 
 * @since 2023. 6. 13.
 * @author choih
 * @see
 * 
 * 
 *      <pre>
 *  Class Name : UsrInfoDto.java
 *  Description : 

 *  << Modification History >>
 *  
 *  Date              Modifier           Description
 *  ----------        -----------        ----------------------
 *
 © Hitachi High-Tech Corporation.  2023. All rights reserved.
 *      </pre>
 */

@Data
@Generated
public class UsrInfoDto {

	private String usrId;
	private String email;
	private String usrNm;
	private String usrPwd;
	private String usrPwdCreatedDt;
	private String usrTel;
	private String menuOrd;
	private Long roleSeq;
	private Long groupSeq;
	private String createdUsrId;
	private String createdDt;
	private String updatedUsrId;
	private String updatedDt;
	private String usrLang;
	private String useYn;
}
