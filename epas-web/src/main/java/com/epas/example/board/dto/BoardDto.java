package com.epas.example.board.dto;

import lombok.Data;
import lombok.Generated;

@Data
@Generated
public class BoardDto {

	private Long idx;
    private String title;
    private String contents;
    private String author;
    private String createdAt;

}