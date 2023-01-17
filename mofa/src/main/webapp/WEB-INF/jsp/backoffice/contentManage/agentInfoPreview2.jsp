<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv='Content-Type' content='text/html; charset=utf-8'>
<meta name='viewport' content='width=device-width, initial-scale=1.0'>  
<script type='text/javascript' src='/js/jquery-2.2.4.min.js'></script>

<title>미리보기</title>

<script type='text/javascript'>
    var contentCount0 = 0;
    var albumLst0 = '${conList}';
    var firstPlay0, errorFlag0;
    var prepareFileNm0, prepareFileType0, prepareFileTime0,  prepareMakeType0;
    $(document).ready(function(){
        console.log('contents play start');
        
        startContentSch();
    });
    function startContentSch(){
            jsonData0 = JSON.parse(albumLst0);
            if(jsonData0.length > 0){
                 firstPlay0 = true;
                 tagMaking0(jsonData0[contentCount0].reportDc, jsonData0[contentCount0].mediaType, jsonData0[contentCount0].detailTime, jsonData0[contentCount0].reportUrl, 'A');
            }
        }
    function tagMaking0(reportDc, fileType, detailTime, reportUrl, makeType){
        var result, mediaTag;
        if (makeType == 'A'){
            $('#content_show0a').html('');
            switch(fileType){
                case 'IMAGE' : result = '<img id="content0a" src="'+reportUrl+reportDc+'" width="1920" height="1080" />'; break;
                case 'MEDIA' : result = '<video id="content0a" src="'+reportUrl+reportDc+'"><source type="video/mp4"/></video>'; break;
                case 'URL'   : result = '<iframe id="content0a" width="1920" height="1080" marginheight="0" marginwidth="0" frameborder="0" scrolling="auto" />'; break;
                default      : result = '<audio id="content0a" controls src="'+reportUrl+reportDc+'"><source type="audio/mpeg"/></audio>'; break;
            }
            mediaTag = $('#content0a');
            $('#content_show0a').html(result);
        } else {
            $('#content_show0b').html('');
            switch(fileType){
                case 'IMAGE' : result = '<img id="content0b" src="'+reportUrl+reportDc+'" width="1920" height="1080" />'; break;
                case 'MEDIA' : result = '<video id="content0b" src="'+reportUrl+reportDc+'"><source type="video/mp4"/></video>'; break;
                case 'URL'   : result = '<iframe id="content0b" width="1920" height="1080" marginheight="0" marginwidth="0" frameborder="0" scrolling="auto"/>'; break;
                default      : result = '<audio id="content0b" controls src="'+reportUrl+reportDc+'"><source type="audio/mpeg"/></audio>'; break;
            }
            mediaTag = $('#content0b');
            $('#content_show0b').html(result);
        }
        if(firstPlay0){
            firstPlay0 = false;
            mediaPlaySetting0(reportDc, fileType, detailTime, reportUrl, makeType);
        } else if (errorFlag0){
            errorFlag0 = false;
            mediaPlaySetting0(reportDc, fileType, detailTime, reportUrl, makeType);
        } else {
            prepareFileNm0 = reportDc;
            prepareFileType0 = fileType;
            prepareFileTime0 = detailTime;
            prepareFileStreCours0 = reportUrl;
            prepareMakeType0 = makeType;
        }
    }
    function mediaPlaySetting0(reportDc, fileType, detailTime, reportUrl, makeType){
        var mediaTag;
        var contentLoadCheck = setTimeout(function(){
            console.log('CONTENTS LOADING ISSUE, NEXT !');
            nextSeqConChk0('1', makeTypeRevers(makeType));
        }, 3000);
        var contentPauseChk = setTimeout(function(){
            console.log('content Pause ? Play Time : ' + ((detailTime*1000)+2000));
            nextSeqConChk0('2', prepareMakeType0);
        }, ((detailTime*1000)+2000));
        
        var idNumberRing = makeType == "A" ? "0a" : "0b";
        var zIndexCount = makeType == "A" ? "150" : "300";
        
        if(fileType == 'URL'){
            $('#content' + idNumberRing).attr('src',reportUrl);
            $('#content' + idNumberRing).load(function(){
                //iframe 로드시점
                console.log('URL LOADING');
                mediaTag = $('#content' + idNumberRing); $('#content_show0b').css('z-index', zIndexCount);
                mediaTag.each(function(){
                    clearTimeout(contentLoadCheck);
                    /*setTimeout(function(){
                        clearTimeout(contentPauseChk);
                        nextSeqConChk0('2', prepareMakeType0);
                    }, detailTime*1000);*/
                    nextSeqConChk0('1', makeTypeRevers(makeType));
                });
                mediaTag.on('error', function(err){
                    clearTimeout(contentLoadCheck);
                    clearTimeout(contentPauseChk);
                    console.log('CONTENT NAME : ' + reportUrl + ', ERROR !');
                    errorFlag0 = true;
                    nextSeqConChk0('1', makeType);
                });
            });
        }else{
           console.log('IMAGE LOADING');
           mediaTag = $('#content' + idNumberRing); $('#content_show0b').css('z-index', zIndexCount);   
        }
        
        
        
        /*switch(makeType){
            case 'A' : mediaTag = $('#content0a'); $('#content_show0b').css('z-index', '150'); break;
            case 'B' : mediaTag = $('#content0b'); $('#content_show0b').css('z-index', '300'); break;
        }*/
        if(fileType != 'URL'){
            if(fileType == 'IMAGE'){
                mediaTag.each(function(){
                    clearTimeout(contentLoadCheck);
                    setTimeout(function(){
                        clearTimeout(contentPauseChk);
                        nextSeqConChk0('2', prepareMakeType0);
                    }, detailTime*1000);
                    nextSeqConChk0('1', makeTypeRevers(makeType));
                });
            } else {
                mediaTag[0].autoplay = true;
                mediaTag[0].src = reportUrl+reportDc + '?autoplay=1';
                mediaTag.on('loadeddata', function(){
                    clearTimeout(contentLoadCheck);
                    nextSeqConChk0('1', makeTypeRevers(makeType));
                });
                mediaTag.on('ended', function(){
                    clearTimeout(contentPauseChk);
                    nextSeqConChk0('2', prepareMakeType0);
                });
            }
            mediaTag.on('error', function(err){
                clearTimeout(contentLoadCheck);
                clearTimeout(contentPauseChk);
                console.log('CONTENT NAME : ' + reportUrl + ', ERROR !');
                errorFlag0 = true;
                nextSeqConChk0('1', makeType);
            });
        }    
    }
    function nextSeqConChk0(checkType, makeType){
        
        if(checkType == '1'){
            contentCount0+=1;
            if(contentCount0 != jsonData0.length){
                tagMaking0(jsonData0[contentCount0].reportDc, jsonData0[contentCount0].mediaType, jsonData0[contentCount0].detailTime,  jsonData0[contentCount0].reportUrl, makeType);
            }
        } else {
            mediaPlaySetting0(prepareFileNm0, prepareFileType0, prepareFileTime0, prepareFileStreCours0, prepareMakeType0);
        }
        
        if(contentCount0 == jsonData0.length -1){
            contentCount0 = -1;
            /*startContentSch();*/
            /*location.href = document.location.href;*/
        }
        
    }
    function makeTypeRevers(makeType){
        if(makeType == 'A'){
            return 'B';
        } else {
            return 'A';
        }
    }
    
    function sleep(delay) {
        var start = new Date().getTime();
        while (new Date().getTime() < start + delay);
    }
</script>
<style>	
    body{
        margin : 0;
        background-color : #000;
    }
    .contentBox0{
        position : fixed;
        top : 0;
        width: 1920px; height: 1080px;
        float : left;
        background-color : #000;
    }
    .contentBox0 video{
        width: 1920px; height: 1080px;
        min-width: 1920px; min-height: 1080px;
    }
</style>	
</head>	
<body>
    <!--<input type="hidden" id="jsonData0" value='${jsonData0}'/>-->
    <iframe src='/upload/silence.mp3' allow='autoplay' id='setAudio' style='display:none'></iframe>  
    <div id='content_show0a' class='contentBox0' style='z-index:200;'></div>
    <div id='content_show0b' class='contentBox0' style='z-index:150;'></div>
</body>	
</html>	
