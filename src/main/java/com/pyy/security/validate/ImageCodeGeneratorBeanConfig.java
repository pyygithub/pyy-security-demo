package com.pyy.security.validate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 图形验证码生成器Bean配置类
 * @author: pyygithub
 * @date: 2018-07-25 19:22
 * @version: v1.0
 */
@Configuration
public class ImageCodeGeneratorBeanConfig {

    @Autowired
    private ImageCodeProperties imageCodeProperties;

    @Bean
    @ConditionalOnMissingBean(name = "imageCodeGenerator")
    public ImageCodeGenerator imageCodeGenerator() {
        GenericImageCodeGenerator imageCodeGenerator = new GenericImageCodeGenerator();
        imageCodeGenerator.setImageCodeProperties(imageCodeProperties);
        return imageCodeGenerator;
    }
}
