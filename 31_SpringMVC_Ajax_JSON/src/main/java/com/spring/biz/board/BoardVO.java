package com.spring.biz.board;

import java.sql.Date;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = { "searchCondition", "searchKeyword", "uploadFile" })
// --> 위에 세개는 JSON 데이터로 안 뽑아내겠다는 선언!
public class BoardVO {
	private int seq;
	private String title;
	private String writer;
	private String content;
	private Date regdate;
	private int cnt;
	
	//검색조건 처리용
	//@JsonIgnore // @JsonIgnore : JSON 데이터 변경해서 제외(json 데이터로 만들 때 뽑아내지 않겠다)
	private String searchCondition;
	//@JsonIgnore
	private String searchKeyword;
	
	//파일업로드
	//@JsonIgnore
	private MultipartFile uploadFile;
	
	public BoardVO() {
		System.out.println(">> BoardVO() 객체생성");
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}


	@Override
	public String toString() {
		return "BoardVO [seq=" + seq + ", title=" + title + ", writer=" + writer + ", content=" + content + ", regdate="
				+ regdate + ", cnt=" + cnt + ", searchCondition=" + searchCondition + ", searchKeyword=" + searchKeyword
				+ ", uploadFile=" + uploadFile + "]";
	}

	//검색조건 처리용----------------
	public String getSearchCondition() {
		return searchCondition;
	}
	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}
	public String getSearchKeyword() {
		return searchKeyword;
	}
	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}

	// 파일업로드 ----------
	public MultipartFile getUploadFile() {
		return uploadFile;
	}
	public void setUploadFile(MultipartFile uploadFile) {
		this.uploadFile = uploadFile;
	}
	
	
}





