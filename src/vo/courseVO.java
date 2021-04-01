package vo;

public class courseVO {
	
	private String course_id;
	private String title;
	private int	c_number;
	private String professor_id;
	private int course_fees;
	public String getCourse_id() {
		return course_id;
	}
	public void setCourse_id(String course_id) {
		this.course_id = course_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getC_number() {
		return c_number;
	}
	public void setC_number(int c_number) {
		this.c_number = c_number;
	}
	public String getProfessor_id() {
		return professor_id;
	}
	public void setProfessor_id(String professor_id) {
		this.professor_id = professor_id;
	}
	public int getCourse_fees() {
		return course_fees;
	}
	public void setCourse_fees(int course_fees) {
		this.course_fees = course_fees;
	}
	
	
	

}


/* 테이블 생성
   CREATE TABLE COURSE(
 	COURSE_ID VARCHAR2(10) NOT NULL,
 	TITLE VARCHAR2(30) NOT NULL,
 	PROFESSOR_ID VARCHAR2(10),
 	C_NUMBER NUMBER(5),
 	COURSE_FEE NUMBER(13)
 	);
 
 INSERT INTO COURSE(COURSE_ID, TITLE, C_NUMBER, PROFESSOR_ID, COURSE_FEE) 
 VALUES('L1012', '웹디자인' ,2 , null, 20000);
 
 INSERT INTO COURSE(COURSE_ID, TITLE, C_NUMBER, PROFESSOR_ID, COURSE_FEE) 
 VALUES('L1013', 'SQL' ,3 , 'P12', 30000);
 
  INSERT INTO COURSE(COURSE_ID, TITLE, C_NUMBER, PROFESSOR_ID, COURSE_FEE) 
 VALUES('L1042', 'Delphi' ,3 , 'P13', 50000);
 
  INSERT INTO COURSE(COURSE_ID, TITLE, C_NUMBER, PROFESSOR_ID, COURSE_FEE) 
 VALUES('L1052', '전자상거래' ,3 , 'P22', 30000);
 
  INSERT INTO COURSE(COURSE_ID, TITLE, C_NUMBER, PROFESSOR_ID, COURSE_FEE) 
 VALUES('L2031', '게임이론' ,3 , 'P23', 50000);
 
   INSERT INTO COURSE(COURSE_ID, TITLE, C_NUMBER, PROFESSOR_ID, COURSE_FEE) 
 VALUES('L111', 'Servlet' ,2 , 'P21', 20000);
 
   INSERT INTO COURSE(COURSE_ID, TITLE, C_NUMBER, PROFESSOR_ID, COURSE_FEE) 
 VALUES('L112', 'SQL' ,3 , 'P12', 30000);
 
 */