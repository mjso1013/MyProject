<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>부서 등록</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript">

$(document).ready(function(){
  $("#deptBtn").click(function(){
      $.ajax({
          url : 'departmentInsertAjax.do',
          type : 'post',
          dataType : 'json',
          data: {'deptName':$("#deptName").val(),
                 'location':$("#location").val()},
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
<h1>부서 등록 페이지</h1>


<span>부서명 : </span>
  <input type="text" name="deptName" id="deptName">
  <span>지역 : </span>
  <input type="text" name="location" id="location">


  <button id="deptBtn">등록</button>


</body>
</html>