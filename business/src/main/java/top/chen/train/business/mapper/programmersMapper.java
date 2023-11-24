package top.chen.train.business.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import top.chen.train.business.domain.programmers;
import top.chen.train.business.domain.programmersExample;

public interface programmersMapper {
    long countByExample(programmersExample example);

    int deleteByExample(programmersExample example);

    int deleteByPrimaryKey(Long id);

    int insert(programmers row);

    int insertSelective(programmers row);

    List<programmers> selectByExample(programmersExample example);

    programmers selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") programmers row, @Param("example") programmersExample example);

    int updateByExample(@Param("row") programmers row, @Param("example") programmersExample example);

    int updateByPrimaryKeySelective(programmers row);

    int updateByPrimaryKey(programmers row);
}