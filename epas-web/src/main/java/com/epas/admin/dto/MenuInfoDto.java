package com.epas.admin.dto;

import lombok.Data;
import lombok.Generated;

/**
 * Menu 정보 Dto Class
 * 
 * @since 2023. 6. 13.
 * @author choih
 * @see
 * 
 * 
 *      <pre>
 *  Class Name : MenuInfoDto.java
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
public class MenuInfoDto {

	private String menuId;
	private String menuNm;
	private String menuParentId;
	private String sortOrd;
	private String url;
	private String useYn;	
	private String createdUsrId;
	private String createdDt;
	private String updatedUsrId;
	private String updatedDt;

}
