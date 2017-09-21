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
public class DepartmentController {

    @Autowired
    private SqlSession sqlSession;

    @RequestMapping(value={"departmentReg.do"}, method=RequestMethod.GET)
    public String department(Model model) {




        return "departmentReg.jsp";
    }


    @RequestMapping(value={"departmentInsertAjax.do"}, method=RequestMethod.POST)
    public Map<String,Object> departmentInsertAjax(Model model, String deptName,String location) {

        DepartmentDAO myBatis = sqlSession.getMapper(DepartmentDAO.class);
        DepartmentVO dept = new DepartmentVO();
        System.out.println("deptName : "+deptName);
        System.out.println("location : "+location);
        dept.setDeptName(deptName);
        dept.setLocation(location);

        int af = myBatis.insertDepartment(dept);
        Map<String, Object> map = new HashMap<String, Object>();

        if(af==1) {
            map.put("result", "OK");

        }


        return map;

    }

    @RequestMapping(value={"departmentList.do"}, method=RequestMethod.GET)
    public String memberList(Model model) {

        DepartmentDAO myBatis = sqlSession.getMapper(DepartmentDAO.class);

        List<DepartmentVO> departments = myBatis.getDepartments();

        model.addAttribute("departments", departments);

        return "departmentList.jsp";
    }



    @RequestMapping(value={"deleteDepartment.do"}, method=RequestMethod.GET)
    public String deleteDepartment(Model model, String deptNum) {

        DepartmentDAO myBatis = sqlSession.getMapper(DepartmentDAO.class);

        int ideptNum = Integer.parseInt(deptNum);
        myBatis.deleteDepartment(ideptNum);



        return "departmentList.do";
    }

   @RequestMapping(value={"modifyDepartment.do"}, method=RequestMethod.GET)
    public String modifyDepartment(Model model, String deptNum, String deptName, String location) {

        model.addAttribute("deptNum", deptNum.trim());
        model.addAttribute("deptName", deptName.trim());
        model.addAttribute("location", location.trim());

        return "modifyDepartment.jsp";
    }

    @RequestMapping(value={"modifyDepartmentProc.do"}, method=RequestMethod.POST)
    public String departmentModifyAjax(Model model, String deptNum,String deptName, String location) {

        DepartmentDAO myBatis = sqlSession.getMapper(DepartmentDAO.class);
        int ideptNum = Integer.parseInt(deptNum);

        System.out.println("ideptNum  : "+ideptNum);
        System.out.println("deptName  : "+deptName);
        System.out.println("location  : "+location);

        DepartmentVO department = myBatis.findDepartment(ideptNum);
        department.setDeptName(deptName.trim());
        department.setLocation(location.trim());


        int af = myBatis.modifyDepartment(department);

        return "redirect:departmentList.do";

    }

}
