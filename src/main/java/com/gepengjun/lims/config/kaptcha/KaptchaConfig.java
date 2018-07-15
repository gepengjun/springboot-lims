package com.gepengjun.lims.config.kaptcha;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.servlet.KaptchaServlet;
import com.google.code.kaptcha.util.Config;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Properties;


@Component
public class KaptchaConfig {

    @Bean
    public DefaultKaptcha myKaptcha(){
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        Properties properties = new Properties();
        properties.setProperty(Constants.KAPTCHA_BORDER,"no");
        properties.setProperty(Constants.KAPTCHA_IMAGE_WIDTH,"200");
        properties.setProperty(Constants.KAPTCHA_IMAGE_HEIGHT,"80");
        properties.setProperty(Constants.KAPTCHA_SESSION_CONFIG_KEY,Constants.KAPTCHA_SESSION_KEY);
        properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_FONT_COLOR,"green");
        properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_FONT_SIZE,"55");
        properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_CHAR_LENGTH,"4");
        properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_CHAR_SPACE,"5");
        properties.setProperty(Constants.KAPTCHA_OBSCURIFICATOR_IMPL,"com.google.code.kaptcha.impl.ShadowGimpy");
        Config config = new Config(properties);
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }
}
