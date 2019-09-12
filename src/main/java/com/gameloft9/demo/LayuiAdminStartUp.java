package com.gameloft9.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import java.util.HashSet;
import java.util.Set;

/**
 * boot 启动类
 * Created by gameloft9 on 2019/9/12.
 */
public class LayuiAdminStartUp {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(LayuiAdminStartUp.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.setWebApplicationType(WebApplicationType.SERVLET);
        Set<String> sources = new HashSet<>();
        sources.add("classpath:applicationContext.xml");
        app.setSources(sources);

        app.run(args);
    }

    private static SpringApplicationBuilder configSpringEnv(SpringApplicationBuilder builder){
        return builder.sources(LayuiAdminStartUp.class);
    }

    @Value("${server.port}")
    private int port;

    @Bean
    public ServletWebServerFactory servletContainer() {
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
        tomcat.addInitializers(new ServletContextInitializer(){
            @Override
            public void onStartup(ServletContext servletContext) throws ServletException {
                XmlWebApplicationContext context = new XmlWebApplicationContext();
                context.setConfigLocations(new String[]{"applicationContext-springmvc.xml"});
                DispatcherServlet dispatcherServlet = new DispatcherServlet(context);
                ServletRegistration.Dynamic dispatcher = servletContext
                        .addServlet("dispatcher", dispatcherServlet);

                dispatcher.setLoadOnStartup(1);
                dispatcher.addMapping("/");
            }
        });
        tomcat.setContextPath("/manager");
        tomcat.addTldSkipPatterns("xercesImpl.jar","xml-apis.jar","serializer.jar");
        tomcat.setPort(port);

        return tomcat;
    }

    @Bean
    public FilterRegistrationBean shiroFilter(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(
                new org.springframework.web.filter.DelegatingFilterProxy());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.setName("springSessionRepositoryFilter");
        filterRegistrationBean.addInitParameter("targetFilterLifecycle","true");

        return filterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean CharacterEncodingFilter(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(
                new org.springframework.web.filter.CharacterEncodingFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("encoding","UTF-8");
        filterRegistrationBean.addInitParameter("forceEncoding","true");
        return filterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean userFilter(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(
                new com.gameloft9.demo.mgrframework.filters.UserFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }

    @Bean
    public ServletListenerRegistrationBean IntrospectorCleanupListener(){
        ServletListenerRegistrationBean servletListenerRegistrationBean = new ServletListenerRegistrationBean();
        servletListenerRegistrationBean.setListener(new org.springframework.web.util.IntrospectorCleanupListener());
        return servletListenerRegistrationBean;
    }

}
