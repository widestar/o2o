package com.widestar.o2o.service.impl;

import com.widestar.o2o.dao.ShopDao;
import com.widestar.o2o.dto.ShopExecution;
import com.widestar.o2o.entity.Shop;
import com.widestar.o2o.enums.ShopStateEnum;
import com.widestar.o2o.exceptions.ShopOperationException;
import com.widestar.o2o.service.ShopService;
import com.widestar.o2o.util.ImageUtil;
import com.widestar.o2o.util.PageCalculator;
import com.widestar.o2o.util.PathUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

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
  public ShopExecution addShop(Shop shop, InputStream shopImgInputStream, String fileName)
      throws ShopOperationException {
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
        if (shopImgInputStream != null) {
          //存储图片
          try {
            addShopImg(shop, shopImgInputStream, fileName);
          } catch (Exception e) {
            throw new ShopOperationException("add shop error:" + e.getMessage());
          }
          //更新店铺的图片地址
          effectedNum = shopDao.updateShop(shop);
          if (effectedNum <= 0) {
            throw new ShopOperationException("更新图片地址失败");
          }
        }
      }
    } catch (Exception e) {
      throw new ShopOperationException("add shop error:" + e.getMessage());
    }
    return new ShopExecution(ShopStateEnum.CHECK, shop);
  }

  @Override
  public Shop getShopById(Long shopId) {
    return shopDao.getShopById(shopId);
  }

  @Override
  public ShopExecution modifyShop(Shop shop, InputStream shopImgInputStream, String fileName)
      throws ShopOperationException {
    if (shop == null || shop.getShopId() == null) {
      return new ShopExecution(ShopStateEnum.NULL_SHOP);
    } else {
      //判断是否需要处理图片
      try {
        if (shopImgInputStream != null && StringUtils.isNotBlank(fileName)) {
          Shop shopTmp = shopDao.getShopById(shop.getShopId());
          if (shopTmp.getShopImg() != null) {
            ImageUtil.deleteFileOrPath(shopTmp.getShopImg());
          }
          addShopImg(shop, shopImgInputStream, fileName);
        }
        //更新店铺信息
        shop.setLastEditTime(new Date());
        int effectedNum = shopDao.updateShop(shop);
        if (effectedNum <= 0) {
          return new ShopExecution(ShopStateEnum.INNER_ERROR);
        } else {
          shop = shopDao.getShopById(shop.getShopId());
          return new ShopExecution(ShopStateEnum.SUCCESS, shop);
        }
      } catch (Exception e) {
        throw new ShopOperationException("modify shop error:" + e.getMessage());
      }
    }
  }

  @Override
  public ShopExecution getShopList(Shop shopCondition, int pageIndex, int pageSize) {
    int rowIndex = PageCalculator.calculateRowIndex(pageIndex, pageSize);
    ShopExecution shopExecution = new ShopExecution();
    List<Shop> shopList=shopDao.getShopList(shopCondition,pageIndex,pageSize);
    int shopCount=shopDao.getShopCount(shopCondition);
    if(shopList!=null){
      shopExecution.setShopList(shopList);
      shopExecution.setCount(shopCount);
    }else{
      shopExecution.setState(ShopStateEnum.NULL_SHOP.getState());
    }
    return shopExecution;
  }

  private void addShopImg(Shop shop, InputStream shopImgInputStream, String fileName) {
    //获取shop图片目录的相对值路径
    String dest = PathUtil.getShopImagePath(shop.getShopId());
    String shopImgAddr = ImageUtil.generateThumbnail(shopImgInputStream, fileName, dest);
    shop.setShopImg(shopImgAddr);
  }
}
