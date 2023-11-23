package com.epas.login.controller;

import java.security.MessageDigest;

/**
* User Password Encrypt Class. 
* 
* @since 20230626
* @author kjyoo
* @see <pre>
*  Class Name : UserSha256.java
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
public class UserSha256 {
	/**
	* User Password Encrypt. 
	*
	* @method : encrypt
	* @date : 20230626
	* @author : kjyoo
	* @param : password
	* 화면에서 입력한 비밀번호 값
	* @return hexString값의 암포 
	* @throws 예외가 있다면 예외 클래스 및 설명
	*     << Modification History >>
	*     
	*     Date        Author       Description
	*     ---------   --------     --------------------
	*     20230626    kjyoo        initial
	*/
	public static String encrypt(String password) throws Exception {
		
		if(password == null) {
			return null;
		}
		
		MessageDigest md = null;
		md = MessageDigest.getInstance("SHA-256");
		
		md.update(password.getBytes());
		byte byteData[] = md.digest();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < byteData.length; i++) {
			sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
		}
		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < byteData.length; i++) {
			String hex = Integer.toHexString(0xff & byteData[i]);
			if (hex.length() == 1) {
				hexString.append('0');
			}
			hexString.append(hex);
		}
		return hexString.toString();
	}
}