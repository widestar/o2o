package com.widestar.o2o.service.impl;

import com.widestar.o2o.dao.ShopDao;
import com.widestar.o2o.dto.ShopExecution;
import com.widestar.o2o.entity.Shop;
import com.widestar.o2o.enums.ShopStateEnum;
import com.widestar.o2o.exceptions.ShopOperationException;
import com.widestar.o2o.service.ShopService;
import com.widestar.o2o.util.ImageUtil;
import com.widestar.o2o.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.Date;
@Service
public class ShopServiceImpl implements ShopService {
  @Autowired
  private ShopDao shopDao;
  /**
   * 使用注解控制事务方法的优点： 1.开发团队达成一致约定，明确标注事务方法的编程风格
   * 2.保证事务方法的执行时间尽可能短，不要穿插其他网络操作，RPC/HTTP请求或者剥离到事务方法外部
   * 3.不是所有的方法都需要事务，如只有一条修改操作，只读操作不需要事务控制
   */
  @Override
  @Transactional//支持事务的注解
  public ShopExecution addShop(Shop shop, File shopImg) {
    //空值判断
    if (shop == null) {
      return new ShopExecution(ShopStateEnum.NULL_SHOP);
    }
    try {
      //给店铺信息赋初始值
      shop.setEnableStatus(0);
      shop.setCreateTime(new Date());
      shop.setLastEditTime(new Date());
      //添加店铺信息
      int effectedNum = shopDao.insertShop(shop);
      if (effectedNum <= 0) {
        throw new ShopOperationException("店铺创建失败");
      } else {
        if(shopImg!=null){
          //存储图片
          try{
            addShopImg(shop,shopImg);
          }catch (Exception e){
            throw new ShopOperationException("add shop error:" + e.getMessage());
          }
          //更新店铺的图片地址
          effectedNum=shopDao.updateShop(shop);
          if (effectedNum <= 0) {
            throw new ShopOperationException("更新图片地址失败");
          }
        }
      }
    } catch (Exception e) {
      throw new ShopOperationException("add shop error:" + e.getMessage());
    }
    return new ShopExecution(ShopStateEnum.CHECK,shop);
  }

  private void addShopImg(Shop shop, File shopImg) {
    //获取shop图片目录的相对值路径
    String dest=PathUtil.getShopImagePath(shop.getShopId());
    String shopImgAddr=ImageUtil.generateThumbnail(shopImg,dest);
    shop.setShopImg(shopImgAddr);
  }
}
