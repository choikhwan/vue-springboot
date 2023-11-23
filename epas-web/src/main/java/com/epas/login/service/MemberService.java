package com.epas.login.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.epas.login.controller.UserSha256;
import com.epas.login.dto.MemberInfoDto;
import com.epas.login.dto.UserConnInfoDto;
import com.epas.login.mapper.MemberMapper;
import com.epas.login.mapper.MemberMapper2;

import lombok.RequiredArgsConstructor;

/**
* User Login Service Class 
* 
* @since 20230626
* @author kjyoo
* @see <pre>
*  Class Name : MemberService.java
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
@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberMapper memberMapper;
    private final MemberMapper2 memberMapper2;

    /**
     * 회원 상세정보 조회 
     *
     * @method : findMemberByLoginId
     * @date : 20230626
     * @author : kjyoo
     * @param : loginId
 	 * 사용자 로그인 ID 
 	 * 
     * @return 로그인 사용자 정보
     * @throws 예외가 있다면 예외 클래스 및 설명
     *     << Modification History >>
     *     
     *     Date        Author       Description
     *     ---------   --------     --------------------
     *     20230626     kjyoo       신규생성
     */ 
    public MemberInfoDto findMemberByLoginId(final String loginId) {
    	//System.out.println("findMemberByLoginId. \n");
        return memberMapper.findByLoginId(loginId);
    }
    
    /**
     * 로그인 - 로그인 password 일치 확인
     *
     * @method : login
     * @date : 20230626
     * @author : kjyoo
     * @param : loginId
 	 * 사용자 로그인 ID 
 	 * @param : password
 	 * 사용자 로그인 Password
 	 * 
     * @return 로그인 사용자 정보
     * @throws 예외가 있다면 예외 클래스 및 설명
     *     << Modification History >>
     *     
     *     Date        Author       Description
     *     ---------   --------     --------------------
     *     20230626     kjyoo       신규생성
     */ 
    public MemberInfoDto login(final String loginId, final String password) throws Exception {
        
        // 1. 회원 정보 및 비밀번호 조회
    	MemberInfoDto member = findMemberByLoginId(loginId);

        // 2. 회원 정보 및 비밀번호 체크
        String encryPassword = UserSha256.encrypt(password);           
        if (member == null || !member.getUsrPwd().equals(encryPassword)) {
            return null;
        }

        return member;
    }
    
    /**
     * get User 정보
     *
     * @method : getUserInfo
     * @date : 20230626
     * @author : kjyoo
     * @param : loginId
 	 * 사용자 로그인 ID 
 	 * 
     * @return 로그인 사용자 정보
     * @throws 예외가 있다면 예외 클래스 및 설명
     *     << Modification History >>
     *     
     *     Date        Author       Description
     *     ---------   --------     --------------------
     *     20230626     kjyoo       신규생성
     */ 
    public MemberInfoDto getUserInfo(final String loginId) {
        
        // 1. 회원 정보 및 비밀번호 조회
    	MemberInfoDto member = memberMapper.findByLoginId(loginId);

        return member;
    }
    
    /**
     * 로그인 유저의 패스워드 교체기간 호출
     *
     * @method : expiredPassword
     * @date : 20230626
     * @author : kjyoo
     * @param : usrId
 	 * 사용자 로그인 ID 
 	 * 
     * @return 패스워드 만료여부(Y:교체필요, N:교체불필요)
     * @throws 예외가 있다면 예외 클래스 및 설명
     *     << Modification History >>
     *     
     *     Date        Author       Description
     *     ---------   --------     --------------------
     *     20230626     kjyoo       신규생성
     */
    public String expiredPassword(final String usrId) {
        return memberMapper2.expiredPasswordByLoginId(usrId);
    }
        
    /**
     * 패스워드 수정
     *
     * @method : updateUserPassword
     * @date : 20230626
     * @author : kjyoo
     * @param : loginId
 	 * 사용자 로그인 ID 
 	 * @param : password
 	 * 사용자 로그인 Password
 	 * 
     * @return 로그인 사용자 정보
     * @throws 예외가 있다면 예외 클래스 및 설명
     *     << Modification History >>
     *     
     *     Date        Author       Description
     *     ---------   --------     --------------------
     *     20230626     kjyoo       신규생성
     */
    public MemberInfoDto updateUserPassword(final String loginId, final String password) throws Exception {
    	
    	MemberInfoDto member = memberMapper.findByLoginId(loginId);
    	if(member == null) {
    		new NullPointerException("수정할 정보가 존재하지 않습니다.");
    	}
    	
    	String encryPassword = UserSha256.encrypt(password);
    	member.setUsrPwd(encryPassword);
    	
        memberMapper.updatePassword(member);
        return member;
    }
    
    /**
     * 유저 정보 수정
     *
     * @method : updateUserInfo
     * @date : 20230626
     * @author : kjyoo
     * @param : loginId
 	 * 사용자 로그인 ID 
 	 * @param : password
 	 * 사용자 로그인 Password
 	 * 
     * @return 로그인 사용자 정보
     * @throws 예외가 있다면 예외 클래스 및 설명
     *     << Modification History >>
     *     
     *     Date        Author       Description
     *     ---------   --------     --------------------
     *     20230626     kjyoo       신규생성
     */
    public MemberInfoDto updateUserInfo(final String loginId, final String name, final String phone, final String email, final String menuOrd) {
    	
    	MemberInfoDto member = memberMapper.findByLoginId(loginId);
    	if(member == null) {
    		new NullPointerException("message.alert_0009");
    	}
    	
    	member.setUsrNm(name);
    	member.setUsrTel(phone);
    	member.setEmail(email);
    	member.setMenuOrd(menuOrd);
    	
        memberMapper.updateUserInfo(member);
        return member;
    }
    
    /**
     * 접속 유저 IP 입력
     *
     * @method : updateUserIp
     * @date : 20230626
     * @author : kjyoo
     * @param : loginId
 	 * 사용자 로그인 ID 
 	 * @param : password
 	 * 사용자 로그인 Password
 	 * 
     * @return 로그인 사용자 정보
     * @throws 예외가 있다면 예외 클래스 및 설명
     *     << Modification History >>
     *     
     *     Date        Author       Description
     *     ---------   --------     --------------------
     *     20230626     kjyoo       신규생성
     */
    public MemberInfoDto updateUserIp(final String loginId, final String usrIp) {
    	
    	MemberInfoDto member = memberMapper.findByLoginId(loginId);
    	
    	member.setUsrIp(usrIp);
    	
        memberMapper.updateUserIp(member);
        return member;
    }
    
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
    public int selectLoginAdminNum() {
        return memberMapper.selectLoginAdminNum();
    }
    
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
    public int selectLoginUserNum() {
        return memberMapper.selectLoginUserNum();
    }
    
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
    public int countLoginUserConn() {
        return memberMapper.countLoginUserConn();
    }
    
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
    public List<UserConnInfoDto> selectLoginUserConn(final String loginId) {
        return memberMapper.selectLoginUserConn(loginId);
    }
    
    /**
     * 유저 Login Connection정보 Insert
     *
     * @method : insertLoginUserConn
     * @date : 20230626
     * @author : kjyoo
 	 * 
     * @return 
     * @throws 예외가 있다면 예외 클래스 및 설명
     *     << Modification History >>
     *     
     *     Date        Author       Description
     *     ---------   --------     --------------------
     *     20230626     kjyoo       신규생성
     */
    public void insertLoginUserConn(HashMap<String, Object> map) {
    	memberMapper.insertLoginUserConn(map);
	}
    
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
    public void insertUserLogoutData(UserConnInfoDto param) {
    	memberMapper.insertUserLogoutData(param);
    }
}