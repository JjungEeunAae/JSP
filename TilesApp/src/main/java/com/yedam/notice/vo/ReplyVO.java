package com.yedam.notice.vo;

import java.util.Date;

import lombok.Data;

@Data
public class ReplyVO {
    /*reply_id number primary key,
    notice_id number not null,
    reply_title varchar2(100) not null,
    reply_subject varchar2(1000) not null,
    reply_writer varchar2(100) not null,
    reply_date date default sysdate*/
	
	private int replyId;
	private int noticeId;
	private String replyTitle;
	private String replySubject;
	private String replyWriter;
	private Date replyDate;
}
