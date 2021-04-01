/*
================================================================================
프로그램명 : owpac01_i01.java
내          용 : 일정관리 입력/수정 (삼성)
--------------------------------------------------------------------------------
작  성  자 : 김동운(2006.08.03)
--------------------------------------------------------------------------------
수  정  자 : 
내      용 : 
관련  근거 : 
================================================================================
*/
package ctl;

//공통 IMPORT
import java.sql.Connection;

//프로그램 필요 IMPORT 
import dao.owpac01_s01_dao;
import util.InteractionLocal;
import util.MappedRecordASRV;

public class owpac01_s01 implements InteractionLocal { 

	public owpac01_s01() {
	}

	public MappedRecordASRV execute(MappedRecordASRV inRec, MappedRecordASRV outRec) throws Exception {

		// 변수 선언 시작  ================================================================================/
		//CommonBean comm	= null;
		Connection conn	= null;
		owpac01_s01_dao  owpac01_s01_dao = null;
		// 변수 선언 끝 ==================================================================================/

		try {

			// 공통 설정 ================================================================================/
			//comm = new CommonBean();
			//comm.setSYS_STAF_ID(CommUtil.trim(inRec.get("SYS_STAF_ID"),""));	 //사용자 ID 설정
			//comm.setSYS_CLASS_NAME(this.getClass().getName());				//프로그램 클래스명 설정
			//comm.setLogger(LoggingService.getLogger());
			// 공통 설정 ================================================================================/

			// 프로그램 로직 처리 =========================================================================/
			//conn = DBhandler.getConnection(DBhandler.BASE_POOL);
			System.out.println("owpac01_s01 입니다.1"+inRec.get("pgm_id"));
			System.out.println("owpac01_s01 입니다.2"+inRec.toString());
			outRec.put("result", "result입니다");
			owpac01_s01_dao = new owpac01_s01_dao();
			outRec = owpac01_s01_dao.execute(conn, inRec, outRec);
			// 프로그램 로직 처리 =========================================================================/

		
		} catch (Exception e) {

			// 오류 처리 ================================================================================/
			//comm.setErrorMsg(e);
			throw e;
			// 오류 처리 ================================================================================/

		} finally {

			// 객체 CLOSE 및 리턴 처리 ====================================================================/
			//DBhandler.close(conn);
			//comm.writeLog();						//로그를 출력한다.
			//outRec = comm.setReturnInfo(outRec);	//리턴 정보 설정
			//LoggingService.restoreLogger(comm.getLogger());
			// 객체 CLOSE 및 리턴 처리 ====================================================================/

		}

		return outRec;
	}

}

