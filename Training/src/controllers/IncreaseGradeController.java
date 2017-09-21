package controllers;

import java.util.*;

import org.apache.ibatis.session.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

import com.google.gson.*;

import dao.*;
import vo.*;

@Controller
public class IncreaseGradeController {

    @Autowired
    private SqlSession sqlSession;

    @RequestMapping(value={"increaseGradeReg.do"}, method=RequestMethod.GET)
    public String increaseGrade(Model model) {

        IncreaseGradeDAO myBatis = sqlSession.getMapper(IncreaseGradeDAO.class);


        return "increaseGradeReg.jsp";
    }

    @RequestMapping(value={"increaseGradeInsertAjax.do"}, method=RequestMethod.POST)
    public Map<String,Object> increaseGradeInsertAjax(Model model, String year,String grade, String increaseRate) {

        IncreaseGradeDAO myBatis = sqlSession.getMapper(IncreaseGradeDAO.class);
        IncreaseGradeVO i = new IncreaseGradeVO();
        Map<String,Object> map = new HashMap<String, Object>();

        System.out.println("year : "+year);
        System.out.println("grade : "+grade);
        System.out.println("increaseRate : "+increaseRate);

        i.setYear(Integer.parseInt(year));
        i.setGrade(grade);
        i.setIncreaseRate(Integer.parseInt(increaseRate));
        int af = myBatis.insertIncreaseGrade(i);
        if(af > 0)
        {
            map.put("KEY", "OK");
        }

        return map;

    }

    @RequestMapping(value={"increaseGradeList.do"}, method=RequestMethod.GET)
    public String memberList(Model model) {

        IncreaseGradeDAO myBatis = sqlSession.getMapper(IncreaseGradeDAO.class);

        List<IncreaseGradeVO> increaseGrades = myBatis.getIncreaseGrades();

        model.addAttribute("increaseGrades", increaseGrades);

        return "increaseGradeList.jsp";
    }



    @RequestMapping(value={"deleteIncreaseGrade.do"}, method=RequestMethod.GET)
    public String deleteDepartment(Model model, String increaseNum) {

        IncreaseGradeDAO myBatis = sqlSession.getMapper(IncreaseGradeDAO.class);

        int iincreaseNum = Integer.parseInt(increaseNum);
        myBatis.deleteIncreaseGrade(iincreaseNum);

        return "increaseGradeList.do";
    }


   @RequestMapping(value={"modifyIncreaseGrade.do"}, method=RequestMethod.GET)
    public String modifyDepartment(Model model, String increaseNum, String year, String grade, String increaseRate) {

        model.addAttribute("increaseNum", increaseNum.trim());
        model.addAttribute("year", year.trim());
        model.addAttribute("grade", grade.trim());
        model.addAttribute("increaseRate", increaseRate.trim());

        return "modifyIncreaseGrade.jsp";
    }

    @RequestMapping(value={"modifyIncreaseGradeProc.do"}, method=RequestMethod.POST)
    public String departmentModifyAjax(Model model, String increaseNum,String year, String grade, String increaseRate) {

        IncreaseGradeDAO myBatis = sqlSession.getMapper(IncreaseGradeDAO.class);
        int iincreaseNum = Integer.parseInt(increaseNum);

        IncreaseGradeVO increase = myBatis.findIncreaseGrade(iincreaseNum);
        increase.setGrade(grade.trim());
        increase.setYear(Integer.parseInt(year));
        increase.setIncreaseRate(Integer.parseInt(increaseRate));


        int af = myBatis.modifyIncreaseGrade(increase);

        return "redirect:increaseGradeList.do";
    }

}
