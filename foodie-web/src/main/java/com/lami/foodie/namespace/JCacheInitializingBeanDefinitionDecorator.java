package com.lami.foodie.namespace;

import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.xml.BeanDefinitionDecorator;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Node;

/**
 * Created by xjk on 1/14/18.
 */
public class JCacheInitializingBeanDefinitionDecorator implements BeanDefinitionDecorator {


    public BeanDefinitionHolder decorate(Node node, BeanDefinitionHolder definition, ParserContext parserContext) {
        return null;
    }

    private String registerJCacheInitializer(Node source, ParserContext ctx){

        return null;
    }

}
