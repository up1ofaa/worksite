package util;


import java.util.*;



public class MappedRecordASRVS extends MappedRecordASRV{
	public MappedRecordASRVS(){
		super();
		attrMap = new HashMap<String, Object>();
		tmpMap = new HashMap<String, Object>();
		attrMapList = new ArrayList<Map<String,Object>>();
	}

	public void copy(Map<String, Object> inputMap) {
		tmpMap = inputMap; 
	}
	
	@Override
	public byte[] getAppHeader() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setAppHeader(byte[] abyte0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setUserRecord(byte[] abyte0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isOccField(String s) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int queryMaxLoopCnt(String s) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String get(String s) {
		if(this.getAttribute(s) == null){
			return "";
		}else{
			return (String)this.getAttribute(s);
		}
	}
	
	@Override
	public Map<String, Object> getObj(String s){
		return (Map<String, Object>)this.getAttribute(s);
	}
	
	@Override
	public List<Map<String, Object>> getList(String s){
		return (List<Map<String, Object>>)this.getAttribute(s);
	}
	
	@Override
	public String getServiceName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] getDefRecord() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] getRecord() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] getBocRecord() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] getCommRecord() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setCommRecord(byte[] abyte0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public byte[] getUserRecord() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTran() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setTran(String s) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getOccursCount(String s) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public byte[] getRawBytes(String s) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] getRawBytes(String s, int i) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isUserRecordContain() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setUserRecordContain(boolean flag) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object[] getNotFoundFields() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString(boolean flag) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRawString(String s, int i) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRawString(String s) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean queryToPreserve(String s, int i) {
		return false;
	}
	
	@Override
	public void put(String s, String s1) {
		this.setAttribute(s, s1);
	}
	
	@Override
	public void put(String s, byte[] abyte0) {
		this.setAttribute(s, abyte0);
	}


	@Override
	public void put(String s, MappedRecordASRV mr) {
		this.setAttribute(s, mr);
	}

	@Override
	public void put(String s, Map<String, Object> rsMsg) {
		attrMap.put(s, rsMsg);
	}
	
	@Override
	public void put(String s, List<Map<String, Object>> rsMsg) {
		attrMap.put(s, rsMsg);
	}
	

	@Override
	public void setDefRecord(byte[] abyte0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setBocRecord(byte[] abyte0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setRecord(byte[] abyte0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setReturnCode(String s) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getReturnCode() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getMsgLength() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTranFlag() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUserID() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTestOrReal() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getMsgID() {
		// TODO Auto-generated method stub
		return null;
	}
}