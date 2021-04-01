<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="org.json.simple.parser.JSONParser"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="org.json.simple.JSONArray"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<% 
	String baseDir=request.getContextPath();
	System.out.print("baseDir:"+baseDir+"\n");
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script>




window.onload=function DOC_LOAD(){
	USR_INIT();
	USR_VIEW_SELECT(1);
	//무한반복된다(무한로딩문제점)
	
	//아래 비동기방식으로 response객체를 받으면 jsp페이지 처음 로딩이후 유지되고
	//response값만 가져와서 활성화된다
	//doSelect();
}

	
function USR_MAIN(args){
	USR_INVALID(args);
	USR_CONFIRM(args);
	switch(args){
			case 'omvc01_s01':
					com_send('omvc01_s01', null, 'do_omvc01_s01');
				break;
			case 'omvc01_u01':
					var inRec	 = new Object(); //리스트와 변수를 모두 담는 Object생성	
					var list_cnt = document.crs.course_id.length;
					var list     = new Array(); //import필요없나봄
					
					for(var i=0; i<list_cnt; i++){
						var obj =new Object();  //import필요없나봄
						obj.course_id	= document.crs.course_id[i].value;
						obj.title		= document.crs.title[i].value;
						obj.c_number	= document.crs.c_number[i].value;
						obj.professor_id= document.crs.professor_id[i].value;
						obj.course_fee	= document.crs.course_fee[i].value;
						list[i]=obj;
					}
					
					inRec.list_cnt=list_cnt;
					inRec.list    =list;
					com_send('omvc01_u01', inRec, 'do_omvc01_u01');
				break;
			default :
				break;	
	}	
}	

function USR_INVALID(args){
	switch(args){
	case 'omvc01_s01':		
		return ;
		break;
	case 'omvc01_u01':
		return ;
		break;
	default :
		return ;
		break;	
	}	
}


function USR_CONFIRM(args){
	switch(args){
	case 'omvc01_s01':
		break;
	case 'omvc01_u01':
		break;
	default :
		return ;
		break;	
	}	
}
	


function com_send(ctlNm, inRec, funNm){
	
	var params="";
		params+="<%=baseDir%>/"+ctlNm;
		//alert(params);
		ajax =ajaxObject();
		ajax.open("post",params);
		ajax.send(JSON.stringify(inRec));	
		
		ajax.onreadystatechange = function() {
			 if(ajax.readyState == 4 && ajax.status == 200) {	
				 var outRec =JSON.parse(this.responseText);
				 window[funNm](outRec);
			 }//end-if
		 }//end ajax.onready		
}//end-function


function do_omvc01_s01(outRec){
	USR_INIT();
	USR_VIEW_SELECT(1);
	var cnt=outRec.JLIST_CNT;	
	var tbObj=document.getElementById('crsTb');
	for(var i=0; i <cnt; i++){
		var new_row=document.getElementById('crsTb').insertRow();			    		
		new_row.onclick= function(){
			var rowIdx=this.rowIndex;
			document.crs.ul_crsId.value =document.crs.course_id[rowIdx].value;		//tbObj.rows[rowIdx].cells[1].innerHTML;
			document.crs.ul_title.value =document.crs.title[rowIdx].value;			//tbObj.rows[rowIdx].cells[2].innerHTML;
			document.crs.ul_cNum.value  =document.crs.c_number[rowIdx].value;		//tbObj.rows[rowIdx].cells[3].innerHTML;
			document.crs.ul_psId.value  =document.crs.professor_id[rowIdx].value; 	//tbObj.rows[rowIdx].cells[4].innerHTML;
			document.crs.ul_crsFee.value=document.crs.course_fee[rowIdx].value;		//tbObj.rows[rowIdx].cells[5].innerHTML;			    			
		};
		var new_cell1= new_row.insertCell();
		var new_cell2= new_row.insertCell();
		var new_cell3= new_row.insertCell();
		var new_cell4= new_row.insertCell();
		var new_cell5= new_row.insertCell();
		var new_cell6= new_row.insertCell();
		new_cell1.innerHTML= (i+1);
		new_cell2.innerHTML= "<input type='text' name='course_id' 		readonly='readonly' value='"+outRec.JLIST[i].course_id		+"'>";
		new_cell3.innerHTML= "<input type='text' name='title'	  		readonly='readonly' value='"+outRec.JLIST[i].title			+"'>";
		new_cell4.innerHTML= "<input type='text' name='c_number'    						value='"+outRec.JLIST[i].c_number		+"'>";  
		new_cell5.innerHTML= "<input type='text' name='professor_id' 	readonly='readonly' value='"+outRec.JLIST[i].professor_id	+"'>"; 
		new_cell6.innerHTML= "<input type='text' name='course_fee'	 						value='"+outRec.JLIST[i].course_fee		+"'>";   	
	}// end-for	
}//end-function



function do_omvc01_u01(outRec){
	USR_INIT();
	USR_VIEW_SELECT(2);


		for(var i=0; i<outRec.rsList.length; i++){
			var new_row=document.getElementById('rsTb').insertRow();	
			var new_cell1= new_row.insertCell();
			var new_cell2= new_row.insertCell();
			var new_cell3= new_row.insertCell();
			var new_cell4= new_row.insertCell();
			var new_cell5= new_row.insertCell();
	
			new_cell1.innerHTML= (i+1);
			new_cell2.innerHTML= "<input type='text' name='course_id_1' 		readonly='readonly' value='"+outRec.rsList[i].course_id		+"'>";
			new_cell3.innerHTML= "<input type='text' name='title_1'	  		readonly='readonly' value='"+outRec.rsList[i].title			+"'>";
			new_cell4.innerHTML= "<input type='text' name='err_cd_1'    	    					value='"+outRec.rsList[i].err_cd		+"'>";  
			new_cell5.innerHTML= "<input type='text' name='err_mst_1'	    	readonly='readonly' value='"+outRec.rsList[i].err_msg		+"'>"; 		
		}// end-for	
}//end-function


	//ajax 객체 자동생성	
	function ajaxObject(){
		if(window.ActiveXObject){ // IE 6 이하
			xmlHttp	= new ActiveXObject("Microsoft.XMLHTTP");
		}else if(window.XMLHttpRequest){// 모질라, 사파리, IE7+ ...
			xmlHttp = new XMLHttpRequest();
		}
		return xmlHttp;
	}
	
	
	//테이블 클릭시 테이블 값 이동
	function tableOnclick(trObj){
		var tbody=trObj.parentNode;
		var trs=tbody.getElementsByTagName('tr');
		var tbObj= document.getElementById('crsTb');
		var rowIdx=0;
		for(var i=0;i<trs.length; i++){
			if(trs[i]==trObj){
				rowIdx=i;
			}			
		}
		document.crs.ul_crsId.value=tbObj.rows[rowIdx].cells[1].innerHTML;
		document.crs.ul_title.value=tbObj.rows[rowIdx].cells[2].innerHTML;
		document.crs.ul_cNum.value=tbObj.rows[rowIdx].cells[3].innerHTML;
		document.crs.ul_psId.value=tbObj.rows[rowIdx].cells[4].innerHTML;
		document.crs.ul_crsFee.value=tbObj.rows[rowIdx].cells[5].innerHTML; 
	}
	
	function USR_INIT(){
		document.crs.reset();
		var rowCount =document.getElementById('crsTb').getElementsByTagName('tr').length;
		for(var i=rowCount-1; i>1 ; i--){
			document.getElementById('crsTb').deleteRow(i);
		}
		
		var rowCount1 =document.getElementById('rsTb').getElementsByTagName('tr').length;
		for(var i=rowCount1-1; i>1 ; i--){
			document.getElementById('rsTb').deleteRow(i);
		}
		
		crs.ul_crsId.value='';
		crs.ul_title.value='';
		crs.ul_cNum.value='';
		crs.ul_psId.value='';
		crs.ul_crsFee.value='';
		//alert('초기화');
		}

	function USR_VIEW_SELECT(args){
		if(args==1){
			document.getElementById('hiddenDiv').style.display='none';
			document.getElementById('defaultDiv').style.display='inline';
		}else if(args==2){
			document.getElementById('hiddenDiv').style.display='inline';
			document.getElementById('defaultDiv').style.display='none';
		}
		
	}
	
</script>
</head>
<body>
<form name="crs">

<center>	
<div>
<input type="text"  name ="key_title" >
<input type="button" value="초기화" onclick="USR_INIT();">
<input type="button" value="비동기조회" onclick="USR_MAIN('omvc01_s01');">
<input type="button" value="전체수정(프로시져 호출)" onclick="USR_MAIN('omvc01_u01');">
</div>
<div id="defaultDiv">
	<table id ="crsTb" border="1" cellspacing="1">
		<tr>
			<th colspan="6">Course 테이블</th>
	</tr>
	
	<tr>
		<th>순번</th>
		<th>과목ID</th>
		<th>과목명</th>
		<th>과목점수</th>
		<th>교수ID</th>
		<th>수강료</th>
	</tr>
	</table>
</div>
<div id="hiddenDiv">
	<table id ="rsTb" border="1" cellspacing="1">
		<tr>
			<th colspan="6">Course 테이블</th>
	</tr>
	
	<tr>
		<th>순번</th>
		<th>과목ID</th>
		<th>과목명</th>
		<th>처리코드</th>
		<th>처리메시지</th>
	</tr>
	</table>
</div>

 	
	<table  cellspacing="1"border="1"  >
	<tr>
	<td>과목ID <input type="text" name ="ul_crsId" disabled="disabled" style="width:40px"></td>
	<td>과목명 <input type="text" name ="ul_title" disabled="disabled" style="width:40px"></td>
	<td>과목점수<input type="text" name="ul_cNum" style="width:40px"></td>
	<td>교수ID <input type="text" name ="ul_psId" disabled="disabled" style="width:40px"></td>
	<td>수강료<input type="text" name="ul_crsFee" style="width:40px" ></td>
	</tr>
	</table>

</center>
</form>

</body>
</html>