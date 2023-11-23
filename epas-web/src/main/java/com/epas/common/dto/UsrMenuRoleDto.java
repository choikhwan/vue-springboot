package com.epas.common.dto;

import lombok.Data;
import lombok.Generated;

/**
 * 사용자 메뉴 Role 정보 Dto Class
 * 
 * @since 2023. 6. 13.
 * @author choih
 * @see
 * 
 * 
 *      <pre>
 *  Class Name : UsrMenuRoleDto.java
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
public class UsrMenuRoleDto {

	private String roleRead;
	private String roleCreate;
	private String roleUpdate;
	private String roleDelete;
	private String menuId;
	private String menuNm;
	private String url;
	private String usrId;

}
