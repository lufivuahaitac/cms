/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.netbit.cms;

import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Truong
 */
@Controller
public class AdminController {
    private static final Logger logger = LogManager.getLogger(AdminController.class);
    @RequestMapping(value = "/admin")
    public String admin(Model model,
            HttpServletRequest request) {
        return "admin/dashboard";
    }
    
    @RequestMapping(value = "/admin/newpost")
    public String merchantDeniedDetail(Model model,
            HttpServletRequest request) {
        return "admin/newpost";
    }

}
