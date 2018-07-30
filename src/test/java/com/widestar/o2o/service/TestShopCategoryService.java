package com.widestar.o2o.service;

import static org.junit.Assert.assertEquals;

import com.widestar.o2o.BaseTest;
import com.widestar.o2o.entity.ShopCategory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TestShopCategoryService extends BaseTest {
  @Autowired
  ShopCategoryService shopCategoryService;
  @Test
  public void testGetShopCategoryList(){
    List<ShopCategory> shopCategoryList=shopCategoryService.getShopCategoryList(new ShopCategory());
    assertEquals(2,shopCategoryList.size());
    ShopCategory testShopCategory=new ShopCategory();
    ShopCategory parentShopCategory=new ShopCategory();
    parentShopCategory.setShopCategoryId(1L);
    testShopCategory.setParent(parentShopCategory);
    shopCategoryList=shopCategoryService.getShopCategoryList(testShopCategory);
    assertEquals(1,shopCategoryList.size());
  }
}
