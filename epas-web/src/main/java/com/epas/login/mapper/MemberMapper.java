package com.epas.login.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.epas.login.dto.MemberInfoDto;
import com.epas.login.dto.UserConnInfoDto;

/**
* MemberMapper xml interface Class. 
* 
* @since 20230626
* @author kjyoo
* @see <pre>
*  Class Name : MemberMapper.java
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
public interface MemberMapper {

	/**
     * 로그인 사용자 정보
     *
     * @method : findByLoginId
     * @date : 20230626
     * @author : kjyoo
     * @param : loginId
 	 * 로그인 유저 ID
     * 
     * @return 로그인 멤버 사용자 정보
     * @throws 예외가 있다면 예외 클래스 및 설명
     *     << Modification History >>
     *     
     *     Date        Author       Description
     *     ---------   --------     --------------------
     *     20230626     kjyoo       신규생성
     */
    MemberInfoDto findByLoginId(String loginId);

    /**
     * 회원 비밀번호 수정
     *
     * @method : updatePassword
     * @date : 20230626
     * @author : kjyoo
     * @param : params
 	 * 로그인 유저 정보
     * 
     * @return 로그인 멤버 사용자 정보
     * @throws 예외가 있다면 예외 클래스 및 설명
     *     << Modification History >>
     *     
     *     Date        Author       Description
     *     ---------   --------     --------------------
     *     20230626     kjyoo       신규생성
     */
	void updatePassword(MemberInfoDto params);
	
	/**
     * 회원 정보 수정
     *
     * @method : updateUserInfo
     * @date : 20230626
     * @author : kjyoo
     * @param : params
 	 * 로그인 유저 정보
     * 
     * @return 로그인 멤버 사용자 정보
     * @throws 예외가 있다면 예외 클래스 및 설명
     *     << Modification History >>
     *     
     *     Date        Author       Description
     *     ---------   --------     --------------------
     *     20230626     kjyoo       신규생성
     */
	void updateUserInfo(MemberInfoDto params);
	
	/**
     * 회원 접속 IP 입력
     *
     * @method : updateUserIp
     * @date : 20230626
     * @author : kjyoo
     * @param : params
 	 * 로그인 유저 정보
     * 
     * @return 로그인 사용자 IP 저장
     * @throws 예외가 있다면 예외 클래스 및 설명
     *     << Modification History >>
     *     
     *     Date        Author       Description
     *     ---------   --------     --------------------
     *     20230626     kjyoo       신규생성
     */
	void updateUserIp(MemberInfoDto params);
	
	/**
     * ADMIN 회원 접속 가능 수
     *
     * @method : selectLoginAdminNum
     * @date : 20231006
     * @author : kjyoo
     * @param : params
 	 * 로그인 가능한 Admin 접속 가능 수
     * 
     * @return 로그인 가능한 Admin 접속 가능 수
     * @throws 예외가 있다면 예외 클래스 및 설명
     *     << Modification History >>
     *     
     *     Date        Author       Description
     *     ---------   --------     --------------------
     *     20231006     kjyoo       신규생성
     */
	int selectLoginAdminNum();
	
	/**
     * 일반 User 회원 접속 가능 수
     *
     * @method : selectLoginUserNum
     * @date : 20231006
     * @author : kjyoo
     * @param : params
 	 * 로그인 가능한 일반 User 접속 가능 수
     * 
     * @return 로그인 가능한 일반 User 접속 가능 수
     * @throws 예외가 있다면 예외 클래스 및 설명
     *     << Modification History >>
     *     
     *     Date        Author       Description
     *     ---------   --------     --------------------
     *     20231006     kjyoo       신규생성
     */
	int selectLoginUserNum();
	
	/**
     * 일반 User 회원 접속 가능 수
     *
     * @method : countLoginUserConn
     * @date : 20231010
     * @author : kjyoo
     * 
     * @return 로그인된 User 접속 수
     * @throws 예외가 있다면 예외 클래스 및 설명
     *     << Modification History >>
     *     
     *     Date        Author       Description
     *     ---------   --------     --------------------
     *     20231010     kjyoo       신규생성
     */
	int countLoginUserConn();
	
	/**
     * 로그인 User 의 로그인 Data 조회
     *
     * @method : selectLoginUserConn
     * @date : 20231010
     * @author : kjyoo
     * 
     * @return 로그인된 User 접속 Data
     * @throws 예외가 있다면 예외 클래스 및 설명
     *     << Modification History >>
     *     
     *     Date        Author       Description
     *     ---------   --------     --------------------
     *     20231010     kjyoo       신규생성
     */
	List<UserConnInfoDto> selectLoginUserConn(final String loginId);
	
	/**
     * 유저 Login Connection정보 Insert
     *
     * @method : insertLoginUserConn
     * @date : 20231010
     * @author : kjyoo
     * @param : map
 	 * 로그인 유저 정보
     * 
     * @return 
     * @throws 예외가 있다면 예외 클래스 및 설명
     *     << Modification History >>
     *     
     *     Date        Author       Description
     *     ---------   --------     --------------------
     *     20231010     kjyoo       신규생성
     */
	void insertLoginUserConn(HashMap<String, Object> map);
	
	/**
     * Login 상태의 user 정보를 LOGOUT으로 Data Insert
     *
     * @method : insertLoginUserConn
     * @date : 20231011
     * @author : kjyoo
 	 * 
     * @return 
     * @throws 예외가 있다면 예외 클래스 및 설명
     *     << Modification History >>
     *     
     *     Date        Author       Description
     *     ---------   --------     --------------------
     *     20231011     kjyoo       신규생성
     */
	void insertUserLogoutData(UserConnInfoDto param);

}
