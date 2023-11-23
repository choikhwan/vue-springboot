package com.epas.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;


/**
 * Com Code Controller Class
 *
 * @since 2023. 10. 17.
 * @author kimjy
 * @see <pre>
 *  Class Name : BinToTextController.java
 *  Description : BinToText Management
 *
 *  << Modification History >>
 *
 *  Date              Modifier           Description
 *  ----------        -----------        ----------------------
 *  2023.10.17        kimjy              initial
 *
 © Hitachi High-Tech Corporation.  2023. All rights reserved.
 *  </pre>
 */
@RestController
public class BinToTextController {

	/*
	 * @Autowired private BinToTextService binToTextService;
	 */

    @Autowired
    MessageSource messageSource;

    /**
    * bin to text 화면 호출
    *
    * @method : bin2txt
    * @date : 2023.10.17
    * @author : kimjy
    * @param : request
    * HttpServletRequest
    * @return ModelAndView
    * @throws
    *     << Modification History >>
    *    
    *     Date        Author       Description
    *     ---------   --------     --------------------
    *     2023.10.17  kimjy        initial
    */
	@GetMapping("/admin/bin2txt")
	public ModelAndView bin2txt(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("admin/bin2txt");
		return mav;
	}
}
