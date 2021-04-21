package ex.sim.entity;

public class StudentCourse {
	private int id;
	private String sNum;
	private String cNum;
	
	public StudentCourse() {
	}

	public StudentCourse(String sNum, String cNum) {
		this.sNum = sNum;
		this.cNum = cNum;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getsNum() {
		return sNum;
	}

	public void setsNum(String sNum) {
		this.sNum = sNum;
	}

	public String getcNum() {
		return cNum;
	}

	public void setcNum(String cNum) {
		this.cNum = cNum;
	}
	

	
	

}
