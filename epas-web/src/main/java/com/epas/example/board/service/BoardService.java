package com.epas.example.board.service;

import java.util.HashMap;
import java.util.List;

import com.epas.example.board.dto.BoardDto;


public interface BoardService {

    public List<BoardDto> boardList(HashMap<String, Object> map);
    
	public BoardDto getBoard(HashMap<String, Object> map);
    
    public void creatBoard(BoardDto param);

    public void updateBoard(BoardDto param);

    public void deleteBoard(BoardDto param);
    
    public long genMaxIdx();

}
	