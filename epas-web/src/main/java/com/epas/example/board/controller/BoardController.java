package com.epas.example.board.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import com.epas.example.board.dto.BoardDto;
import com.epas.example.board.service.BoardService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BoardController {

	@Autowired
    private BoardService boardService;	

    @Autowired
    MessageSource messageSource;	


	@GetMapping("/board/list")
	public ModelAndView boardList(Model model, HttpServletRequest request) {

//		HttpSession session = request.getSession();
//		MemberInfoDto vo = (MemberInfoDto)session.getAttribute("loginMember");

		HashMap<String, Object> map = new HashMap<String, Object>();		
		ModelAndView mav = new ModelAndView("jsonView");
		List<BoardDto> list = boardService.boardList(map);
        
		mav.addObject("data", list);
		return mav;
	}
	
	@GetMapping("/board/detail")
	public ModelAndView boardDetail(Model model, HttpServletRequest request) {

//		HttpSession session = request.getSession();
//		MemberInfoDto vo = (MemberInfoDto)session.getAttribute("loginMember");

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("idx", request.getParameter("idx"));
		ModelAndView mav = new ModelAndView("jsonView");
		BoardDto data = boardService.getBoard(map);
        
		mav.addObject("data", data);
		return mav;
	}
	
    @PostMapping("/board/deleteBoard")
    public ModelAndView deleteBoard(@RequestBody BoardDto params, HttpServletRequest request) throws Exception {

		ModelAndView mav = new ModelAndView("jsonView");
		
    	boardService.deleteBoard(params);
	  
		mav.addObject("data", params);
		
		return mav;
	}
    
    @PostMapping("/board/creatBoard")
    public ModelAndView creatBoard(@RequestBody BoardDto params, HttpServletRequest request) throws Exception {

		ModelAndView mav = new ModelAndView("jsonView");
		
		long newIdx = boardService.genMaxIdx();
		params.setIdx(newIdx);
		
    	boardService.creatBoard(params);
	  
		mav.addObject("data", params);
		return mav;
	}     
    
    @PostMapping("/board/updateBoard")
    public ModelAndView updateBoard(@RequestBody BoardDto params, HttpServletRequest request) throws Exception {

		ModelAndView mav = new ModelAndView("jsonView");
		
    	boardService.updateBoard(params);
	  
		mav.addObject("data", params);
		return mav;
	}        

}