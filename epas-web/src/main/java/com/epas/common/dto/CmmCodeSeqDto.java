package com.epas.common.dto;

import lombok.Data;
import lombok.Generated;

/**
 * user group, user role 같이 seq 형태의 데이터를 공통코드 형태로 사용하기 위한 Dto Class
 * 
 * @since 2023. 6. 13.
 * @author choih
 * @see
 * 
 * 
 *      <pre>
 *  Class Name : CmmCodeSeqDto.java
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
public class CmmCodeSeqDto {

	private Long code;
    private String codeId;
    private String codeNm;
    private String codeDc;
    private String codeAndNm;
}
