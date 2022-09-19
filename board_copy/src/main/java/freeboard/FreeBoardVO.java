package freeboard;

import java.util.Date;

public class FreeBoardVO {
	private int num;
	private String title;
	private String writer;
	private String content;
	private Date regdate;
	private int cnt;
	private int likenum;
	private String board_file;
	
	
	public FreeBoardVO() {
		
	}
	
	
	
	public FreeBoardVO(int num, String title, String writer, String content, Date regdate, int cnt, int likenum, String board_file) {
		super();
		this.num = num;
		this.title = title;
		this.writer = writer;
		this.content = content;
		this.regdate = regdate;
		this.cnt = cnt;
		this.likenum= likenum;
		this.board_file = board_file;
	}



	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
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



	public int getLikenum() {
		return likenum;
	}



	public void setLikenum(int likenum) {
		this.likenum = likenum;
	}
	
	
	public String getBoard_file() {
		return board_file;
	}



	public void setBoard_file(String board_file) {
		this.board_file = board_file;
	}



	@Override
	public String toString() {
		return "BoardVO [num=" + num + ", title=" + title + ", writer=" + writer + ", content=" + content + ", regdate="
				+ regdate + ", cnt=" + cnt + "]";
	}
}
