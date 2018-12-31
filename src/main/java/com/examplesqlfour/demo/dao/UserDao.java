package com.examplesqlfour.demo.dao;


import org.apache.ibatis.annotations.*;
import com.examplesqlfour.demo.model.User;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.List;

//Dao 类获取表中指定数据测试是否成功
@Mapper
@Component
public interface UserDao {
    //Mybatis  的注解
    /*
    * 按学号查询
    * */
    @Select("select * from student where Sno=#{Sno}")
     List<User> getUserList(Integer Sno);
     List <User> check(Integer Sno);

     /*
     *
     * 求成绩总和*/
    @Select("select sum(Score) from student")
    int getScoreSum();
/*
*
* 求平均数*/
    @Select("select avg(Score) from student")
    float getScoreAvg();
/*
* 查询所有数据
* */
    @Select("select * from student ")
        List <User> getUserListall();

    @Select("select * from student order by ${sortField}  ${sortOrder}")
    List<User> getter(@Param("soreField") String sortField,@Param("sortOrder")String sortOrder);

/*
* 查询列表个数
* */
    @Select("SELECT COUNT(*) FROM student   ")
    int getUserListAll();
    /*
    *
    * 多段查询*/
    @Select("SELECT * FROM student where Sno like #{key} or Cname like #{key} or Sname like #{key} or Score like #{key} ")
    List <User> getStudentList(String key);//了解like模糊查询
    /*
    * 通过班名Cname查询
    * */
    @Select("SELECT * FROM student where Cname = #{Cname} ")
    List <User> getStudentCnameList(@Param("Cname")String value);
    /*
    * 插入数据
    *
    * */
    @Insert("INSERT INTO student(Sno,Cname,Sname,Score) VALUES(#{Sno},#{Cname},#{Sname},#{Score})")
    int insert(@Param("Sno")Integer Sno,@Param("Cname")String Cname,@Param("Sname")String Sname,@Param("Score") Integer Score);
    /*这样语句中的#{name}、#{age}就分别对应了User对象中的name和age属性。*/
    /*@Insert("INSERT INTO suppliers(id, company) VALUES(#{id}, #{company})")
    void insert(@Param("id")Integer id,@Param("company")String companyr);*/
   /*
   * 删除数据
   * */
    @Delete("DELETE FROM student WHERE Sno = #{Sno}")
    int delete( int Sno);//进行其他段名的删除
/*
* 更新数据
* */
    @Update("UPDATE student SET Sno=#{Sno},Cname=#{Cname},Sname=#{Sname},Score=#{Score} WHERE Sno=#{Sno_pre}")
    int update(@Param("Sno")Integer Sno,@Param("Cname")String Cname,@Param("Sname")String Sname,@Param("Score")Integer Score,@Param("Sno_pre")Integer Sno_pre);

    @Select("select * from student where not Sno=#{Sno}")
    List<User> test(Integer Sno);

}
