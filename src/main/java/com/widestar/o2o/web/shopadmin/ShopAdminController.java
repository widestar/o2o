package com.widestar.o2o.web.shopadmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/o2o/shopadmin",method = RequestMethod.GET)
public class ShopAdminController {
  //WEB-INF/html/shop/shopoperation.html
  @RequestMapping(value = "/shopoperation")
  public String shopOperation(){
    return "shop/shopoperation";
  }
  @RequestMapping(value = "/shoplist")
  public String shopList(){
    return "shop/shoplist";
  }
  @RequestMapping(value = "/shopmanagement")
  public String shopManagement(){
    return "shop/shopmanagement";
  }

}
