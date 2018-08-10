package com.widestar.o2o.service;

import com.widestar.o2o.dto.ShopExecution;
import com.widestar.o2o.entity.Shop;
import com.widestar.o2o.exceptions.ShopOperationException;

import java.io.InputStream;

public interface ShopService {
  ShopExecution addShop(Shop shop, InputStream shopImgInputStream, String fileName)
      throws ShopOperationException;
  Shop getShopById(Long shopId);
  ShopExecution modifyShop(Shop shop, InputStream shopImgInputStream,String fileName)
      throws ShopOperationException;
  ShopExecution getShopList(Shop shopCondition,int pageIndex,int pageSize);
}
