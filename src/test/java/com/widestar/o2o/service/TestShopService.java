package com.widestar.o2o.service;

import static org.junit.Assert.assertEquals;

import com.widestar.o2o.BaseTest;
import com.widestar.o2o.dto.ShopExecution;
import com.widestar.o2o.entity.Area;
import com.widestar.o2o.entity.PersonInfo;
import com.widestar.o2o.entity.Shop;
import com.widestar.o2o.entity.ShopCategory;
import com.widestar.o2o.enums.ShopStateEnum;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;

public class TestShopService extends BaseTest {
  @Autowired
  private ShopService shopService;
  @Test
  public void testAddShop() throws FileNotFoundException {
    Shop shop=new Shop();
    PersonInfo owner=new PersonInfo();
    Area area=new Area();
    ShopCategory shopCategory=new ShopCategory();
    owner.setUserId(1L);
    area.setAreaId(2);
    shopCategory.setShopCategoryId(1L);
    shop.setOwner(owner);
    shop.setArea(area);
    shop.setShopCategory(shopCategory);
    shop.setShopName("高丽娜的店铺2");
    shop.setShopDesc("lina");
    shop.setShopAddr("北京交通大学2");
    shop.setPhone("181419286552");
    shop.setCreateTime(new Date());
    shop.setEnableStatus(ShopStateEnum.CHECK.getState());
    shop.setAdvice("审核中");
    File shopImg=new File("/Users/didi/widestar/pictures/jing.jpg");
    InputStream is=new FileInputStream(shopImg);
    ShopExecution se=shopService.addShop(shop,is,shopImg.getName());
    assertEquals(ShopStateEnum.CHECK.getState(),se.getState());
  }
}
