package com.event.demo.model;

import javax.persistence.Entity;

@Entity
public class User {
    private Integer id;
   /*
   *
   * 学号*/
    private Integer Sno;
    /*
    * 班级*/
    private String Cname;
    /*
    *
    * 学生姓名*/
    private  String Sname;
    /*
    *
    * 成绩*/

    private  Integer Score;

    public  Integer getScore(){
        return  Score;
    }

    public  void  setScore(){
        this.Score=Score;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Integer getSno() {
        return Sno;
    }

    public void setSno(Integer sno) {
        this.Sno = Sno;
    }
    public String getCname() {
        return Cname;
    }

    public void setCname(String cname) {
        this.Cname = Cname;
    }

    public String getSname() {
        return Sname;
    }

    public void setSname(String sname) {
        this.Sname = Sname;
    }






}
