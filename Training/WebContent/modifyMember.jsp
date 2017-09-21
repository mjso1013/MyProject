<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>사원 수정</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript">

$(document).ready(function(){

  var msg =  '${success}';

  if(msg == "01")
  {
    alert("저장되었습니다.");
  }


  $("#modifyMember").click(function(){
      $.ajax({
          url : 'memberModifyAjax.do',
          type : 'post',
          dataType : 'json',
          data: {
                 'name':$("#name").val(),
                 'position':$("#position").val(),
                 'bossNum':$("#bossNum").val(),
                 'joinDate':$("#joinDate").val(),
                 'salary':$("#salary").val(),
                 'commission':$("#commission").val(),
                 'deptNum':$("#deptNum").val()
                 },
          success : function(data){
           alert(data.KEY);
          }

      });



  });

});

</script>

</head>
<body>
<h1>사원 수정 페이지</h1>

<form action="modifyMemberProc.do" method="post">
<span>이름 : </span>
  <input type="text" name="name" id="name" value="${name}"/>
  <span>직책 : </span>
  <input type="text" name="position" id="position" value="${position}"/>
   <span>직속상사사원번호 : </span>
  <input type="text" name="bossNum" id="bossNum" value="${bossNum}"/>
   <span>입사일 : </span>
  <input type="text" name="joinDate" id="joinDate" value="${joinDate}"/>
   <span>급여 : </span>
  <input type="text" name="salary" id="salary" value="${salary}"/>
   <span>커미션 : </span>
  <input type="text" name="commission" id="commission" value="${commission}"/>
  <span>부서번호 : </span>
  <input type="text" name="deptNum" id="deptNum" value="${deptNum}"/>
  <input type="hidden" name="idNum" id="idNum" value="${idNum}"/>


  <input type="submit" value="수정"/>
  </form>


</body>
</html>