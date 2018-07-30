package com.widestar.o2o.dao;

import com.widestar.o2o.BaseTest;
import com.widestar.o2o.entity.Area;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TestAreaDao extends BaseTest {
  @Autowired
  private AreaDao areaDao;
  @Test
  public void testQueryArea(){
    List<Area> areaList=areaDao.queryArea();
//    System.out.println(areaList);
    assertEquals(5,areaList.size());
  }
}
