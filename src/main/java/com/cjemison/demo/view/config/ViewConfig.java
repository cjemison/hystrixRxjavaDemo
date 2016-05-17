package com.cjemison.demo.view.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by cjemison on 5/17/16.
 */
@Configuration
@EnableAsync
public class ViewConfig extends WebMvcConfigurerAdapter {

}
