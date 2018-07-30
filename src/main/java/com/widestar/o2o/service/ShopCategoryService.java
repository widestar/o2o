package com.widestar.o2o.service;

import com.widestar.o2o.entity.ShopCategory;

import java.util.List;

public interface ShopCategoryService {
  List<ShopCategory> getShopCategoryList(ShopCategory shopCategoryCondition);
}
