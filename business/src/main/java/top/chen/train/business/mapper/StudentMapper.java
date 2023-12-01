package top.chen.train.business.mapper;

import org.apache.ibatis.annotations.Param;
import top.chen.train.business.domain.Student;
import top.chen.train.business.domain.StudentExample;

import java.util.List;

public interface StudentMapper{
    long countByExample(StudentExample example);

    int deleteByExample(StudentExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Student row);

    int insertSelective(Student row);

    List<Student> selectByExample(StudentExample example);

    Student selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") Student row, @Param("example") StudentExample example);

    int updateByExample(@Param("row") Student row, @Param("example") StudentExample example);

    int updateByPrimaryKeySelective(Student row);

    int updateByPrimaryKey(Student row);

    List<Student> selectAll(String school);
}