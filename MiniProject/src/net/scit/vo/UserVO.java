package net.scit.vo;

public class UserVO {
	private String usrid;
	private String pw;
	private String usrname;
	private String teamnum;
	
	public UserVO() {}
	
	public UserVO(String usrid, String pw, String usrname, String teamnum) {
		super();
		this.usrid = usrid;
		this.pw = pw;
		this.usrname = usrname;
		this.teamnum = teamnum;
	}

	public String getUsrid() {
		return usrid;
	}

	public void setUsrid(String usrid) {
		this.usrid = usrid;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getUsrname() {
		return usrname;
	}

	public void setUsrname(String usrname) {
		this.usrname = usrname;
	}

	public String getTeamnum() {
		return teamnum;
	}

	public void setTeamnum(String teamnum) {
		this.teamnum = teamnum;
	}
	
	@Override
	public String toString() {
		return "usrid=" + usrid + ", pw=" + pw + ", usrname=" + usrname + ", teamnum=" + teamnum;
	}
	
	
	
	
}
