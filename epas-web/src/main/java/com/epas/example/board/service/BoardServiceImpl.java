package com.epas.example.board.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epas.example.board.dto.BoardDto;
import com.epas.example.board.mapper.BoardMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
	@Autowired
	private BoardMapper boardMapper;

    public List<BoardDto> boardList(HashMap<String, Object> map){
    	return boardMapper.boardList(map);
		
	}
	
	public BoardDto getBoard(HashMap<String, Object> map){

		BoardDto vo = boardMapper.getBoard(map);
		
		return vo;
	}
	
	public void creatBoard(BoardDto param) {
		boardMapper.creatBoard(param);
	}

	public void updateBoard(BoardDto param) {
		boardMapper.updateBoard(param);
	}

	public void deleteBoard(BoardDto param) {
		boardMapper.deleteBoard(param);
	}
	
    public long genMaxIdx() {
    	return boardMapper.genMaxIdx();
    }
}
