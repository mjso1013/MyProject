package dao;

import java.util.*;

import org.springframework.stereotype.*;

import vo.*;

@Component
public interface DepartmentDAO {

    public DepartmentVO findDepartment(int deptNum);

    public List<DepartmentVO> getDepartments();

    public int insertDepartment(DepartmentVO d);

    public int deleteDepartment(int deptNum);

    public int modifyDepartment(DepartmentVO department);


}
