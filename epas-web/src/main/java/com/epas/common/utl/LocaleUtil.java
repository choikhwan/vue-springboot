package com.epas.common.utl;

import java.util.Locale;

import org.springframework.web.servlet.i18n.SessionLocaleResolver;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

/**
 * LocaleUtil Class
 *
 * @since 2023.06.19.
 * @author choih
 * @see <pre>
 *  Class Name : LocaleUtil.java
 *  Description : 현재 설정된  Locale을 확인 하는 클래스
 *
 *  << Modification History >>
 *  
 *  Date              Modifier           Description
 *  ----------        -----------        ----------------------
 *  2023.06.19.       choih              initial
 *
 © Hitachi High-Tech Corporation.  2023. All rights reserved.
 *  </pre>
 */
public class LocaleUtil {

	/**
	* 기본 로케일을 리턴한다. 기본은 영문이다
	*
	* @method : getDefaultLocale
	* @date : 2023.06.19
	* @author : choih
	* @return Locale
	* @throws 예외가 있다면 예외 클래스 및 설명
	*     << Modification History >>
	*     
	*     Date        Author       Description
	*     ---------   --------     --------------------
	*     2023.06.19  choih        initial
	*/
    public static Locale getDefaultLocale() {
        return Locale.US;
    }

	/**
	* HttpServletRequest 를 받아서 저장되어 있를 locale 값을 리턴한다. 없는 경우는 기본 로케일을 리턴한다. 
	*
	* @method : getLocale
	* @date : 2023.06.19
	* @author : choih
	* @param : request
	* HttpServletRequest
	* @return Locale
	* @throws 예외가 있다면 예외 클래스 및 설명
	*     << Modification History >>
	*     
	*     Date        Author       Description
	*     ---------   --------     --------------------
	*     2023.06.19  choih        initial
	*/
    public static Locale getLocale(HttpServletRequest request) {
        Locale locale = null;
        HttpSession session = request.getSession(); 
        locale = (Locale)session.getAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME);

        if (locale == null ) {
            locale = getDefaultLocale();
        }
        return locale;
    }


}