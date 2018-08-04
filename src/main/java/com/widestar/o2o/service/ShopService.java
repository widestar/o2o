package com.widestar.o2o.service;

import com.widestar.o2o.dto.ShopExecution;
import com.widestar.o2o.entity.Shop;

import java.io.InputStream;

public interface ShopService {
  ShopExecution addShop(Shop shop, InputStream shopImgInputStream, String fileName);
}
