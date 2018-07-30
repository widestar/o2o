package com.widestar.o2o.dao;

import static org.junit.Assert.assertEquals;

import com.widestar.o2o.BaseTest;
import com.widestar.o2o.entity.ShopCategory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TestShopCategoryDao extends BaseTest {
  @Autowired
  private ShopCategoryDao shopCategoryDao;
  @Test
  public void testQueryShopCategory(){
    List<ShopCategory> shopCategoryList=shopCategoryDao.queryShopCategory(new ShopCategory());
    assertEquals(2,shopCategoryList.size());
    ShopCategory testShopCategory=new ShopCategory();
    ShopCategory parentShopCategory=new ShopCategory();
    parentShopCategory.setShopCategoryId(1L);
    testShopCategory.setParent(parentShopCategory);
    shopCategoryList=shopCategoryDao.queryShopCategory(testShopCategory);
    assertEquals(1,shopCategoryList.size());
  }
}
