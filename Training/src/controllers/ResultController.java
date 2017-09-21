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
public class ResultController {

    @Autowired
    private SqlSession sqlSession;

    @RequestMapping(value={"resultReg.do"}, method=RequestMethod.GET)
    public String result(Model model) {


        return "resultReg.jsp";
    }


    @RequestMapping(value={"resultInsertAjax.do"}, method=RequestMethod.POST)
    public Map<String,Object> resultInsertAjax(Model model, String year,String idNum, String grade) {

        ResultDAO myBatis = sqlSession.getMapper(ResultDAO.class);
        ResultVO result = new ResultVO();
        result.setYear(Integer.parseInt(year));
        result.setIdNum(Integer.parseInt(idNum));
        result.setGrade(grade);
        Map<String, Object> map = new HashMap<String, Object>();

        int af = myBatis.insertResult(result);


        return map;

    }

    @RequestMapping(value={"resultList.do"}, method=RequestMethod.GET)
    public String resultList(Model model) {

        ResultDAO myBatis = sqlSession.getMapper(ResultDAO.class);

        List<ResultVO> results = myBatis.getResults();


        model.addAttribute("results", results);

        return "resultList.jsp";
    }

    @RequestMapping(value={"deleteResult.do"}, method=RequestMethod.GET)
    public String deleteResult(Model model, String resultNum) {

        ResultDAO myBatis = sqlSession.getMapper(ResultDAO.class);

        int iresultNum = Integer.parseInt(resultNum);
        myBatis.deleteResult(iresultNum);



        return "resultList.do";
    }

   @RequestMapping(value={"modifyResult.do"}, method=RequestMethod.GET)
    public String modifyDepartment(Model model, String resultNum, String year, String idNum, String grade) {
        int iResultNum = Integer.parseInt(resultNum);
        int iyear = Integer.parseInt(year);
        int iidNum = Integer.parseInt(idNum);

        model.addAttribute("resultNum", iResultNum);
        model.addAttribute("year", iyear);
        model.addAttribute("idNum", iidNum);
        model.addAttribute("grade", grade.trim());

        return "modifyResult.jsp";
    }

    @RequestMapping(value={"modifyResultProc.do"}, method=RequestMethod.POST)
    public String departmentModifyAjax(Model model, String resultNum,String year, String idNum,String grade) {

        ResultDAO myBatis = sqlSession.getMapper(ResultDAO.class);
        int iresultNum = Integer.parseInt(resultNum);
        int iyear = Integer.parseInt(year);
        int iidNum = Integer.parseInt(idNum);


        ResultVO result = myBatis.findResult(iresultNum);
        result.setYear(iyear);
        result.setIdNum(iidNum);
        result.setGrade(grade);

        int af = myBatis.modifyResult(result);

        return "redirect:resultList.do";

    }

}
