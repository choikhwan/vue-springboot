package com.epas.example.board.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.epas.example.board.dto.BoardDto;

@Mapper
public interface BoardMapper {
	
	public List<BoardDto> boardList(HashMap<String, Object> map);
	
	public BoardDto getBoard(HashMap<String, Object> map);
	
	void creatBoard(BoardDto params);
	
	void updateBoard(BoardDto params);
	
	void deleteBoard(BoardDto params);
	
	public long genMaxIdx();
	
}
