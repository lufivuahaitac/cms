///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package vn.netbit.config;
//
//import java.util.ArrayList;
//import java.util.List;
//import org.apache.commons.configuration.HierarchicalConfiguration;
//
///**
// *
// * @author Tran Ba Y
// */
//public class XmlConfig {
//
//    
//    
//    /**
//     * Get merchant information via merchantCode
//     *
//     * @return ResponseCode
//     */
//    public static List<String> findDeniedBean() {
//        List<String> beans = new ArrayList<String>();
//        List<HierarchicalConfiguration> x = Config.getXmlConfig().configurationsAt("tables.menu");
//        for (HierarchicalConfiguration sub : x) {
//           beans.add(sub.getString("key"));
//        }
//        return beans;
//    }
//    
//}
