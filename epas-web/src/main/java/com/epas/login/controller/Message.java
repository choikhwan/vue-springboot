package com.epas.login.controller;

import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

/**
* Message, View Location Class. 
* 
* @since 20230626
* @author kjyoo
* @see <pre>
*  Class Name : Message.java
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
@Data
@Generated
@NoArgsConstructor
public class Message {
	String message = "";
	String href = "";
	
	/**
    * Message 표시 및 화면 이동. 
    *
    * @method : Message
    * @date : 20230626
    * @author : kjyoo
    * @param : message
	* 화면에 표시될 메세지 내용
	* @param : href
	* 이동할 화면 경로
	* 
    * @return href 화면 호출
    * @throws 예외가 있다면 예외 클래스 및 설명
    *     << Modification History >>
    *     
    *     Date        Author       Description
    *     ---------   --------     --------------------
    *     20230626     kjyoo       신규생성
    */
	public Message(String message, String href) {
		this.message = message;
		this.href = href;
	}
}