package net.scit.vo;

public class TodoVO {
	private String t_num;
	private String t_content;
	private String usrid;
	private String teamnum;
	private int t_state;
	private int t_priority;
	private String t_regdate;

	public TodoVO() {
		super();
	}

	public TodoVO(String t_content, String usrid, String teamnum, int t_state) {
		super();
		this.t_content = t_content;
		this.usrid = usrid;
		this.teamnum = teamnum;
		this.t_state = t_state;
	}

	public String getT_num() {
		return t_num;
	}

	public void setT_num(String t_num) {
		this.t_num = t_num;
	}

	public String getT_content() {
		return t_content;
	}

	public void setT_content(String t_content) {
		this.t_content = t_content;
	}

	public String getUsrid() {
		return usrid;
	}

	public void setUsrid(String usrid) {
		this.usrid = usrid;
	}

	public String getTeamnum() {
		return teamnum;
	}

	public void setTeamnum(String teamnum) {
		this.teamnum = teamnum;
	}

	public int getT_state() {
		return t_state;
	}

	public void setT_state(int t_state) {
		this.t_state = t_state;
	}

	public int getT_priority() {
		return t_priority;
	}

	public void setT_priority(int t_priority) {
		this.t_priority = t_priority;
	}

	public String getT_regdate() {
		return t_regdate;
	}

	public void setT_regdate(String t_regdate) {
		this.t_regdate = t_regdate;
	}

	@Override
	public String toString() {
		return "TodoVO [t_num=" + t_num + ", t_content=" + t_content + ", usrid=" + usrid + ", teamnum=" + teamnum
				+ ", t_state=" + t_state + ", t_priority=" + t_priority + ", t_regdate=" + t_regdate + "]";
	}

}
