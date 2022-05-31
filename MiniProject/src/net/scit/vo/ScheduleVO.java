package net.scit.vo;

public class ScheduleVO {
	private String s_num;
	private String s_content;
	private String usrid;
	private String startdate;
	private String enddate;
	private String teamnum;

	public ScheduleVO() {
		super();
	}

	public ScheduleVO(String s_content, String usrid, String startdate, String enddate, String teamnum) {
		super();
		this.s_content = s_content;
		this.usrid = usrid;
		this.startdate = startdate;
		this.enddate = enddate;
		this.teamnum = teamnum;
	}

	public String getS_num() {
		return s_num;
	}

	public void setS_num(String s_num) {
		this.s_num = s_num;
	}

	public String getS_content() {
		return s_content;
	}

	public void setS_content(String s_content) {
		this.s_content = s_content;
	}

	public String getUsrid() {
		return usrid;
	}

	public void setUsrid(String usrid) {
		this.usrid = usrid;
	}

	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public String getEnddate() {
		return enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

	public String getTeamnum() {
		return teamnum;
	}

	public void setTeamnum(String teamnum) {
		this.teamnum = teamnum;
	}

	@Override
	public String toString() {
		return "ScheduleVO [s_num=" + s_num + ", s_content=" + s_content + ", usrid=" + usrid + ", startdate="
				+ startdate + ", enddate=" + enddate + ", teamnum=" + teamnum + "]";
	}

}
