package com.widestar.o2o.dao;

import static org.junit.Assert.assertEquals;

import com.widestar.o2o.BaseTest;
import com.widestar.o2o.entity.Area;
import com.widestar.o2o.entity.PersonInfo;
import com.widestar.o2o.entity.Shop;
import com.widestar.o2o.entity.ShopCategory;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class TestShopDao extends BaseTest {
  @Autowired
  private ShopDao shopDao;
  @Test
  @Ignore
  public void testInsertShop(){
    Shop shop=new Shop();
    Area area=new Area();
    ShopCategory shopCategory=new ShopCategory();
    PersonInfo owner=new PersonInfo();
    area.setAreaId(2);
    owner.setUserId(1L);
    shopCategory.setShopCategoryId(1L);
    shop.setArea(area);
    shop.setShopCategory(shopCategory);
    shop.setOwner(owner);
    shop.setShopName("测试的店铺");
    shop.setShopDesc("test");
    shop.setShopAddr("test");
    shop.setPhone("test");
    shop.setShopImg("test");
    shop.setPriority(1);
    shop.setCreateTime(new Date());
    shop.setEnableStatus(1);
    shop.setAdvice("审核中");
    int effectedNum=shopDao.insertShop(shop);
    assertEquals(1,effectedNum);
  }
  @Test
  public void testUpdateShop(){
    /*Shop shop=new Shop();
    Area area=new Area();
    ShopCategory shopCategory=new ShopCategory();
    PersonInfo owner=new PersonInfo();
    area.setAreaId(2);
    owner.setUserId(1L);*/
    /*shopCategory.setShopCategoryId(1L);
    shop.setArea(area);
    shop.setShopCategory(shopCategory);
    shop.setOwner(owner);
    shop.setShopName("测试的店铺");
    shop.setShopDesc("test");
    shop.setShopAddr("test");
    shop.setPhone("test");
    shop.setShopImg("test");
    shop.setPriority(1);
    shop.setCreateTime(new Date());
    shop.setEnableStatus(1);
    shop.setAdvice("审核中");*/
    Shop shop=new Shop();
    shop.setShopId(1L);
    shop.setShopName("刘泽广的店铺1111！");
    shop.setShopAddr("北京邮电大学！");
    shop.setLastEditTime(new Date());
    int effectedNum=shopDao.updateShop(shop);
    assertEquals(1,effectedNum);
  }
}
