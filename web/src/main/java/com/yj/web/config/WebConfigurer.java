package com.yj.web.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.yj.web.aspect.WebLogAspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈自定义WebMVC配置类〉
 *
 * @author 欧俊俊
 * @create 2018/8/31
 */
@Component
@Configuration
public class WebConfigurer extends WebMvcConfigurerAdapter{
    private static final Logger logger = LoggerFactory.getLogger(WebLogAspect.class);

    @Autowired
    FitnessConfig fitnessConfig;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        logger.info("上传路径 = "+"file:"+fitnessConfig.getUploadPath());
        registry.addResourceHandler("/files/**").addResourceLocations("file:"+fitnessConfig.getUploadPath());
        //registry.addResourceHandler("/files/**").addResourceLocations("file:///D:/fitnessstatic/");
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);
        /*
        1.需要先定义一个convert转换消息的对象；
        2.添加fastjson的配置信息，比如是否要格式化返回的json数据
        3.在convert中添加配置信息
        4.将convert添加到converters中
         */
        //1.定义一个convert转换消息对象
        FastJsonHttpMessageConverter fastConverter=new FastJsonHttpMessageConverter();
        ////2.添加fastjson的配置信息，比如：是否要格式化返回json数据
        FastJsonConfig fastJsonConfig=new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(
                SerializerFeature.PrettyFormat
        );
        fastConverter.setFastJsonConfig(fastJsonConfig);
        converters.add(fastConverter);
    }

    @Bean//使用@Bean注入fastJsonHttpMessageConvert
    public HttpMessageConverters fastJsonHttpMessageConverters(){
        //1.需要定义一个Convert转换消息的对象
        FastJsonHttpMessageConverter fastConverter=new FastJsonHttpMessageConverter();
        //2.添加fastjson的配置信息，比如是否要格式化返回的json数据
//
        FastJsonConfig fastJsonConfig=new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
        //3.在convert中添加配置信息
        fastConverter.setFastJsonConfig(fastJsonConfig);

        HttpMessageConverter<?> converter=fastConverter;
        return new HttpMessageConverters(converter);
    }

    /**
     * 多模块的jsp访问，默认是src/main/webapp，但是多模块的目录只设置yml文件不行
     * @return
     */
    @Bean
    public InternalResourceViewResolver viewResolver() {

        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setViewClass(org.springframework.web.servlet.view.JstlView.class);
        // jsp目录
        resolver.setPrefix("/");
        // 后缀
        resolver.setSuffix(".html");
        return resolver;
    }
}
