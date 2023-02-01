package com.yedam.project;

public class BoardVO {
	int postNum;
	String postTitle;
	String postContent;
	String Writer;
	String postDate;

	public int getPostNum() {
		return postNum;
	}

	public int getPostNum(int num) {
		return postNum;
	}

	public void setPostNum(int postNum) {
		this.postNum = postNum;
	}

	public String getPostTitle() {
		return postTitle;
	}

	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}

	public String getPostContent() {
		return postContent;
	}

	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}

	public String getWriter() {
		return Writer;
	}

	public void setWriter(String writer) {
		Writer = writer;
	}

	public String getPostDate() {
		return postDate;
	}

	public void setPostDate(String postDate) {
		this.postDate = postDate;
	}

	@Override
	public String toString() {
		return String.format(" ⚝ 글번호(%s)\n ⚝ 작성일자: [%s]\n ⚝ 작성자: %s 님\n ⚝ 글제목: %s\n ⚝ 글내용: %s", postNum, postDate,
				Writer, postTitle, postContent);
	}

}
