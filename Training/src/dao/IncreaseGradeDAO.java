package dao;

import java.util.*;

import org.springframework.stereotype.*;

import vo.*;

@Component
public interface IncreaseGradeDAO {

    public List<IncreaseGradeVO> getIncreaseGrades();

    public int insertIncreaseGrade(IncreaseGradeVO i);

    public IncreaseGradeVO findIncreaseGrade(int increaseNum);

    public int deleteIncreaseGrade(int increaseNum);

    public int modifyIncreaseGrade(IncreaseGradeVO increaseGrade);




}
