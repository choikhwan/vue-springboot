package com.epas.login.mapper;

import org.apache.ibatis.annotations.Mapper;

/**
* MemberMapper xml interface Class. 
* 
* @since 20230626
* @author kjyoo
* @see <pre>
*  Class Name : MemberMapper2.java
*  Description : 
*
*  << Modification History >>
*  
 *  Date              Author             Description
*  ----------        -----------        ----------------------
*  20230626           kjyoo              initial
*
© Hitachi High-Tech Corporation.  2023. All rights reserved.
*  </pre>
*/
@Mapper
public interface MemberMapper2 {


	/**
     * 회원 비밀번호 만료기간
     *
     * @method : expiredPasswordByLoginId
     * @date : 20230626
     * @author : kjyoo
     * @param : params
 	 * 로그인 유저 정보
     * 
     * @return 만료기간 Flag (Y-만료, N-유효) 
     * @throws 예외가 있다면 예외 클래스 및 설명
     *     << Modification History >>
     *     
     *     Date        Author       Description
     *     ---------   --------     --------------------
     *     20230626     kjyoo       신규생성
     */
	String expiredPasswordByLoginId(String usrId);

}
