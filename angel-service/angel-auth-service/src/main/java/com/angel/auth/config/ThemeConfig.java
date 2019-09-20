//package com.angel.auth.config;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.ui.context.support.ResourceBundleThemeSource;
//import org.springframework.web.servlet.theme.FixedThemeResolver;
//
//@Configuration
//public class ThemeConfig{
//
//    /*
//     * 在application.properties中指定主题配置文件（静态配置）
//     */
//	@Value("${theme}")
//	private String theme;
//
//
//	/*
//     * 指定主题配置文件前缀，例如下文：theme-
//     */
//	@Bean
//	public ResourceBundleThemeSource themeSource(){
//		ResourceBundleThemeSource ResourceBundleThemeSource = new ResourceBundleThemeSource();
//		ResourceBundleThemeSource.setBasenamePrefix("theme-");
//		ResourceBundleThemeSource.setDefaultEncoding("utf-8");
//		return ResourceBundleThemeSource;
//	}
//
//    /*
//     * 将读取到的静态配置写入FixedThemeResolver（springMVC固定模板）
//     */
//	@Bean
//	public FixedThemeResolver themeResolver(){
//		FixedThemeResolver fixedThemeResolver = new FixedThemeResolver();
//		fixedThemeResolver.setDefaultThemeName(theme);
//		return fixedThemeResolver;
//	}
//}