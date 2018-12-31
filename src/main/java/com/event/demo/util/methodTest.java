package com.event.demo.util;

import com.yisi.stiku.mathsgo.live.service.MgLiveCallbackLiveRecordService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: DELL
 * Date: 2018/7/11
 * Time: 11:16
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/context/spring*.xml"})
@WebAppConfiguration
public class methodTest {
    @Autowired
    private MgLiveCallbackLiveRecordService mgLiveCallbackLiveRecordService;

    @Test
    public void test01() {
        List<String> list = new ArrayList<String>();
        list.add("1234567");
        list.add("123452");
        list.add("012");
        list.add("013");
        mgLiveCallbackLiveRecordService.playBackListSort(list);
    }
}
