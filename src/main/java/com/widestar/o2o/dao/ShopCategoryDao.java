package com.widestar.o2o.dao;

import com.widestar.o2o.entity.ShopCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShopCategoryDao {
    List<ShopCategory> queryShopCategory(
        @Param("shopCategoryCondition") ShopCategory shopCategoryCondition);
}
