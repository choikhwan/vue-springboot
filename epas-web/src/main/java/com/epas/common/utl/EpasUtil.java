package com.epas.common.utl;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.epas.common.service.UserNotifyMsgService;
import com.epas.login.dto.MemberInfoDto;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.rolling.RollingFileAppender;
import ch.qos.logback.core.rolling.TimeBasedRollingPolicy;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
* UserNotifyMsgInfo Create
*
* @since 20230725
* @author kjyoo
* @see <pre>
*  Class Name : EpasUtil.java
*  Description :
*
*  << Modification History >>
*
 *  Date              Author             Description
*  ----------        -----------        ----------------------
*  20230725           kjyoo              initial
*
© Hitachi High-Tech Corporation.  2023. All rights reserved.
*  </pre>
*/
@Slf4j
@Controller
@RequiredArgsConstructor
public class EpasUtil {

	@Autowired
	private UserNotifyMsgService userNotifyMsgService;

	private String OUTPUTPATH = "";	// batch log file save path
	private int ROTATIONDAYS = 90;	// batch log file store days

	public void sendMessage(String msgNo, String funcNm, String remark, boolean batchYn) {
		insertUserNotifyMsgInfo(msgNo, funcNm, remark, batchYn, "", "", "", "");
	}

	public void sendMessage(String msgNo, String funcNm, String remark, boolean batchYn, String param0) {
		insertUserNotifyMsgInfo(msgNo, funcNm, remark, batchYn, param0, "", "", "");
	}

	public void sendMessage(String msgNo, String funcNm, String remark, boolean batchYn, String param0, String param1) {
		insertUserNotifyMsgInfo(msgNo, funcNm, remark, batchYn, param0, param1, "", "");
	}

	public void sendMessage(String msgNo, String funcNm, String remark, boolean batchYn, String param0, String param1, String param2) {
		insertUserNotifyMsgInfo(msgNo, funcNm, remark, batchYn, param0, param1, param2, "");
	}

	public void sendMessage(String msgNo, String funcNm, String remark, boolean batchYn, String param0, String param1, String param2, String param3) {
		insertUserNotifyMsgInfo(msgNo, funcNm, remark, batchYn, param0, param1, param2, param3);
	}

	public void insertUserNotifyMsgInfo(String msgNo, String funcNm, String remark, boolean batchYn, String param0, String param1, String param2, String param3) {

		if(remark != null && remark != "") {
			remark = " " + remark;
		}

		// batch 에서 호출한 알림이 아니면 Log_YYYYMMDD.log 파일에 알림 로그를 저장한다.
		// /epas-web/src/main/resources/logback.xml 파일 <logger name="com.epas.common.utl.EpasUtil" ...> 항목 참조
		if(!batchYn)
			writeLogFile(msgNo, funcNm, remark, param0, param1, param2, param3);

		HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("msgNo", msgNo);
        map.put("msgfN", funcNm);
        map.put("msgRemark", remark);
        map.put("param0", param0);
        map.put("param1", param1);
        map.put("param2", param2);
        map.put("param3", param3);

        userNotifyMsgService.insertUserNotifyMsgInfo(map);
	}

	/**
	* 알림 메시지 Log_YYYYMMDD.log 파일에 저장
	* /epas-web/src/main/resources/logback.xml 파일
	* <logger name="com.epas.common.utl.EpasUtil" ...> 항목 참조
	*
	* @method : writeLogFile
	* @date : 2023.09.18
	* @author : yangkw
	* @param : String msgNo, String funcNm, String remark, String param0, String param1, String param2, String param3
	* @return
	* @throws
	*     << Modification History >>
	*
	*     Date        Author       Description
	*     ---------   --------     --------------------
	*     2023.09.18  yangkw        initial
	*/
	public void writeLogFile(String msgNo, String funcNm, String remark, String param0, String param1, String param2, String param3) {
    	String msgTypeSep = ""; //메세지종류 구분자 , I/W/E
    	String msgType = ""; //메세지종류 INFORMATION, WARNING, ERROR, ETC
    	String msgNoLog = "";

    	if(msgNo != null && !"".equals(msgNo)) {

    		// dynamic logback setting
    		setLogbackConfig();

    		// 현재 로그인한 id 조회
    		String loginId = "";
    		HttpSession session = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest().getSession();
    		if(session != null) {
    			MemberInfoDto vo = (MemberInfoDto)session.getAttribute("loginMember");
    			loginId = vo != null ? vo.getUsrId() : "";
    		}

	    	//메세지종류, INFORMATION, WARNING, ERROR, ETC
	    	msgTypeSep = msgNo.substring(0,1);
	    	if("I".equals(msgTypeSep)) msgType = "INFORMATION";
	    	else if("W".equals(msgTypeSep)) msgType = "WARNING";
	    	else if("E".equals(msgTypeSep)) msgType = "ERROR";
	    	else msgType = "ETC";

	    	// 로그 파일에 표시될 메시지 no
	    	msgNoLog = msgNo+"-EP"+funcNm;

	    	// 표시할 로그 메시지 조회
    		HashMap<String, Object> map = new HashMap<String, Object>();
	        map.put("msgNo", msgNo);
	    	String msgContent = userNotifyMsgService.selectMsgContentByMsgNo(map);

	    	if(msgContent != null) {
		    	msgContent = msgContent.replaceAll("param0", param0);
		    	msgContent = msgContent.replaceAll("param1", param1);
		    	msgContent = msgContent.replaceAll("param2", param2);
		    	msgContent = msgContent.replaceAll("param3", param3);

		    	log.info("["+loginId+"]"+logItem(funcNm, "")+"-"+logItem(msgType, "")+" "+logItem(msgNoLog, "")+" : "+msgContent+"\n"+logItem(param0 ,"parameter0")+logItem(param1 ,"parameter1")+logItem(param2 ,"parameter2")+logItem(param3 ,"parameter3"));
	    	}
	    }
	}

	/**
	* 로그 항목 표시 문자열
	*
	* @method : logItem
	* @date : 2023.09.18
	* @author : yangkw
	* @param : String
	* @return String
	* @throws
	*     << Modification History >>
	*
	*     Date        Author       Description
	*     ---------   --------     --------------------
	*     2023.09.18  yangkw        initial
	*/
	public String logItem(String diplayItem, String parameterItemName) {
		String retStr = "";
		if(diplayItem!= null && !"".equals(diplayItem)) {
			String parameterItem = !"".equals(parameterItemName) ? parameterItemName+"=" : "";
			retStr = "["+parameterItem+diplayItem+"]";
		}
		return retStr;
	}


	/**
	* Please fill out the method summary.
	*
	* @method : replaceXssRemoveSqlInjection
	* @date : 2023. 10. 5.
	* @author : gohkh
	* @param : value
	* 인젝션 문자열 제거를 원하는 문자열
	* @return 인젝션 문자열 제거
	* @throws 예외가 있다면 예외 클래스 및 설명
	*     << Modification History >>
	*
	*     Date        Author       Description
	*     ---------   --------     --------------------
	*     2023. 10. 5.     gohkh      initial
	*/
	public static String replaceXssRemoveSqlInjection(String value) {
		String[] excludeTxtArr = { "UPDATED_" };

		if(value!= null && !"".equals(value)) {
			for (String excludeTxt : excludeTxtArr) {
				if (value.toUpperCase().indexOf(excludeTxt) == 0) {
					return value;
				}
			}
	        value = value.replaceAll("<", "").replaceAll(">", "");
	        value = value.replaceAll("\\(", "").replaceAll("\\)", "");
	        value = value.replaceAll("'", "");
	        value = value.replaceAll("eval\\((.*)\\)", "");
	        value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']","");
	        value = value.replaceAll("script","");
	        value = value.replaceAll("/((\\%3D)|(=))[^\\n]*((\\%27)|(\')|(\\-\\-)|(\\%3B)|(;))/i","");
	        value = value.replaceAll("/((\\%27)|(\'))union/ix","");
	        value = value.replaceAll("/\\w*((\\%27)|(\\'))((\\%6F)|o|(\\%4F))((\\%72)|r|(\\%52))/ix","");
	        value = value.replaceAll("insert|update|delete|having|drop|(\'|%27).(and|or).(\'|%27)|(\'|%27).%7C{0,2}|%7C{2}","");
	        value = value.replaceAll("/((\\%3C)|<)((\\%69)|i|(\\%49))((\\%6D)|m|(\\%4D))((\\%67)|g|(\\%47))[^\n]+((\\%3E)|>)/I","");
		}
        return value;
    }

	/**
	* Please fill out the method summary.
	*
	* @method : replaceXssAnsSqlInjection
	* @date : 2023. 10. 5.
	* @author : gohkh
	* @param : value
	* 인젝션 문자 치환대상
	* @return 인젝션 문자열 치환
	* @throws 예외가 있다면 예외 클래스 및 설명
	*     << Modification History >>
	*
	*     Date        Author       Description
	*     ---------   --------     --------------------
	*     2023. 10. 5.     gohkh      initial
	*/
	public static String replaceXssAnsSqlInjection(String value) {
		if(value!= null && !"".equals(value)) {
	        value = value.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
	        value = value.replaceAll("\\(", "&#40;").replaceAll("\\)", "&#41;");
	        value = value.replaceAll("'", "&#39;");
	        value = value.replaceAll("eval\\((.*)\\)", "");
	        value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']","\"\"");
	        value = value.replaceAll("script","");
	        value = value.replaceAll("/((\\%3D)|(=))[^\\n]*((\\%27)|(\')|(\\-\\-)|(\\%3B)|(;))/i","");
	        value = value.replaceAll("/((\\%27)|(\'))union/ix","");
	        value = value.replaceAll("/\\w*((\\%27)|(\\'))((\\%6F)|o|(\\%4F))((\\%72)|r|(\\%52))/ix","");
	        value = value.replaceAll("insert|update|delete|having|drop|(\'|%27).(and|or).(\'|%27)|(\'|%27).%7C{0,2}|%7C{2}","");
	        value = value.replaceAll("/((\\%3C)|<)((\\%69)|i|(\\%49))((\\%6D)|m|(\\%4D))((\\%67)|g|(\\%47))[^\n]+((\\%3E)|>)/I","");
		}

        return value;
    }

	/**
	* Log_yyyymmdd.log 파일 저장 경로, 저장 일수 설정에 따른 logback 동적 설정 적용
	* /epas-web/src/main/resources/logback.xml 참조
	*
	* @method : setLogbackConfig
	* @date : 2023.10.12
	* @author : yangkw
	* @param :
	* @return
	* @throws
	*     << Modification History >>
	*
	*     Date        Author       Description
	*     ---------   --------     --------------------
	*     2023.10.12  yangkw        initial
	*/
	public <E> void setLogbackConfig(){

		LocalDate now = LocalDate.now();

		// get system setting value OUTPUTPATH
		HashMap<String, String> getSysParam =  new HashMap<String, String>();
		getSysParam.put("sysSetTyp"		, "SETTINGS");
		getSysParam.put("sysParentId"	, "OUTPUT");
		getSysParam.put("sysSetNm"		, "OUTPUTPATH");
		String getOUTPUTPATH = userNotifyMsgService.selectSystemValue(getSysParam);

		// get system setting value ROTATIONDAYS
		getSysParam.put("sysSetTyp"		, "SETTINGS");
		getSysParam.put("sysParentId"	, "ROTATION");
		getSysParam.put("sysSetNm"		, "ROTATIONDAYS");
		String strROTATIONDAYS = userNotifyMsgService.selectSystemValue(getSysParam);
		int getROTATIONDAYS = Integer.parseInt(strROTATIONDAYS);

		// BatchLog 파일 경로 값이 변경되었거나 저장일수가 변경되었다면 logback 재설정하고 start
		if(!OUTPUTPATH.equals(getOUTPUTPATH) || ROTATIONDAYS != getROTATIONDAYS) {

			OUTPUTPATH = getOUTPUTPATH;
			ROTATIONDAYS = getROTATIONDAYS;

			String yyyy = String.valueOf(now.getYear());
			String mm =  String.format("%02d", now.getMonthValue());

			String fileNamePattern = getOUTPUTPATH+"/"+yyyy+""+mm+"/Log_%d{yyyyMMdd}.log";

			// /epas-web/src/main/resources/logback.xml 참조 access context
			LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
			loggerContext.start();

			// BatchLog Logger 설정 접근
			Logger logger = loggerContext.getLogger("com.epas.common.utl.EpasUtil");

			// epas-batch RollingFileAppender 참조 access object
			@SuppressWarnings("unchecked")
			RollingFileAppender<E> rollingFileAppender = (RollingFileAppender<E>) logger.getAppender("epas");

			// TimeBasedRollingPolicy 설정
			TimeBasedRollingPolicy<E> timeBasedRollingPolicy = new TimeBasedRollingPolicy<E>();
			timeBasedRollingPolicy.setContext(loggerContext);
			timeBasedRollingPolicy.setFileNamePattern(fileNamePattern);
			timeBasedRollingPolicy.setMaxHistory(ROTATIONDAYS); // 지정된 일수 분량의 로그 파일은 유지가 되고 이전의 로그 파일은 삭제됨.
			timeBasedRollingPolicy.setCleanHistoryOnStart(true);
			timeBasedRollingPolicy.setParent(rollingFileAppender);
			timeBasedRollingPolicy.start();

			// epas-batch RollingFileAppender TimeBasedRollingPolicy 설정 세팅
			rollingFileAppender.setRollingPolicy(timeBasedRollingPolicy);
			rollingFileAppender.start();
		}
	}
}