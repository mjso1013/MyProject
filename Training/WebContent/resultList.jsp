<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>전체 실적 조회</title>
<style type="text/css">
table, th, td {
  border : 1px solid black;
  border-collapse: collapse;
}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript">

  $(document).ready(function(){
    $("table button.deleteResult").click(function(){

      $.ajax({
      url : 'deleteResult.do',
      type : 'get',
      datatype : 'json',
      data : {'resultNum' : $(this).next().val()},
      success : function(data) {
        if ($.trim(data) == "1") {
          alert("인증번호가 발송되었습니다.");
        } else {
          alert("삭제.");
          location.reload();
        }
    }
    });
    });
  });
</script>
</head>
<body>
  <h1>전체 실적 조회</h1>
  <table>
    <thead>
      <tr>
        <th>실적번호</th>
        <th>연도</th>
        <th>사원번호</th>
        <th>등급</th>
      </tr>
    </thead>
    <tbody>
    <c:forEach items="${results}" var="results">
      <tr>
        <td>${results.resultNum}</td>
        <td>${results.year}</td>
        <td>${results.idNum}</td>
        <td>${results.grade}</td>
        <td><a href="modifyResult.do?resultNum=${results.resultNum}&year=${results.year}&idNum=${results.idNum}&grade=${results.grade}"><button>수정</button></a></td>
        <td><button class="deleteResult">삭제</button><input type="hidden" value="${results.resultNum}"/></td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
  <br>
  <a href="main.jsp"><button>메인으로 가기</button></a>
  <a href="resultReg.do"><button>실적 등록</button></a>
</body>
</html>