package com.widestar.o2o.dao;

import com.widestar.o2o.entity.Shop;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShopDao {
  /**
   * 新增店铺
   * @param shop
   * @return
   */
  int insertShop(Shop shop);

  /**
   * 更新店铺信息
   * @param shop
   * @return
   */
  int updateShop(Shop shop);

  /**
   * 查询店铺信息
   * @param shopId
   * @return
   */
  Shop getShopById(Long shopId);

  List<Shop> getShopList(@Param("shopCondition") Shop shopCondition,
    @Param("rowIndex") int pageIndex,
    @Param("pageSize") int pageSize
  );

  int getShopCount(@Param("shopCondition") Shop shopCondition);
}
