package com.widestar.o2o.web.shopadmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/o2o/shopadmin",method = RequestMethod.GET)
public class ShopAdminController {
  //WEB-INF/html/shop/shopoperation.html
  @RequestMapping(value = "/shopoperation")
  public String shopOperation(){
    return "shop/shopoperation";
  }

}
