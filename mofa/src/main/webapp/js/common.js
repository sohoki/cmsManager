AtenCommon = function(){};
AtenCommon.prototype.goLingPage = function(pageNo, formNm, submitUrl){
	$(":hidden[name=pageIndex]").val(pageNo);		
	$("form[name='"+formNm+"'").attr("action",submitUrl).submit();
}

AtenCommon.prototype.uniElementValue = function (elementLists, value){
	var elementArray = elementLists.split(",");
	for (var i =0; i < elementArray.length; i++){
	  if ($("#"+elementArray[i])  != undefined ) {
			$("#"+elementArray[i]).val(value);
		}
	}
}
//클래스 생성
var AtenCommon = new AtenCommon();

//우편번호 
function zipCode(){
	 window.open("/common/zipcode.jsp", "zip_code", "width=500, height=400, toolbar=no, menubar=no, scrollbars=no, resizable=auto" );	
}
//페이지 이동 
function view_Page(code, code1, code_value, action_page, frm_nm){
	document.getElementById("mode").value = code;	
	code.value =code_value;	
	frm_nm.action = action_page;
	frm_nm.submit();	
}
//페이지로 가기 
function listPage(code, code1){	
	$("form[name="+code+"").attr("action", code1).submit();
	return;
}
//페이징 스크립트;
function ajaxPaging(currentPageNo, firstPageNo, recordCountPerPage, firstPageNoOnPageList, lastPageNoOnPageList, totalPageCount, pageSize, pageScript){
    var pageHtml = "";
    pageHtml += "<div class=pagination>";
	 if (currentPageNo == firstPageNo ){
      pageHtml += "<a href='#' >&laquo;</a>";
	 }else {
      pageHtml += "<a href='#' onclick='"+pageScript+"("+ firstPageNo +")';return false; '>&laquo;</a>";
	 }
	 if (parseInt(currentPageNo) > parseInt(firstPageNo)){
      pageHtml += "<a href='#' onclick='"+pageScript+"("+ parseInt(parseInt(currentPageNo) -1)+");return false;'>&lt;</a>"
	 }else {
      pageHtml += "<a href='#' >&lt;</a>"
	 }
    for(var  i = firstPageNoOnPageList; i<= lastPageNoOnPageList; i++){
		 if (i == currentPageNo){
            pageHtml += "<a class=active>"+i+"</a>";
		 }else {
            pageHtml += "<a href='#' onclick='"+pageScript+"("+i+");return false; '>"+i+"</a>";
		 }
    }

	 if (parseInt(totalPageCount) > parseInt(pageSize) ){
        pageHtml += "<a href='#' onclick='"+pageScript+"("+ parseInt(parseInt(currentPageNo) + 1)+");return false;'>&gt;</a>"
	 }else {
        pageHtml += "<a href='#' onclick='"+pageScript+"("+ parseInt(parseInt(currentPageNo) + 1)+");return false;'>&gt;</a>"
	 }
    if (parseInt(totalPageCount) > parseInt(pageSize)  ){
      pageHtml += "<a href='#' onclick='"+pageScript+"("+ totalPageCount +");return false;'>&raquo;</a>";
	 }else{
      pageHtml += "<a href='#' >&raquo;</a>";
	 }	
    return pageHtml;
}
//need popup  hide
function fn_needHid(){
	if($('.needpopup').hasClass('opened')){
		$(".needpopup_remover").click();
     }
}	
function fn_linkPage(url, formNm){
	$("form[name="+formNm+"]").attr("action",url).submit();
}
function fn_UniLinkPage(pageId, pageNo, formNm, url) {
	$(":hidden[name="+pageId+"]").val(pageNo);				
	$("form[name="+formNm+"]").attr("action",url).submit();
}
function fn_telReg(){
	//값을 입력받을 때마다 '-'(하이픈)을 자동으로 넣고 싶으면 
	//( /(^02.{0}|^01.{1}|[0-9]{3})([0-9]+)([0-9]{4})/, "$1-$2-$3" );
}
function apiExecute(type, url, data, beforeSend, done_callback, fail_callback, always_callback) {
	return _apiExecute(type, url, data, beforeSend, done_callback, fail_callback, always_callback);
}
//특정 길이 대체 문자
function stringLength (str, strlength, replaceTxt){
	if (str.length < parseInt(strlength)){
		for (var i =0; i < (parseInt(strlength) - str.length); i++ ){
			str = replaceTxt + str;
		}
	}else {
		str = str;
	}
	return str;
}
//숫자 유효성 체크 후 숫자 이외 문자 삭제
	 
//숫자 유효성 체크 
function isNumeric(num, opt){
	  // 좌우 trim(공백제거)을 해준다.
	  num = String(num).replace(/^\s+|\s+$/g, "");
	  
	  opt = "1";
	  if(typeof opt == "undefined" || opt == "1"){
	    // 모든 10진수 (부호 선택, 자릿수구분기호 선택, 소수점 선택)
	    var regex = /^[+\-]?(([1-9][0-9]{0,2}(,[0-9]{3})*)|[0-9]+){1}(\.[0-9]+)?$/g;
	  }else if(opt == "2"){
	    // 부호 미사용, 자릿수구분기호 선택, 소수점 선택
	    var regex = /^(([1-9][0-9]{0,2}(,[0-9]{3})*)|[0-9]+){1}(\.[0-9]+)?$/g;
	  }else if(opt == "3"){
	    // 부호 미사용, 자릿수구분기호 미사용, 소수점 선택
	    var regex = /^[0-9]+(\.[0-9]+)?$/g;
	  }else{
	    // only 숫자만(부호 미사용, 자릿수구분기호 미사용, 소수점 미사용)
	    var regex = /^[0-9]$/g;
	  }
	 
	  if(regex.test(num) == true   ){
		num = num.replace(/,/g, "");
	    return isNaN(num) ? false : true;
	  }else{ 
		  return false; 
	  } 
}
//공백 문자열 제거
function fn_trim(txtInfo){
	return String(txtInfo).replace(/^\s+|\s+$/g, "");
}
// 숫자 이외 문자 삭제
function isStringRemove(txtInfo){
	return String(txtInfo).replace(/^\s+|\s+$/g, "").replace(/[^0-9]/g,'') ;
}
//오늘 날짜 알기 
function today_get(){
	var now = new Date();
    var today_day = fnLPAD(String(now.getDate()), "0", 2); //일자를 구함
    var today_month = fnLPAD(String((now.getMonth() + 1)), "0", 2); // 월(month)을 구함    
    var today_year = String(now.getFullYear()); //년(year)을 구함
    var today = today_year + today_month + today_day;
    
    return today;
}
function fn_allCheck(){
	if ($("#all_check").prop("checked")){
		$("input[type=checkbox]").prop("checked", true);
	}else{
		$("input[type=checkbox]").prop("checked", false);
	}
}
function dayConvert(day){
	if(day.length == 8){
		day = day.substring(0,4)+"-"+day.substring(4,6)+"-"+day.substring(6,8);
	}
	return day 
}
//숫자만 입력 
function only_num() {
    if (((event.keyCode < 48) || (event.keyCode > 57)) && (event.keyCode != 190)) event.returnValue = false;
}
function fn_emptyReplace(ckValue, replaceValue){
	return  (ckValue == "" || ckValue == undefined ) ? replaceValue : ckValue;
}
function _apiExecute(type, url, data, beforeSend, done_callback, fail_callback, always_callback) {
	var contentType = "application/x-www-form-urlencoded; charset=UTF-8";
	var jqXHR = $.ajax({
		type : type,
		contentType : contentType,
		url : url,
		data : data,
		beforeSend:function(jxFax, settings){
        	jxFax.setRequestHeader('AJAX', true);
        }
	}).done(done_callback).fail(fail_callback).always(always_callback);

	return jqXHR;
}

//패스워드 체크 구문
function chkPwd(str){
	 var reg_pwd = /^.*(?=.{10,20})(?=.*[0-9])(?=.*[a-zA-Z]).*$/;
	 if(!reg_pwd.test(str)){
	  return false;
	 }
	 return true;
}
function fnLPAD(val, set, cnt) {
    if (!set || !cnt || val.length >= cnt) {
        return val;
    }
    var max = (cnt - val.length) / set.length;
    for (var i = 0; i < max; i++) {
        val = set + val;
    }
    return val;
}
function any_empt_line_id(frm_nm, alert_message){
	 var form_nm = eval("document.getElementById('"+frm_nm+"')");
	 if (form_nm.value.length < 1)
	 {
		  alert(alert_message);
		  form_nm.focus();
		  return false;
	 }else{
         return true;
	 }
}
function fn_calValidate(strDay, EndDay , alert_message){
	if (parseInt( $("#"+strDay).val()) > parseInt( $("#"+EndDay).val()) ){
		  alert(alert_message);
		  $("#"+strDay).focus();
		  return false;
	}else {
		return true;
	}
}
function search_form(){
	$(":hidden[name=pageIndex]").val(1);	
	$("form[name=regist]").submit();
	
}
//체크 박스 빈값 관련 내용 처리 
function fn_CheckBoxMsg(message, checkboxNm){
	var check_length = $("input:checkbox[name="+checkboxNm+"]:checked").length;
	if (check_length <1){
		alert(message);
		return false;
	}else {
		return true;
	}
}
function ckeckboxValue(message, checkboxNm){
	var checkboxvalue = "";
	var check_length = $("input:checkbox[name="+checkboxNm+"]:checked").length;
	if (check_length <1){
		alert(message);
		return false;
	}else {
		$("input:checkbox[name="+checkboxNm+"]:checked").each(function(){
			checkboxvalue = checkboxvalue+","+ $(this).val();
		});	
	}
	return checkboxvalue.substring(1);
}
function ckeckboxValueEmpty( checkboxNm){
	var checkboxvalue = "";
	var check_length = $("input:checkbox[name="+checkboxNm+"]:checked").length;
	if (check_length >0 ){
		$("input:checkbox[name="+checkboxNm+"]:checked").each(function(){
			checkboxvalue = checkboxvalue+","+ $(this).val();
		});	
		return checkboxvalue.substring(1);
	}
	
}
function fn_uniDel(){
	if (confirm("삭제 하시겠습니까?")== true){
		return true;
	}else {
		return false;
	}
}
function del_Check(url){
	if (confirm("삭제 하시겠습니까?")== true){
		locaion.href=url;
	}
}
function fn_uniCheck( _data, _focusNm, _ckInput, alert_message){
	var url = "/sohoki/util/UniCheck.do";
	if ($("#"+_focusNm).val()!= ""  ){
		$.ajax({
			url: url,
			type : "POST",
			beforeSend:function(jxFax, settings){
	        	jxFax.setRequestHeader('AJAX', true);
	        },
			data : _data,
			success :function (data, textStatus, jqXHR){
			     if (data.status == "UNIQUE"){
			    	 alert(data.message);
			    	 $("#"+_ckInput).val("Y");
			     }else {
			    	 alert(data.message);
			    	 $("#"+_ckInput).val("N");
			     }
			},
			error: function (jqXHR, textStatus, errorThrown){
				alert(textStatus + ":" + errorThrown);
			}
		});
	}else {
		alert(alert_message);
		$("#"+_focusNm).focus();
	}
}
function fn_searchList(_url, _data){
	$.ajax({
			url: url,
			type : "POST",
			data : _data,
			success :function (data, textStatus, jqXHR){
				
			},
			error: function (jqXHR, textStatus, errorThrown){
				alert(textStatus + ":" + errorThrown);
			}
	});
}
//확인 
function fn_uniDel(_url, _data, _action_url){
	if (confirm("삭제 하시겠습니까?")== true){
		$.ajax({
			url: _url,
			type : "POST",
			beforeSend:function(jxFax, settings){
	        	jxFax.setRequestHeader('AJAX', true);
	        },
			data : _data,
			success :function (data, textStatus, jqXHR){
				 if (data.status == "SUCCESS"){
					 alert("data.status:" + data.status);
					 alert("_action_url:" + _action_url);
					 
		    		 $("form[name=regist]").attr("action", _action_url).submit();
				 }else if (data.status == "LOGIN FAIL"){
					location.href="/backoffice/login.do";
				 }else {
					 alert(data.message);
				 }
			},
			error: function (jqXHR, textStatus, errorThrown){
				alert(textStatus + ":" + errorThrown);
			}
		});
	}
	else {
	    return;
	}
}
function fn_uniDelJSON(_url, _data, _action_url){
	if (confirm("삭제 하시겠습니까?")== true){
		$.ajax({
			url: _url,
			type : "POST",
			beforeSend:function(jxFax, settings){
	        	jxFax.setRequestHeader('AJAX', true);
	        },
			contentType : "application/json; charset=utf-8",
	        data : JSON.stringify(_data),
			success :function (data, textStatus, jqXHR){
			     alert(data.message);
		    	 if (data.status == "SUCCESS"){
		    		 $("form[name=regist]").attr("action", _action_url).submit();
				 }else if (data.status == "LOGIN FAIL"){
					location.href="/backoffice/login.do";
				 }
			},
			error: function (jqXHR, textStatus, errorThrown){
				alert(textStatus + ":" + errorThrown);
			}
		});
	}
	else {
	    return;
	}
}
//공용 업데이트 코드
function fn_uniUpdate(_url, _data, _action ){
	$.ajax({
		url: _url,
		type : "POST",
		beforeSend:function(jxFax, settings){
        	jxFax.setRequestHeader('AJAX', true);
        },
		data : _data,
		success :function (data, textStatus, jqXHR){
			 if (data.status == "SUCCESS"){
		    	 alert("정상적으로 처리 되었습니다.");
		     }else if (data.status == "LOGIN FAIL") {
		    	 alert("로그인 정보가 올바르지 않습니다.");
		    	 location.href="/backoffice/login.do";
		     }else {
		    	 alert("처리시 문제가 생겼습니다");
		     }
		},
		error: function (jqXHR, textStatus, errorThrown){
			alert(textStatus + ":" + errorThrown);
		}
	});
	if (_action !=  null){
		$("form[name=regist]").attr("action", _action_url).submit();
	}
}
function uniAjax(url, param, done_callback, fail_callback){
	var jxFax =  $.ajax({
		        type : 'POST',
		        beforeSend:function(jxFax, settings){
		        	jxFax.setRequestHeader('AJAX', true);
		        },
		        url : url,
		        contentType : "application/json; charset=utf-8",
		        data : JSON.stringify(param)
		    }).done(done_callback).fail(fail_callback);
	
    return jxFax;
}
function uniAjaxXform(url, param, done_callback, fail_callback){
	var jxFax =  $.ajax({
		        type : 'POST',
		        beforeSend:function(jxFax, settings){
		        	jxFax.setRequestHeader('AJAX', true);
		        },
		        url : url,
		        contentType : 'application/x-www-form-urlencoded; charset=UTF-8',
		        data : param
		    }).done(done_callback).fail(fail_callback);
	
    return jxFax;
}
// ajax 로딩이미지 
function loadingImg(showGubun){
	if (showGubun == "Viw"){
		$("body").append("<span id='backgroundgif'></span>");
		var loadingimg = "<div style='width:100%;height:100%;z-index:100;background-color:#ffffff;opacity:0.6; position:fixed;text-align:center;vertical-align:middle;'><img src='/images/loading_img.gif'></img></div>";    	
		$("#backgroundgif").html(loadingimg);
	}else{
		$("#backgroundgif").html("");
		if ($("#backgroundgif") != undefined){
			$("#backgroundgif").remove();
		}
	} 	
}
function uniAjaxSerial(url, param, done_callback, fail_callback){
	var jxFax =  $.ajax({
		        type : 'POST',
		        beforeSend:function(jxFax, settings){
		        	jxFax.setRequestHeader('AJAX', true);
		        },
		        url : url,
		        contentType : "application/json; charset=utf-8",
		        data : param,
		    }).done(done_callback).fail(fail_callback);
    return jxFax;
}
function uniAjaxMutipart(url, param, done_callback, fail_callback){
	var jxFax =  $.ajax({
		        type : 'POST',
		        url : url,
		        enctype: 'multipart/form-data',
		        contentType : "application/json; charset=utf-8",
		        processData: false,
	            //contentType: false,
	            cache: false,
	            timeout: 600000,
		        data : param,
		    }).done(done_callback).fail(fail_callback);
	
    return jxFax;
}
//페이징 함수
/*function fn_PageShow(){
	var pageView =  
}*/
//중복 검사 확인 작업 
function fn_UniCheckAlert(_UniCheckFormNm, _focusNm){
	if ($("#"+_UniCheckFormNm).val() == "Y"){
		alert("중복 검사를 하지 않았습니다");
		$("#"+_UniCheckFormNm).focus();
		return false;
	}else {
		return true;
	}
}
jQuery.fn.serializeObject = function() {
    var obj = null;
    try {
        //if (this[0].tagName && this[0].tagName.toUpperCase() == "FORM") {
            var arr = this.serializeArray();
            if (arr) {
                obj = {};
                jQuery.each(arr, function() {
                    obj[this.name] = this.value;
                });
            }//if ( arr ) {
        //}
    } catch (e) {
        alert(e.message);
    } finally {
    }
 
    return obj;
};


//이전 일자 알기 기간은 월로 설정 3 이면 3개월 이전  
function getPastDate(period){
    var dt = new Date();
 
    dt.setMonth((dt.getMonth() + 1) - period);
 
    var year = dt.getFullYear();
    var month = dt.getMonth();
    var day = dt.getDate();
 
    if(month < 10) month = "0" + month;
    if(day < 10) day = "0" + day;
 
    return year + "-" + month + "-" + day;
}

function fn_ComboView(){
	if ($("#adminLevel").val() == "ROLE_ADMIN" || $("#adminLevel").val() == ""){
		$("#partId").hide();
	}else {
		$("#partId").show();
	}
}
//페이징 스크립트;
function ajaxPaging(currentPageNo, firstPageNo, recordCountPerPage, firstPageNoOnPageList, lastPageNoOnPageList, totalPageCount, pageSize, pageScript){
    var pageHtml = "";
    pageHtml += "<div class='new_pager'>";
	 if (currentPageNo == firstPageNo ){
      pageHtml += "<li><a href='#' >&laquo;</a></li>";
	 }else {
      pageHtml += "<li><a href='#' onclick='"+pageScript+"("+ firstPageNo +")';return false; '>&laquo;</a></li>";
	 }
	 if (parseInt(currentPageNo) > parseInt(firstPageNo)){
      pageHtml += "<li><a href='#' onclick='"+pageScript+"("+ parseInt(parseInt(currentPageNo) -1)+");return false;'>&lt;</a></li>"
	 }else {
      pageHtml += "<li><a href='#' >&lt;</a></li>"
	 }
    for(var  i = firstPageNoOnPageList; i<= lastPageNoOnPageList; i++){
		 if (i == currentPageNo){
            pageHtml += "<li><strong>"+i+"</strong></li>";
		 }else {
            pageHtml += "<li><a href='#' onclick='"+pageScript+"("+i+");return false; '>"+i+"</a></li>";
		 }
    }

	 if (parseInt(totalPageCount) > parseInt(pageSize) ){
        pageHtml += "<li><a href='#' onclick='"+pageScript+"("+ parseInt(parseInt(currentPageNo) + 1)+");return false;'>&gt;</a></li>"
	 }else {
        pageHtml += "<li><a href='#' onclick='"+pageScript+"("+ parseInt(parseInt(currentPageNo) + 1)+");return false;'>&gt;</a></li>"
	 }
    if (parseInt(totalPageCount) > parseInt(pageSize)  ){
      pageHtml += "<li><a href='#' onclick='"+pageScript+"("+ totalPageCount +");return false;'>&raquo;</a></li>";
	 }else{
      pageHtml += "<li><a href='#' >&raquo;</a></li>";
	 }	
    pageHtml += "</div>";
    return pageHtml;
 }
//지난 일자는 등록 하지 못하게 하기 
function yesterDayConfirm(res_day, alert_message){
	var day = new Date();
    var dateNow = fnLPAD(String(day.getDate()), "0", 2); //일자를 구함
    var monthNow = fnLPAD(String((day.getMonth() + 1)), "0", 2); // 월(month)을 구함    
    var yearNow = String(day.getFullYear()); //년(year)을 구함
    var today = yearNow + monthNow + dateNow;
    
    if (parseInt(res_day) < today){
    	alert(alert_message);
    	return false;
    }else {
    	return true;
    }	
}
function fnLPAD(val, set, cnt) {
    if (!set || !cnt || val.length >= cnt) {
        return val;
    }
    var max = (cnt - val.length) / set.length;
    for (var i = 0; i < max; i++) {
        val = set + val;
    }
    return val;
}
function drawChart(json_data) {   
	try{
		var data = new google.visualization.DataTable();
		data.addColumn('string', '시간');
		data.addColumn('number', '카운터수');
		//alert(json_data);
		
		data.addRows(JSON.parse(json_data));
		var options = { title: '시간별 접속 현황', fontSize: '12', fontName: '굴림체', 
				        hAxis: {title: '시간', titleTextStyle: {color: 'red', fontName: '굴림체'}} , 
				        vAxis: {title: '접속 카운트', titleTextStyle: {color: 'blue', fontName: '굴림체'}}
		};      
		var chart = new google.visualization.ColumnChart(document.getElementById('chart_div'));
		chart.draw(data, options);   
	}catch( e){
		alert(e);
	}
}
function fn_selectCombo(selectId, selectval, obj, value, txt){
        $("#"+selectId).show();
		$("#"+selectId+" option").remove();
		$("#"+selectId).append("<option value=''>선택</option>");
		for (var i = 0; i <= obj.length; i ++){
			var data = obj[i];
			$("#"+selectId).append("<option value='"+data+"."+value+"'>"+data+"."+txt+"</option>");
	    }   
	    if (selectval != "")
	    	$("#"+selectId).val(selectval);
}
function today_check(code ){
	return ( parseInt(code) < parseInt(today_get()) ) ? false : true;
}
function day_Check(strVal, endValue){
	return ( parseInt(endValue) < parseInt(strVal))? false : true;
}
//iframe 크기 자동 조절
function ResizeFrame(name){
	// IFRAME 내부의 body 개체
	var fBody = document.frames(name).document.body;
	// IFRAME 개체
	var fName = document.all(name);
	// IFRAME 내부의 body개체의 넓이를 계산하여 IFRAME의 넓이를 설정해 준다.
	fName.style.width= fBody.scrollWidth + (fBody.offsetWidth - fBody.clientWidth);
	// IFRAME 내부의 body개체의 높이를 계산하여 IFRAME의 높이를 설정해 준다.
	fName.style.height= fBody.scrollHeight + (fBody.offsetHeight - fBody.clientHeight);
	
	// 만약 IFRAME의 크기 설정에 실패 하였다면 기본크기로 설정한다.
	if (Frame_name.style.height == "0px" || Frame_name.style.width == "0px")	{
		fName.style.width = "700px"; //기본 iframe 너비
		fName.style.height = "300px"; //기본 iframe 높이
	}
}

//달력 부분 정리 하기

//null check
function fn_EmptyCheck(value, defaultVal){
	return  (value == "") ? defaultVal : value;
}
//포스트 전송
function fn_doPost(name , params, url) {
	if (name != null) {
		$('body form[name='+ name +']').remove();
	}
	var frm;
	if (name != null) {
		frm = $('<form name="'+ name +'"></form>').appendTo('body');
	} else {
		frm = $('<form></form>').appendTo('body');
	}
	$(frm).append(this.setParams(params));
	$(frm).attr({
		'method': 'post',
		'action': url,
		'target': '_self'
	}).submit();
}


function setParams (params) {
	var arr = [];
	switch (typeof(params)) {
		case 'undefined':
			break;
		case 'string':
			if (isEmpty(params)) {
				break;
			}
			$.each(params.split('&'), function(i, o) {
				var tmp = o.split("=", 2);
				arr.push('<input type="hidden" name="'+ nvl(tmp[0]) +'" value="'+ nvl(tmp[1]) +'" />');
			});
			break;
		case 'object':
			$.each(params, function(k, v) {
				arr.push('<input type="hidden" name="'+ nvl(k) +'" value="'+ nvl(v) +'" />')
			});
			break;
		default:
			break;
	}
	return arr.join('');
}
function isEmpty(arg) {
	if ((arg == undefined) || (arg == '')) {
		return true;
	}
	return false;
}
function nvl(s, def) {
	if ((def == undefined) || this.isEmpty(def)) {
		def = '';
	}
	if (this.isEmpty(s)) {
		s = def;
	}
	return s;
}
