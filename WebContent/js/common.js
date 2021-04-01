/*
 * @date 
 * @memberOf com
 * @author 
 * @excemple 
 */
com.send= function ( object){
	var ob= new Object();
	ob=object;
	var ctlNm =ob.id;
	var inRec =ob.inRec;
	var funNm =ob.doneFunc;
	var baseDir =ob.baseDir;	
	var params="";
	params+="<%=baseDir%>/"+ctlNm;
	var recordUrl ="<%=baseDir%>/"+"Action?"+"name=ctl."+ctlNm;
	ajax =ajaxObject();
	
	ajax.open("post",recordUrl);
	ajax.send(JSON.stringify(inRec));		
	ajax.onreadystatechange = function() {
		 if(ajax.readyState == 4 && ajax.status == 200) {	
		
			 var str= JSON.parse(this.responseText);
			 var outRec=str.outRec;
			 window[funNm](outRec);
		 }//end-if
	 }//end ajax.onready		
}//end-function
