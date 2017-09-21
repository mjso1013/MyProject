package dao;

import java.util.*;

import org.springframework.stereotype.*;

import vo.*;

@Component
public interface MemberDAO {

    public MemberVO findMember(int idNum);

    public List<MemberVO> getMembers();

    public int insertMember(MemberVO m);

    public int deleteMember(int idNum);

    public int modifyMember(MemberVO member);

    public List<IncreaseGradeVO> avgIncreaseRate();

    public List<ResultVO> avgGrade();

    public List<MemberVO> increaseSal();

    public List<PositionStatisticsVO> getPositionInfo();

    public int avgIncreaseRateAvg();

    public int increaseSalAvg();

    public int salAvg();

    public int commissionAvg();

    public String avgGradeAvg();

    public List<String> getPositionList();

}
