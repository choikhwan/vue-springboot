package com.epas.login.dto;

import lombok.Data;
import lombok.Generated;

/**
 * User Info Request Dto Class
 * 
 * @since 2023. 6. 26.
 * @author yookj
 * @see
 * 
 * 
 *      <pre>
 *  Class Name : MemberInfoDto.java
 *  Description : 

 *  << Modification History >>
 *  
 *  Date              Modifier           Description
 *  ----------        -----------        ----------------------
 *  20230626           kjyoo              initial
 © Hitachi High-Tech Corporation.  2023. All rights reserved.
 *      </pre>
 */
@Data
@Generated
public class MemberInfoDto {

	private String usrId;
	private String email;
	private String usrNm;
	private String usrPwd;
	private String usrPwdCreatedDt;
	private String usrTel;
	private String menuOrd;
	private Long roleSeq;
	private String roleNm;
	private Long groupSeq;
	private String usrGroupNm;
	private String usrLang;
	private String usrIp;
	private String createdUsrId;
	private String createdDt;
	private String updatedUsrId;
	private String updatedDt;
	private String lastLoginDt;
	private String lastNotifyClearDt;

}
