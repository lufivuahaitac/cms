/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.netbit.config;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;
import vn.netbit.threads.ThreadPoolManager;

/**
 *
 * @author YTB
 */
public class Config extends HttpServlet {

    private static Logger logger;
    private static final long serialVersionUID = 1L;
    private static String log4j2Path;

    private static PropertiesConfiguration systemConfig;

    private static XMLConfiguration xmlConfig;

    public static PropertiesConfiguration getSystemConfig() {
        return systemConfig;
    }

    public static void setSystemConfig(PropertiesConfiguration systemConfig) {
        Config.systemConfig = systemConfig;
    }
//
//    public static XMLConfiguration getXmlConfig() {
//        return xmlConfig;
//    }
//
//    public static void setXmlConfig(XMLConfiguration xmlConfig) {
//        Config.xmlConfig = xmlConfig;
//    }

    public static String prefix;

    @Override
    public void init() throws ServletException {
        initConfig();
        InitConfig reload = new InitConfig();
        ThreadPoolManager.getInstance().scheduleGeneralAtFixedRate(reload, 10, 10);
    }

    /**
     * initConfig
     */
    public void initConfig() {
        prefix = getServletContext().getRealPath("/");
        log4j2Path = prefix + getInitParameter("log4j2-init-file");

        try {
            logger();

            String configPath = prefix + getInitParameter("Config");
            systemConfig = new PropertiesConfiguration(configPath);
            systemConfig.setReloadingStrategy(new FileChangedReloadingStrategy());

//            String xmlConfigPath = prefix + getInitParameter("XmlConfig");
//            xmlConfig = new XMLConfiguration(xmlConfigPath);
//            FileChangedReloadingStrategy xmlConfigStrategy = new FileChangedReloadingStrategy();
//            xmlConfigStrategy.setConfiguration(xmlConfig);
//            xmlConfigStrategy.init();
//            xmlConfig.setReloadingStrategy(new FileChangedReloadingStrategy());
            
        } catch (IOException e) {
            logger.error("Load file config failed ex: {}.", e);

        } catch (ConfigurationException e) {
            logger.error("Load file config failed ex: {}.", e);
        }
    }
    public static String getPath() {
        return prefix;
    }

 
    /**
     *
     * @throws IOException
     */
    private static void logger() throws IOException {
        String configuration = log4j2Path;
        URI source = new File(configuration).toURI();
        Configurator.initialize("contextLog4J", null, source);
        logger = LogManager.getLogger(Config.class);
    }

    @Override
    public void destroy() {
        ThreadPoolManager.getInstance().shutdown();
    }
    
    class InitConfig implements Runnable  {

        @Override
        public void run() {
            initConfig();
        }
    
    }

}
