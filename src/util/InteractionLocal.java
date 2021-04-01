package util;

import util.MappedRecordASRV;

public interface InteractionLocal {
	 public abstract MappedRecordASRV execute(MappedRecordASRV inRec, MappedRecordASRV outRec)
		        throws Exception;
}
