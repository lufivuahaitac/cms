/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.netbit.config;

/**
 *
 * @author Truong
 */
public class SystemConfig {

    public static int getReloadTime() {
        return Config.getSystemConfig().getInt("RELOAD_TIME", 10);
    }

}
