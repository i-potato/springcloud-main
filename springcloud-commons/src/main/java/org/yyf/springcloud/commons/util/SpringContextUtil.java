package org.yyf.springcloud.commons.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 获取IOC容器 ApplicationContext 
 * 可在线程中使用容器中的Bean
 * @author yyf
 * 
 */

@Component
public class SpringContextUtil implements ApplicationContextAware{
	/**
	 *  Spring应用上下文环境
	 */
	private static ApplicationContext applicationContext; 

	/**
	 * 重写setApplicationContext 获取IOC容器
	 */
	@Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
          SpringContextUtil.applicationContext = applicationContext;
    }



    public static ApplicationContext getApplicationContext() {
           return applicationContext;
    }

    
    /**
     * 按名称获取Bean
     * @param name
     * @return
     * @throws BeansException
     */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String name) throws BeansException {
		return (T) applicationContext.getBean(name);
	}
	
	/**
	 * 按类型获取Bean
	 * @param name
	 * @return
	 * @throws BeansException
	 */
	public static <T> T getBean(Class<T> name) throws BeansException {
		return (T) applicationContext.getBean(name);
	}
}
