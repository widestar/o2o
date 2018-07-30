package com.widestar.o2o.service.impl;

import com.widestar.o2o.dao.ShopCategoryDao;
import com.widestar.o2o.entity.ShopCategory;
import com.widestar.o2o.service.ShopCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ShopCategoryServiceImpl implements ShopCategoryService {
  @Autowired
  private ShopCategoryDao shopCategoryDao;
  @Override
  public List<ShopCategory> getShopCategoryList(ShopCategory shopCategoryCondition) {

    return shopCategoryDao.queryShopCategory(shopCategoryCondition);
  }
}
