package com.widestar.o2o.service;

import com.widestar.o2o.dto.ShopExecution;
import com.widestar.o2o.entity.Shop;

import java.io.File;

public interface ShopService {
  public ShopExecution addShop(Shop shop, File shopImg);
}
