package com.widestar.o2o.service;

import static org.junit.Assert.assertEquals;

import com.widestar.o2o.BaseTest;
import com.widestar.o2o.entity.Area;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TestAreaService extends BaseTest {
  @Autowired
  private AreaService areaService;
  @Test
  public void testGetAreaList(){
    List<Area> areaList=areaService.getAreaList();
    assertEquals("北京",areaList.get(0).getAreaName());
  }
}
