/*
================================================================================
���α׷��� : owpac01_i01.java
��          �� : �������� �Է�/���� (�Ｚ)
--------------------------------------------------------------------------------
��  ��  �� : �赿��(2006.08.03)
--------------------------------------------------------------------------------
��  ��  �� : 
��      �� : 
����  �ٰ� : 
================================================================================
*/
package ctl;

//���� IMPORT
import java.sql.Connection;

//���α׷� �ʿ� IMPORT 
import dao.owpac01_s01_dao;
import util.InteractionLocal;
import util.MappedRecordASRV;

public class owpac01_s01 implements InteractionLocal { 

	public owpac01_s01() {
	}

	public MappedRecordASRV execute(MappedRecordASRV inRec, MappedRecordASRV outRec) throws Exception {

		// ���� ���� ����  ================================================================================/
		//CommonBean comm	= null;
		Connection conn	= null;
		owpac01_s01_dao  owpac01_s01_dao = null;
		// ���� ���� �� ==================================================================================/

		try {

			// ���� ���� ================================================================================/
			//comm = new CommonBean();
			//comm.setSYS_STAF_ID(CommUtil.trim(inRec.get("SYS_STAF_ID"),""));	 //����� ID ����
			//comm.setSYS_CLASS_NAME(this.getClass().getName());				//���α׷� Ŭ������ ����
			//comm.setLogger(LoggingService.getLogger());
			// ���� ���� ================================================================================/

			// ���α׷� ���� ó�� =========================================================================/
			//conn = DBhandler.getConnection(DBhandler.BASE_POOL);
			System.out.println("owpac01_s01 �Դϴ�.1"+inRec.get("pgm_id"));
			System.out.println("owpac01_s01 �Դϴ�.2"+inRec.toString());
			outRec.put("result", "result�Դϴ�");
			owpac01_s01_dao = new owpac01_s01_dao();
			outRec = owpac01_s01_dao.execute(conn, inRec, outRec);
			// ���α׷� ���� ó�� =========================================================================/

		
		} catch (Exception e) {

			// ���� ó�� ================================================================================/
			//comm.setErrorMsg(e);
			throw e;
			// ���� ó�� ================================================================================/

		} finally {

			// ��ü CLOSE �� ���� ó�� ====================================================================/
			//DBhandler.close(conn);
			//comm.writeLog();						//�α׸� ����Ѵ�.
			//outRec = comm.setReturnInfo(outRec);	//���� ���� ����
			//LoggingService.restoreLogger(comm.getLogger());
			// ��ü CLOSE �� ���� ó�� ====================================================================/

		}

		return outRec;
	}

}

