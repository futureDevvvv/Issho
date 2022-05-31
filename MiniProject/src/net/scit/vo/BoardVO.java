package net.scit.vo;

public class BoardVO {
	private String b_num;
	private String b_title;
	private String b_content;
	private String usrid;
	private String b_regdate;
	private int b_hitcount;
	private String teamnum;

	public BoardVO() {
	}

	public BoardVO(String b_title, String b_content, String usrid, String teamnum) {
		this.b_title = b_title;
		this.b_content = b_content;
		this.usrid = usrid;
		this.teamnum = teamnum;
	}

	public String getB_num() {
		return b_num;
	}

	public void setB_num(String b_num) {
		this.b_num = b_num;
	}

	public String getB_title() {
		return b_title;
	}

	public void setB_title(String b_title) {
		this.b_title = b_title;
	}

	public String getB_content() {
		return b_content;
	}

	public void setB_content(String b_content) {
		this.b_content = b_content;
	}

	public String getUsrid() {
		return usrid;
	}

	public void setUsrid(String usrid) {
		this.usrid = usrid;
	}

	public String getB_regdate() {
		return b_regdate;
	}

	public void setB_regdate(String b_regdate) {
		this.b_regdate = b_regdate;
	}

	public int getB_hitcount() {
		return b_hitcount;
	}

	public void setB_hitcount(int b_hitcount) {
		this.b_hitcount = b_hitcount;
	}

	public String getTeamnum() {
		return teamnum;
	}

	public void setTeamnum(String teamnum) {
		this.teamnum = teamnum;
	}

	@Override
	public String toString() {
		return "BoardVO [b_num=" + b_num + ", b_title=" + b_title + ", b_content=" + b_content + ", usrid=" + usrid
				+ ", b_regdate=" + b_regdate + ", b_hitcount=" + b_hitcount + ", teamnum=" + teamnum + "]";
	}

}
