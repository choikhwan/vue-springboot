package com.epas.admin.dto;

import lombok.Data;
import lombok.Generated;

/**
 * Role Menu 정보 Dto Class
 * 
 * @since 2023. 6. 13.
 * @author choih
 * @see
 * 
 * 
 *      <pre>
 *  Class Name : RoleMenuInfoDto.java
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
public class RoleMenuInfoDto {

	private long	roleMenuSeq;
	private String	roleRead;
	private String	roleCreate;
	private String	roleUpdate;
	private String	roleDelete;
	private long	roleSeq;
	private String	menuId;
	private String  menuParentId;
	private String  menuNm;
	private String  url;
	private String  sortOrd;
	private String  path;
	private String  level;
	private String	useYn;
	private String	createdUsrId;
	private String	createdDt;
	private String	updatedUsrId;
	private String	updatedDt;
	private int		menuIdKey;
	private int		menuParentIdKey;

}
