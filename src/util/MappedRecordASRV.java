package util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.resource.ResourceException;
import javax.resource.cci.Record;
import javax.servlet.http.HttpSession;






	
public abstract class MappedRecordASRV implements Record, NlogObject{
    public MappedRecordASRV()
    {
		attrMap = new HashMap<String, Object>();
		tmpMap = new HashMap<String, Object>();
        attachment = false;
        tranid = "CWBA";
    }

    public void setAttribute(String name, Object value)
    {
    	tmpMap.put(name, value);
    }

    public Object getAttribute(String name)
    {
        return tmpMap.get(name);
    }

    public void setHttpSession(HttpSession sess)
    {
        session = sess;
    }

    public HttpSession getHttpSession()
    {
        return session;
    }

    public String getClientInfo()
    {
        return clientInfo;
    }

    public void setClientInfo(String info)
    {
        clientInfo = info;
    }
    public void addAttachmentFile(String name, byte f[])
    {
        attachmentMap.put(name, f);
    }

    public void addAttachmentFileAll(HashMap fileMap)
    {
        attachmentMap = fileMap;
    }

    public void addDownloadFile(byte f[])
    {
        downloadFile = f;
    }

    public boolean isAttachment()
    {
        return attachment;
    }

    public void setAttachment(boolean attachment)
    {
        this.attachment = attachment;
    }

    public byte[] getAttachmentFile(String name)
    {
        return (byte[])attachmentMap.get(name);
    }

    public byte[] getDownloadFile()
    {
        return downloadFile;
    }

    public Iterator getAttachmentFileNames()
    {
        return attachmentMap.keySet().iterator();
    }
    
    public String[] getArrayString(String columnName)
    {
        String[] resultArray = columnName.split("^");
        return resultArray;
    }

    public int[] getArrayInt(String columnName)
    {
    	String[] tmpArray = columnName.split("^");
    	int resultArray[] = new int[tmpArray.length];
    	int i =0;
    	for(String tmp : tmpArray){
    		resultArray[i++] = Integer.parseInt(tmp);
    	}
        return resultArray;
    }

    public float[] getArrayFloat(String columnName)
    {
    	String[] tmpArray = columnName.split("^");
    	float resultArray[] = new float[tmpArray.length];
    	int i =0;
    	for(String tmp : tmpArray){
    		resultArray[i++] = Float.parseFloat(tmp);
    	}
        return resultArray;
    }

    public double[] getArrayDouble(String columnName)
    {
    	String[] tmpArray = columnName.split("^");
    	double resultArray[] = new double[tmpArray.length];
    	int i =0;
    	for(String tmp : tmpArray){
    		resultArray[i++] = Double.parseDouble(tmp);
    	}
        return resultArray;
    }

    public BigDecimal[] getArrayBigDecimal(String columnName)
    {
    	String[] tmpArray = columnName.split("^");
    	BigDecimal resultArray[] = new BigDecimal[tmpArray.length];
    	int i =0;
    	for(String tmp : tmpArray){
    		resultArray[i++] = new BigDecimal(tmp);
    	}
        return resultArray;
    }

    public boolean isRecord()
    {
        return true;
    }

    public Object clone()
        throws CloneNotSupportedException
    {
        return super.clone();
    }

    public String toString()
    {
        return toString(false);
    }


    public boolean equals(Object other)
    {
        if(other == null)
            return false;
        if(other instanceof MappedRecordASRV)
        {
            MappedRecordASRV rec = (MappedRecordASRV)other;
            return recordName.equals(rec.getRecordName());
        } else
        {
            return false;
        }
    }

    public byte[] parseUserAddress(String ipStr)
        throws ResourceException
    {
        StringTokenizer token = new StringTokenizer(ipStr, ".");
        byte ipHexBytes[] = new byte[4];
        Arrays.fill(ipHexBytes, (new Integer(64)).byteValue());
        if(token.countTokens() != 4)
            return ipHexBytes;
        int ipBit = 0;
        int i = 0;
        while(token.hasMoreTokens()) 
            try
            {
                ipBit = Integer.parseInt(token.nextToken());
                ipHexBytes[i++] = (new Integer(Integer.parseInt(Integer.toHexString(ipBit), 16))).byteValue();
            }
            catch(NumberFormatException ne)
            {
                return ipHexBytes;
            }
        return ipHexBytes;
    }

    public String getTranLog()
    {
        return toString(false);
    }


    public String getRecordName()
    {
        return recordName;
    }

    public String getRecordShortDescription()
    {
        return description;
    }

    public int hashCode()
    {
        return recordName.hashCode();
    }

    public void setRecordName(String name)
    {
        recordName = name;
    }

    public void setRecordShortDescription(String desc)
    {
        description = desc;
    }

    public void putArrayString(String columnName, String column[])
    {
        int len = column.length;
        for(int i = 0; i < len; i++)
            put(columnName, column[i], i);

    }

    public void putArrayInt(String columnName, int column[])
    {
        int len = column.length;
        for(int i = 0; i < len; i++)
            put(columnName, String.valueOf(column[i]), i);

    }

    public void putArrayFloat(String columnName, float column[])
    {
        int len = column.length;
        for(int i = 0; i < len; i++)
            put(columnName, String.valueOf(column[i]), i);

    }

    public void putArrayDouble(String columnName, double column[])
    {
        int len = column.length;
        for(int i = 0; i < len; i++)
            put(columnName, String.valueOf(column[i]), i);

    }

    public void putArrayBigDecimal(String columnName, BigDecimal column[])
    {
        int len = column.length;
        for(int i = 0; i < len; i++)
            put(columnName, String.valueOf(column[i]), i);

    }

    public int getInt(String columnName)
    {
        return Integer.parseInt(get(columnName).trim());
    }

    public int getInt(String columnName, int index)
    {
        return Integer.parseInt(get(columnName, index).trim());
    }

    public float getFloat(String columnName)
    {
        return Float.parseFloat(get(columnName).trim());
    }

    public float getFloat(String columnName, int index)
    {
        return Float.parseFloat(get(columnName, index).trim());
    }

    public long getLong(String columnName)
    {
        return Long.parseLong(get(columnName).trim());
    }

    public long getLong(String columnName, int index)
    {
        return Long.parseLong(get(columnName, index).trim());
    }

    public short getShort(String columnName)
    {
        return Short.parseShort(get(columnName).trim());
    }

    public short getShort(String columnName, int index)
    {
        return Short.parseShort(get(columnName, index).trim());
    }

    public double getDouble(String columnName)
    {
        return Double.parseDouble(get(columnName).trim());
    }

    public double getDouble(String columnName, int index)
    {
        return Double.parseDouble(get(columnName, index).trim());
    }

    public BigDecimal getBigDecimal(String columnName)
    {
        return new BigDecimal(get(columnName).trim());
    }

    public BigDecimal getBigDecimal(String columnName, int index)
    {
        return new BigDecimal(get(columnName, index).trim());
    }

    public void putInt(String columnName, int data)
    {
        put(columnName, String.valueOf(data));
    }

    public void putInt(String columnName, int data, int index)
    {
        put(columnName, String.valueOf(data), index);
    }

    public void putFloat(String columnName, float data)
    {
        put(columnName, String.valueOf(data));
    }

    public void putFloat(String columnName, float data, int index)
    {
        put(columnName, String.valueOf(data), index);
    }

    public void putLong(String columnName, long data)
    {
        put(columnName, String.valueOf(data));
    }

    public void putLong(String columnName, long data, int index)
    {
        put(columnName, String.valueOf(data), index);
    }

    public void putShort(String columnName, short data)
    {
        put(columnName, String.valueOf(data));
    }

    public void putShort(String columnName, short data, int index)
    {
        put(columnName, String.valueOf(data), index);
    }

    public void putDouble(String columnName, double data)
    {
        put(columnName, String.valueOf(data));
    }

    public void putDouble(String columnName, double data, int index)
    {
        put(columnName, String.valueOf(data), index);
    }

    public void putBigDecimal(String columnName, BigDecimal data)
    {
        put(columnName, data.toString());
    }

    public void putBigDecimal(String columnName, BigDecimal data, int index)
    {
        put(columnName, data.toString(), index);
    }

	public void addMapList(String dataId) {
		if(dataId.indexOf("dma_") > -1){
			attrMap.put(dataId, tmpMap);
		}else if(dataId.indexOf("dlt_") > -1){		
			ArrayList<Map<String,Object>> tmpList = attrMapList;
			if(attrMap.get(dataId) == null){
				attrMap.put(dataId, tmpList);
			}
			attrMapList = new ArrayList<Map<String,Object>>();
		}else{
			ArrayList<Map<String,Object>> tmpList = attrMapList;
			attrMap.put(dataId, tmpList);
			attrMapList = new ArrayList<Map<String,Object>>();
		}
	}
	
	public Map<String, Object> getAllMap() {
        Iterator<String> tmpMapIter = tmpMap.keySet().iterator();
        while( tmpMapIter.hasNext() ){
        	String tmpMapKey 	= tmpMapIter.next();
        	Object tmpVal 		= tmpMap.get(tmpMapKey);
        	attrMap.put(tmpMapKey, tmpVal);
        }
		return attrMap;
	}
	
    public abstract byte[] getAppHeader();

    public abstract void setAppHeader(byte abyte0[]);

    public abstract void setUserRecord(byte abyte0[]);

    public abstract boolean isOccField(String s);

    public abstract int queryMaxLoopCnt(String s);

    public abstract String get(String s);

	public String get(String s, int i) {
		HashMap<String, Object> tmpMaps = new HashMap<String, Object>();
		if(attrMapList.size() > 0){
			if(attrMapList.size() > i){
				tmpMaps = (HashMap<String, Object>) attrMapList.get(i);
				return (String)tmpMaps.get(s);
			}else{
				return (String) "";
			}
		}else{
			return (String) "";
		}
		//return (String)this.getAttribute(s);
	}
    
    public abstract Map<String, Object> getObj(String s);
    
    public abstract List<Map<String, Object>> getList(String s);
    
    public ArrayList<Map<String, Object>> getArr(String s){
    	return (ArrayList<Map<String, Object>>)this.getAttribute(s);
    };

    public abstract String getServiceName();

    public abstract byte[] getDefRecord();

    public abstract byte[] getRecord();

    public abstract byte[] getBocRecord();

    public abstract byte[] getCommRecord();

    public abstract void setCommRecord(byte abyte0[]);

    public abstract byte[] getUserRecord();

    public abstract String getTran();

    public abstract void setTran(String s);

    public abstract int getOccursCount(String s);

    public abstract byte[] getRawBytes(String s);

    public abstract byte[] getRawBytes(String s, int i);

    public abstract boolean isUserRecordContain();

    public abstract void setUserRecordContain(boolean flag);

    public abstract Object[] getNotFoundFields();

    public long getCreationTime()
    {
        return creationTime;
    }

    public abstract String toString(boolean flag);

    public abstract String getRawString(String s, int i);

    public abstract String getRawString(String s);

    public abstract boolean queryToPreserve(String s, int i);

    public abstract void put(String s, String s1);

    public abstract void put(String s, Map<String, Object> rsMsg);
    
    public abstract void put(String s, List<Map<String, Object>> rsMsg);

    public abstract void put(String s, byte abyte0[]);

    //public abstract void put(String s, String s1, int i);

    //public abstract void put(String s, byte abyte0[], int i);

	public void put(String s, String s1, int i) {
		if(attrMapList.size() < 1){
			attrMapList.add(0, new HashMap<String, Object>());
		}else{
			if((attrMapList.size()-1) < i){
				attrMapList.add(i, new HashMap<String, Object>());
			}
		}
		HashMap<String, Object> tmpMap = (HashMap<String, Object>) attrMapList.get(i);
		tmpMap.put(s, s1);
	}

	public void put(String s, byte[] abyte0, int i) {
		if(attrMapList.size() < 1){
			attrMapList.add(0, new HashMap<String, Object>());
		}else{
			if((attrMapList.size()-1) < i){
				attrMapList.add(i, new HashMap<String, Object>());
			}
		}
		HashMap<String, Object> tmpMap = (HashMap<String, Object>) attrMapList.get(i);
		tmpMap.put(s, abyte0);
		this.oldInt = i;
	}

	public void put(String s, ArrayList<Map<String, Object>> s1) {
		tmpMap.put(s, s1);
	}
    
    public abstract void put(String s, MappedRecordASRV mr);

    public abstract void setDefRecord(byte abyte0[]);

    public abstract void setBocRecord(byte abyte0[]);

    public abstract void setRecord(byte abyte0[]);

    public abstract void init();

    public abstract void setReturnCode(String s);

    public abstract String getReturnCode();

    public abstract int getMsgLength();

    public abstract String getDate();

    public abstract String getTranFlag();

    public abstract String getUserID();

    public abstract String getTestOrReal();

    public abstract String getMsgID();

    private String description;
    protected String recordName;
    protected String clientInfo;
    protected HashMap attachmentMap;
    protected byte downloadFile[];
    protected long creationTime;
    protected boolean attachment;
    protected int oldInt = 0;
    protected HttpSession session;
    protected Map<String, Object> putArrMap = new HashMap<String, Object>();
    protected Map<String, Object> attrMap = new HashMap<String, Object>();
    protected Map<String, Object> tmpMap = new HashMap<String, Object>();
    protected ArrayList<Map<String,Object>> attrMapList;
    protected String tranid;
}