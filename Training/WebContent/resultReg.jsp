<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>실적 등록</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript">

$(document).ready(function(){
  $("#resultBtn").click(function(){
      $.ajax({
          url : 'resultInsertAjax.do',
          type : 'post',
          dataType : 'json',
          data: {'year':$("#year").val(),
                 'idNum':$("#idNum").val(),
                 'grade':$("#grade").val()
                 },
          success : function(data){
           alert(data.KEY);
          },
          error : function(request,status,error) {
            alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);

                  }

      });



  });

});

</script>

</head>
<body>
<h1>실적 등록 페이지</h1>


<span>연도 : </span>
  <select id="year" name="year">
    <option value="2017">2017</option>
    <option value="2016">2016</option>
    <option value="2015">2015</option>
  </select>
  <span>사원번호 : </span>
  <input type="text" name="idNum" id="idNum" style="width: 30px">
   <span>등급 : </span>
  <select id="grade" name="grade">
    <option value="A">A</option>
    <option value="B">B</option>
    <option value="C">C</option>
    <option value="D">D</option>
  </select>



  <button id="resultBtn">등록</button>


</body>
</html>