package com.epas.common.dto;

import java.util.HashMap;
import java.util.List;

import lombok.Data;
import lombok.Generated;

/**
 * User Preferences Info Dto Class
 * 
 * @since 2023. 7. 26.
 * @author yookj
 * @see
 * 
 * 
 *      <pre>
 *  Class Name : UserPreferenceDto.java
 *  Description : 

 *  << Modification History >>
 *  
 *  Date              Modifier           Description
 *  ----------        -----------        ----------------------
 *  20230726           kjyoo              initial
 © Hitachi High-Tech Corporation.  2023. All rights reserved.
 *      </pre>
 */
@Data
@Generated
public class UserPreferenceDto {

	private String comCdGrpId;
	private String comCdId;
	private String usrEnvId;
	private String envSetId;
	private String envSetDiv;
	private String parentComCdId;
	private String comCdNm;
	private String comCdDesc;
	private Long comCdSortOrd;
	private String comCdUseAt;
	private String envParent;
	private String envSetVal;
	private String oldEnvSetVal;
	private String createdUsrId;
	private String createdDt;
	private String updatedUsrId;
	private String updatedDt;
	
	List<HashMap<String,String>> prefInfos;

}