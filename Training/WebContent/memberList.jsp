<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>전체 사원 조회</title>
<style type="text/css">
table, th, td {
  border: 1px solid black;
  border-collapse: collapse;
}

td{
  text-align: right;
}

#memberCountDiv{
 display: none;
}

#positionStatisticsDiv{
 display: none;
}

#memberListDiv{
 display: none;
}

.selectEffect{
font-weight: bold;
background-color: yellow;
}

.mouseOverEffect{
background-color: yellow;
}

.mouseOutWhite{
background-color: white;
}

.oddTrColor{
background-color: #B7F0B1;
}

</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript">
  $(document).ready(function() {
    $("table button.deleteMember").click(function() {
      $.ajax({
        url : 'deleteMember.do',
        type : 'get',
        datatype : 'json',
        data : {
          'idNum' : $(this).next().val()
        },
        success : function(data) {
          if ($.trim(data) == "1") {
            alert("삭제");
            location.reload();
          } else {
            alert("오류발생");
          }
        }
      });
    });

  $("#memberCountBtn").click(function(){
  if($("#memberCountDiv").css('display')=='block'){
       $("#memberCountDiv").css("display","none");
     }else{
      $("#memberCountDiv").css("display","block");
     }
  });

  $("#positionStatisticsBtn").click(function(){
    if($("#positionStatisticsDiv").css('display')=='block'){
         $("#positionStatisticsDiv").css("display","none");
       }else{
        $("#positionStatisticsDiv").css("display","block");
       }
  });

  $("#memberListBtn").click(function(){
    if($("#memberListDiv").css('display')=='block'){
      $("#memberListDiv").css("display","none");
    }else{
     $("#memberListDiv").css("display","block");
    }
  });


  /* 짝수 tr 색 넣기 */
  $("tbody tr:even").addClass("oddTrColor");
  $("#memberListTable tr:last").css("background-color","activecaption");

      /* 마우스오버 했을 때 노란색으로 표시 */
    $("#memberListTable tbody tr").mouseover(function(){
      $(this).addClass("mouseOverEffect");
      if($("tbody tr").hasClass('oddTrColor')){
        $(this).removeClass('oddTrColor');

      }
    });


    /* 마우스 아웃했을 때 없애기 */
    $("#memberListTable tbody tr:even").mouseout(function(){
      $(this).removeClass("mouseOverEffect");
    });

     $("#memberListTable tbody tr:odd").mouseout(function(){

        if($(this).hasClass('selectEffect')){
          $(this).removeClass("mouseOverEffect");
          $(this).removeClass('oddTrColor');
        }else{

        $(this).removeClass("mouseOverEffect");
        $(this).addClass("oddTrColor");
        }

      });


    /* 눌렀을 때 배경 노락색 글자 BOLD */
    $("table").eq(2).find("tbody tr").click(function(){

      if($("#memberListTable tbody tr:even").hasClass('selectEffect')){
         $("#memberListTable tbody tr").removeClass('selectEffect');
         $(this).addClass('selectEffect');
      }else if($("#memberListTable tbody tr:odd").hasClass('selectEffect')){
        $("#memberListTable tbody tr").removeClass('selectEffect');
        $(this).addClass('selectEffect');
        $("#memberListTable tbody tr:odd").addClass('oddTrColor');
        $(this).removeClass('oddTrColor');
      }else{
        $(this).addClass('selectEffect');
      }
    });


    /* 평균 실적 등급을 누르면 같은 평균 실적 등급 빨간색으로 표시 */
    /*이중 each*/
    $(".memberListClass tbody tr td.avgGrade").each(function(index){
        $(this).click(function(){

            $(".memberListClass tbody tr td.avgGrade").css({"color":"black","font-weight":"normal"});

            $(".memberListClass tbody tr td.avgGrade").each(function(){

             if($(this).text()==$(".memberListClass tbody tr td.avgGrade").eq(index).text()){

               $(this).css({"color":"red","font-weight":"bold"});
             }

           });
       });
    });


    /* SELECT BOX로 리스트 조건 걸기 */
    $("select").change(function(){

    /* SELECT 가 CHANGE 될 때마다 리스트 초기화 */
      $(".memberListClass tbody tr").show();

      $(".memberListClass tbody tr td.positionList").each(function(index, item){
           /* select 둘다 전체일때 */
          if($("#positionSelect option:selected").text()=='전체'){
            if($("#deptSelect option:selected").text()=='전체'){
               $(".memberListClass tbody tr").show();

             /* positionSelect는 전체고 deptSelect는 조건이 있을때 */
             }else if($("#deptSelect option:selected").text()!=$(".memberListClass tbody tr td.deptList").eq(index).text()){

                 $(".memberListClass tbody tr").eq(index).hide();
               }
          }

          /* positionSelect의 text가 positionList의 text와 다를때 */
          else if($("#deptSelect option:selected").text()=='전체'){

            /* positionSelect는 조건이 있고 deptSelect는 전체일때 */
            if($("#positionSelect option:selected").text()!=$(".memberListClass tbody tr td.positionList").eq(index).text()){
                 $(".memberListClass tbody tr").eq(index).hide();
              }

          /* select 둘다 조건에 걸렸을 때 */
          }else if($("#deptSelect option:selected").text()!=$(".memberListClass tbody tr td.deptList").eq(index).text()||$("#positionSelect option:selected").text()!=$(".memberListClass tbody tr td.positionList").eq(index).text()){
              $(".memberListClass tbody tr").eq(index).hide();
          }
      });
    });


    /* 테이블의 tr의 갯수 구하고 0이면 데이터 없음 표시 */
    $("select").change(function(){
       var rowCount = document.getElementById('rowCount');
       rowCount.setAttribute("value",$('#memberListTable tbody tr:visible').length);

       if(0 === $('#memberListTable tbody tr:visible').length)
       {
         $("#trText").css("display", "block");
       }else{
         $("#trText").css("display", "none");
       }
    });


  });
</script>
</head>
<body>

<h1>회사 관련 목록 보기</h1>
<div>
<button id="memberCountBtn">총 사원수 보기</button>&nbsp;<button id="positionStatisticsBtn">직급별 통계 보기</button>&nbsp;<button id="memberListBtn">전체사원보기 보기</button>
</div>
<br/><br/>

 <div id="memberCountDiv">
  <table id="memberCountTable">
    <thead>
      <tr>
        <th style="background-color: skyblue;">총 사원수</th>
      </tr>
    </thead>
    <tbody>
      <tr>
        <td>${totalMemberCount}명</td>
      </tr>
    </tbody>
  </table>
 </div>

 <div id="positionStatisticsDiv">
  <h4>직급별 통계</h4>

  <table>
    <thead>
      <tr style="background-color: skyblue">
        <th></th>
        <th>사원수</th>
        <th>최고 급여</th>
        <th>최소 급여</th>
        <th>평균 급여</th>
        <th>총액</th>
      </tr>
    </thead>
    <tbody>
      <c:forEach items="${getInfos}" var="getInfos">
      <tr>
        <td style="background-color: pink; font-weight: bold;">${getInfos.position }</td>
        <td>${getInfos.memberCount }명</td>
        <td><fmt:formatNumber value="${getInfos.maxSal}" pattern="#,###,###"/>만원</td>
        <td><fmt:formatNumber value="${getInfos.minSal}" pattern="#,###"/>만원</td>
        <td><fmt:formatNumber value="${getInfos.avgSal}" pattern="#,###"/>만원</td>
        <td><fmt:formatNumber value="${getInfos.sumSal}" pattern="#,###"/>만원</td>
      </tr>
      </c:forEach>
    </tbody>
  </table>
 <br><br>
 </div>

<div id="memberListDiv">
  <div style="position:relative; padding-left: 700px">
    <span>직급 : </span>
    <select id="positionSelect">
      <option value="positionAll">전체</option>
      <c:forEach items="${positionList}" var="positionList">
      <option>${positionList }</option>
      </c:forEach>

    </select>
    <span>부서명 : </span>
    <select id="deptSelect">
      <option value="deptAll">전체</option>
      <option value="salesDept">영업부</option>
      <option value="devDept">개발부</option>
      <option value="board">이사회</option>
    </select>
  </div>
  <table id="memberListTable" class="memberListClass">
    <thead>
      <tr style="background-color: skyblue">
        <th>사원번호</th>
        <th>이름</th>
        <th>직급</th>
        <th>직속상사명</th>
        <th style="width: 100px;">입사일</th>
        <th>평균인상률</th>
        <th style="width: 100px;">급여</th>
        <th>급여 총 인상액</th>
        <th>평균 실적 등급</th>
        <th>커미션</th>
        <th>부서명</th>
      </tr>
    </thead>
    <tbody>
      <c:forEach items="${members}" var="members" varStatus="status">
        <c:if test="${status.last }">
          <tr style="background-color: activecaption;">
        </c:if>
        <c:if test="${!status.last}">
            <td>${members.idNum}</td>
        </c:if>
        <c:if test="${status.last}">
            <td></td>
        </c:if>
            <td style="text-align: center;">${members.name}</td>
            <td class="positionList" style="text-align: center;">${members.position}</td>
            <td style="text-align: center;">${members.boss}</td>
            <td style="text-align: center;">${members.joinDate.substring(0,10)}</td>
            <td>${members.avgIncrease }%</td>
            <td><fmt:formatNumber value="${members.salary}" pattern="#,###"/>만원</td>
            <td><fmt:formatNumber value="${members.increaseSal }" pattern="#,###"/>만원</td>
            <td class="avgGrade">${members.avgGrade }</td>
            <td>${members.commission}</td>
            <td class="deptList">${members.deptName}</td>
       <c:if test="${!status.last}">
            <td><a
              href="modifyMember.do?idNum=${members.idNum}&name=${members.name}&position=${members.position}&bossNum=${members.bossNum}
          &joinDate=${members.joinDate}&salary=${members.salary}&commission=${members.commission}&deptNum=${members.deptNum}"><button>수정</button></a></td>
            <td><button class="deleteMember">삭제</button>
              <input type="hidden" value="${members.idNum}" /></td>
       </c:if>
          </tr>
      </c:forEach>
    </tbody>
  </table>
   <p id="trText" style="display: none; font-weight: bold; padding-left: 200px">데이터가 존재하지 않습니다.</p>
   데이터 수 : <input type="text" id="rowCount" width="30px"/>
  <br>
</div>
  <a href="main.jsp"><button>메인으로 가기</button></a>
  <a href="memberReg.do"><button>사원 등록</button></a>

</body>
</html>