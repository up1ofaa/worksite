package util;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Result {

	private Map<String, Object> resultMap = new HashMap<String, Object>();


	public final static String STATUS_SUCESS = "S";


	public final static String STATUS_SUCESS_MESSAGE = "�젙�긽 泥섎━�릺�뿀�뒿�땲�떎.";


	public final static String STATUS_ERROR = "E";


	public final static String STATUS_ERROR_DEFAULT_DETAIL_CODE = "E9999";


	public final static String STATUS_ERROR_MESSAGE = "泥섎━ �룄以� �삤瑜섍� 諛쒖깮�릺�뿀�뒿�땲�떎.";


	public final static String STATUS_WARNING = "W";

	
	public final static String STATUS_WARNING_MESSAGE = "泥섎━ �룄以� �삤瑜섍� 諛쒖깮�릺�뿀�뒿�땲�떎.";


	public final static String VIEW_DEFAULT = "wqView";


	public final static String MESSAGE_KEY = "rsMsg";


	public final static String RESULT_KEY_DEFAULT = "result";

	public void setData(String id, String data) {
		resultMap.put(id, data);
	}

	public void setData(String id, Map data) {
		resultMap.put(id, data);
	}

	public void setData(String id, List data) {
		resultMap.put(id, data);
	}
	
	public void setData(String id, MappedRecordASRV data) {
		resultMap.put(id, data);
	}

	public Map<String, Object> getResult() {
		if (resultMap.get(MESSAGE_KEY) == null) {
			setMsg(STATUS_SUCESS);
		}

		return resultMap;
	}

	/**
	 * 硫붿꽭吏� 泥섎━ - �긽�깭 湲곕낯 硫붿꽭吏� 泥섎━
	 * 
	 * @date 2017.12.02
	 * @memberOf
	 * @param {} status : 硫붿꽭吏� �긽�깭
	 * @returns void
	 * @author Inswave
	 * @example WqModel.setMsg( STATUS_SUCCESS );
	 */
	public void setMsg(String status) {
		String msg = "";
		if (status == STATUS_ERROR) {
			msg = STATUS_ERROR_MESSAGE;
		} else if (status == STATUS_SUCESS) {
			msg = STATUS_SUCESS_MESSAGE;
		} else if (status == STATUS_WARNING) {
			msg = STATUS_WARNING_MESSAGE;
		}
		setMsg(status, msg);
	}

	/**
	 * 硫붿꽭吏� 泥섎━
	 * 
	 * @date 2017.12.02
	 * @memberOf
	 * @param {} status : 硫붿꽭吏� �긽�깭, message : 硫붿꽭吏� �궡�슜
	 * @returns void
	 * @author Inswave
	 * @example WqModel.setMsg( STATUS_SUCCESS, "�젙�긽 泥섎━�릺�뿀�뒿�땲�떎." );
	 */
	public void setMsg(String status, String message) {
		setMsg(status, message, null);
	}

	/**
	 * 硫붿꽭吏� 泥섎━
	 * 
	 * @date 2017.12.02
	 * @memberOf
	 * @param {} status : 硫붿꽭吏� �긽�깭, message : 硫붿꽭吏� �궡�슜
	 * @returns void
	 * @author Inswave
	 * @example WqModel.setMsg(returnData, MsgUtil.STATUS_SUCCESS, "�젙�긽 泥섎━�릺�뿀�뒿�땲�떎." , exception 媛앹껜);
	 */
	public void setMsg(String status, String message, Exception ex) {

		Map<String, Object> result = new HashMap<String, Object>();

		if (status.equals(STATUS_SUCESS)) {
			result.put("statusCode", STATUS_SUCESS);
			result.put("message", getDefaultStatusMessage(message, STATUS_SUCESS_MESSAGE));
		} else if (status.equals(STATUS_WARNING)) {
			result.put("statusCode", STATUS_WARNING);
			result.put("message", getDefaultStatusMessage(message, STATUS_WARNING_MESSAGE));
		} else if (status.equals(STATUS_ERROR)) {
			setErrorMsg(STATUS_ERROR_DEFAULT_DETAIL_CODE, message, ex);
			return;
		}

		if (ex != null) {
			result.put("messageDetail", ex.getMessage());
		}

		resultMap.put(MESSAGE_KEY, result);
	}

	/**
	 * �삤瑜� 硫붿꽭吏� 泥섎━
	 * 
	 * @date 2017.12.02
	 * @memberOf
	 * @param {} errorCode : �삤瑜섏퐫�뱶, message : 硫붿꽭吏� �궡�슜
	 * @returns void
	 * @author Inswave
	 * @example WqModel.setErrorMsg("E0001", "�꽭�뀡�씠�뾾�뒿�땲�떎." );
	 */
	public void setErrorMsg(String errorCode, String message) {
		setErrorMsg(errorCode, message, null);
	}

	/**
	 * �삤瑜� 硫붿꽭吏� 泥섎━
	 * 
	 * @date 2017.12.02
	 * @memberOf
	 * @param {} errorCode : �삤瑜섏퐫�뱶, message : 硫붿꽭吏� �궡�슜
	 * @returns void
	 * @author Inswave
	 * @example WqModel.setErrorMsg("E0001", "�꽭�뀡�씠�뾾�뒿�땲�떎." , exception 媛앹껜);
	 */
	public void setErrorMsg(String errorCode, String message, Exception ex) {
		Map<String, Object> result = new HashMap<String, Object>();

		result.put("statusCode", STATUS_ERROR);
		result.put("errorCode", errorCode);
		result.put("message", getDefaultStatusMessage(message, STATUS_ERROR_MESSAGE));

		if (ex != null) {
			result.put("messageDetail", "" + ex.getMessage());
		}
		resultMap.put(MESSAGE_KEY, result);
	}

	public String getDefaultStatusMessage(String message, String defMessage) {
		if (message == null) {
			return defMessage;
		}
		return message;
	};
}