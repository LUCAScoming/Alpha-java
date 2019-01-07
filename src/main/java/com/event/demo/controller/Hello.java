package com.event.demo.controller;

import com.event.demo.dao.UserDao;
import com.event.demo.model.User;
import com.event.demo.service.UserService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class Hello {
    @Autowired
    private UserDao userDao;


    @Autowired
    private UserService userService;
    private Integer num = 1;

    @RequestMapping(value = "/hello", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody

    public int getHello() {
        num++;
        return num;

    }


    @RequestMapping(value = "/test")
    @ResponseBody
    public List<User> test(Integer Sno) {
        return userDao.test(Sno);
    }

    /*
     * 分页查询** /

    /*PageHelper.startpage(1,1);*/

    @RequestMapping(value = "/selectpage")
    @ResponseBody
    public List<User> getUser(int pageNum, int pageSize, String orderBy) {
        int count = userDao.getUserListAll();
        PageHelper.startPage(pageNum, pageSize);/*每页的数量给pageSize条,查询第pageNun的结果*/
        /*PageHelper.orderBy(orderBy);*/
        /*
         * 进行分页结果的排序，id为字段名，排序规则DESC/ASC
         * */
        return userDao.getUserListall();
    }

    /*
     * 查询全部
     *
     * */
    @RequestMapping(value = "/selectAll")
    @ResponseBody
    public List<User> getUser() {
        return userDao.getUserListall();
    }

    /*
     *@parm value
     * key
     *  */
    @RequestMapping(value = "/search")
    @ResponseBody
    public List<User> search(String key) {
        return userDao.getStudentList(key);
    }

    /*
     *
     * 查询条数*/
    @RequestMapping(value = "/selectcount")
    @ResponseBody
    public int getUsercount() {
        int count = userDao.getUserListAll();
        return count;
    }

    /*
     * 通过学生学号查询学号
     *
     * Sno学生学号*/
    @RequestMapping(value = "/select")
    @ResponseBody
    public List<User> select(@RequestParam("Sno") Integer Sno) {
        return userDao.getUserList(Sno);
    }

    /*
     * 添加数据
     * Sno学号，Cname班级名，Sname学生姓名
     * 成功返回true
     * */
    @RequestMapping(value = "/add")
    @ResponseBody
    public boolean save(Integer Sno, String Cname, String Sname, Integer Score) {
      /* boolean is_save=false;
       List<User> checkList = userDao.getUserList(Sno);
       if (checkList.isEmpty()){
          userDao.insert(Sno,Cname,Sname,Score);
           is_save=true;
       }
       return is_save;*/
        return userService.addService(Sno, Cname, Sname, Score);
    }

    /*
     * 删除操作
     * 通过@PathVariable获取地址中Sno的参数
     * Sno学号
     * */
    @RequestMapping(value = "/delete/Sno={Sno}")
    @ResponseBody
    public boolean delete(@PathVariable("Sno") int Sno) {
        if (userDao.delete(Sno) == 1) return true;
        else return false;
        /*return userDao.delete(id);*/
    }

    /*
     * 更新操作
     * Sno更新后学号
     * Cname班名
     * Sname学生姓名
     * Sno_pre更新之前的学号
     * */

    @RequestMapping(value = "/update")
    @ResponseBody
    public boolean update(int Sno, String Cname, String Sname, Integer Score, Integer Sno_pre) {
        return userService.updateservice(Sno, Cname, Sname, Score, Sno_pre);
    }

    /*
     * 求学生成绩总和*/
    @RequestMapping(value = "/sum")
    @ResponseBody
    public int sum() {
        return userDao.getScoreSum();
    }

    @RequestMapping(value = "/avg")
    @ResponseBody
    public float avg() {
        return userDao.getScoreAvg();
    }

    @RequestMapping(value = "/order")
    @ResponseBody
    public List<User> getListOrder(String key, String order) {
        return userDao.getter(key, order);
    }

    /////////////////////////////////////////////////////TkMapper//////////////////////////////////////////////

    @RequestMapping("queryBySno")
    public List<User> query(Integer sno){
        return userService.query(sno);
    }

}
