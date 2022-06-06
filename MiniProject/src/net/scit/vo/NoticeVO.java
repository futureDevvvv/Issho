package net.scit.vo;

public class NoticeVO {
	private String n_num;
	private String n_title;
	private String n_content;
	private int n_hitcount;

	public NoticeVO() {
		super();
	}

	public NoticeVO(String n_title, String n_content, int n_hitcount) {
		super();
		this.n_title = n_title;
		this.n_content = n_content;
		this.n_hitcount = n_hitcount;
	}

	public String getN_num() {
		return n_num;
	}

	public void setN_num(String n_num) {
		this.n_num = n_num;
	}

	public String getN_title() {
		return n_title;
	}

	public void setN_title(String n_title) {
		this.n_title = n_title;
	}

	public String getN_content() {
		return n_content;
	}

	public void setN_content(String n_content) {
		this.n_content = n_content;
	}

	public int getN_hitcount() {
		return n_hitcount;
	}

	public void setN_hitcount(int n_hitcount) {
		this.n_hitcount = n_hitcount;
	}

	@Override
	public String toString() {
		System.out.println("글번호 = " + n_num + " , 제목 = " + n_title + " , 조회수 = "+ n_hitcount);
		return "내용 = " + n_content;
	}

}
