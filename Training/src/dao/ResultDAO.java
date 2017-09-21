package dao;

import java.util.*;

import org.springframework.stereotype.*;

import vo.*;

@Component
public interface ResultDAO {

    public List<ResultVO> getResults();

    public int insertResult(ResultVO r);

    public ResultVO findResult(int resultNum);

    public int deleteResult(int resultNum);

    public int modifyResult(ResultVO result);


}
