package jp.co.aforce.bean;

public class Members implements java.io.Serializable {
	private String memberNo;
	private String name;
	private int age;
	private int birthYear;
	private int birthMonth;
	private int birthDay;

	public String getMemberNo() {
		return memberNo;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public int getBirthYear() {
		return birthYear;
	}

	public int getBirthMonth() {
		return birthMonth;
	}

	public int getBirthDay() {
		return birthDay;
	}


	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setBirthYear(int birthYear) {
		this.birthYear = birthYear;
	}

	public void setBirthMonth(int birthMonth) {
		this.birthMonth = birthMonth;
	}

	public void setBirthDay(int birthDay) {
		this.birthDay =birthDay;
	}

}
