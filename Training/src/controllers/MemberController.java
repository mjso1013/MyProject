package controllers;

import java.util.*;

import javax.servlet.http.*;

import org.apache.ibatis.session.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

import dao.*;
import vo.*;

@Controller
public class MemberController {

    @Autowired
    private SqlSession sqlSession;

    @RequestMapping(value={"memberReg.do"}, method=RequestMethod.GET)
    public String member(Model model, HttpServletRequest req) {

        String aa = req.getParameter("success");
        System.out.println(aa);
        model.addAttribute("success",aa);

        return "memberReg.jsp";
    }

    @RequestMapping(value={"memberInsertAjax.do"}, method=RequestMethod.POST)
    public String memberInsertAjax(Model model, String name,String position, String bossNum, String joinDate, String salary, String commission, String deptNum) {

        MemberDAO myBatis = sqlSession.getMapper(MemberDAO.class);
        MemberVO member = new MemberVO();

        int ibossNum = Integer.parseInt(bossNum);

        member.setName(name);
        member.setPosition(position);
        member.setBossNum(ibossNum);
        member.setJoinDate(joinDate);
        member.setSalary(Integer.parseInt(salary));
        member.setCommission(Integer.parseInt(commission));
        member.setDeptNum(Integer.parseInt(deptNum));

        int af = myBatis.insertMember(member);

        model.addAttribute("success", "01");

        return "redirect:memberReg.do?success=01";

    }

    /*
     *
     *
     *
     * */

    @RequestMapping(value={"memberList.do"}, method=RequestMethod.GET)
    public String memberList(Model model) {

        //DAO
        MemberDAO myBatis = sqlSession.getMapper(MemberDAO.class);


        List<MemberVO> members = myBatis.getMembers();

        List<IncreaseGradeVO> avgIncreases = myBatis.avgIncreaseRate();

        List<ResultVO> avgGrades = myBatis.avgGrade();

        List<MemberVO> increaseSals = myBatis.increaseSal();


        for(int i = 0; i<members.size();i++) {
            members.get(i).setAvgIncrease(avgIncreases.get(i).getAvgIncreaseRate());
        }

        for(int i = 0;i<members.size();i++) {
            members.get(i).setAvgGrade(avgGrades.get(i).getAvgGrade());
        }

        for(int i =0; i<members.size();i++) {
            members.get(i).setIncreaseSal(increaseSals.get(i).getIncreaseSal());

        }

        //직급별 사원수, 최고급여, 평균 급여, 총액
        List<PositionStatisticsVO> getInfos = myBatis.getPositionInfo();

        //포지션 리스트 받기
        List<String> positionList = myBatis.getPositionList();

        int avgIncreaseAvg = myBatis.avgIncreaseRateAvg();
        int increaseSalAvg = myBatis.increaseSalAvg();
        int salAvg = myBatis.salAvg();
        int commissionAvg = myBatis.commissionAvg();
        String avgGradeAvg = myBatis.avgGradeAvg();


        //전체 회원 수
        int totalMemberCount = members.size();

        MemberVO memberVO = new MemberVO();
        memberVO.setIdNum(members.size()+1);
        memberVO.setName("전체 평균");
        memberVO.setAvgIncrease(Integer.toString(avgIncreaseAvg));
        memberVO.setSalary(salAvg);
        memberVO.setIncreaseSal(Integer.toString(increaseSalAvg));
        memberVO.setAvgGrade(avgGradeAvg);
        memberVO.setCommission(commissionAvg);
        members.add(memberVO);


        model.addAttribute("getInfos", getInfos);
        model.addAttribute("totalMemberCount", totalMemberCount);
        model.addAttribute("avgIncreases", avgIncreases);
        model.addAttribute("members", members);
        model.addAttribute("positionList", positionList);


        return "memberList.jsp";
    }

    @RequestMapping(value={"deleteMember.do"}, method=RequestMethod.GET)
    public String deleteMember(Model model, String idNum) {

        MemberDAO myBatis = sqlSession.getMapper(MemberDAO.class);
        System.out.println("사원번호  :   "+idNum);
        int iidNum = Integer.parseInt(idNum);

        try {
        myBatis.deleteMember(iidNum);
            model.addAttribute("check", "1");
        }catch(Exception e) {
            System.out.println("오류발생");
            model.addAttribute("check", "2");
        }

        return "deleteList.jsp";
    }

    @RequestMapping(value={"modifyMember.do"}, method=RequestMethod.GET)
    public String modifyMember(Model model, String idNum, String name, String position, String bossNum, String joinDate, String salary, String commission, String deptNum) {

        model.addAttribute("idNum", idNum.trim());
        model.addAttribute("name", name.trim());
        model.addAttribute("position", position.trim());
        model.addAttribute("bossNum", bossNum.trim());
        model.addAttribute("joinDate", joinDate.substring(0, 10));
        model.addAttribute("salary", salary.trim());
        model.addAttribute("commission", commission.trim());
        model.addAttribute("deptNum", deptNum.trim());

        return "modifyMember.jsp";
    }

    @RequestMapping(value={"modifyMemberProc.do"}, method=RequestMethod.POST)
    public String memberModifyAjax(Model model, String name,String position, String bossNum, String joinDate, String salary, String commission, String deptNum,String idNum) {

        MemberDAO myBatis = sqlSession.getMapper(MemberDAO.class);
        int iidNum = Integer.parseInt(idNum);

        MemberVO member = myBatis.findMember(iidNum);
        int ibossNum = Integer.parseInt(bossNum);

        member.setName(name);
        member.setPosition(position);
        member.setBossNum(ibossNum);
        member.setJoinDate(joinDate);
        member.setSalary(Integer.parseInt(salary));
        member.setCommission(Integer.parseInt(commission));
        member.setDeptNum(Integer.parseInt(deptNum));

        int af = myBatis.modifyMember(member);

        return "redirect:memberList.do";

    }

}
