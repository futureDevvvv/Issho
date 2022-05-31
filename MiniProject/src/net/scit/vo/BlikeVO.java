package net.scit.vo;

public class BlikeVO {
	private String l_num;
	private String b_num;
	private String usrid;

	public BlikeVO() {
	}
	
	
	public BlikeVO(String b_num, String usrid) {
		this.b_num = b_num;
		this.usrid = usrid;
	}


	public String getL_num() {
		return l_num;
	}

	public void setL_num(String l_num) {
		this.l_num = l_num;
	}

	public String getB_num() {
		return b_num;
	}

	public void setB_num(String b_num) {
		this.b_num = b_num;
	}

	public String getUsrid() {
		return usrid;
	}

	public void setUsrid(String usrid) {
		this.usrid = usrid;
	}

	@Override
	public String toString() {
		return "BlikeVO [l_num=" + l_num + ", b_num=" + b_num + ", usrid=" + usrid + "]";
	}

}
