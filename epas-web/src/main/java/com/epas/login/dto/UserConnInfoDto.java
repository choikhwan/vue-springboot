package com.epas.login.dto;

import java.util.HashMap;
import java.util.List;

import lombok.Data;
import lombok.Generated;

/**
 * User Connection Info Dto Class
 * 
 * @since 2023. 10. 10.
 * @author yookj
 * @see
 * 
 * 
 *      <pre>
 *  Class Name : UserConnInfoDto.java
 *  Description : 

 *  << Modification History >>
 *  
 *  Date              Modifier           Description
 *  ----------        -----------        ----------------------
 *  20231010           kjyoo              initial
 © Hitachi High-Tech Corporation.  2023. All rights reserved.
 *      </pre>
 */
@Data
@Generated
public class UserConnInfoDto {

	private Long connId;
	private String usrId;
	private String usrSessionId;
	private String usrAct;
	private String createdDt;
	
	List<HashMap<String,String>> connInfos;

}
