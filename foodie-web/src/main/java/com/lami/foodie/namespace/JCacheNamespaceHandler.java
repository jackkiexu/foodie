package com.lami.foodie.namespace;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * Created by xjk on 1/14/18.
 */
public class JCacheNamespaceHandler extends NamespaceHandlerSupport{

    public void init() {
        super.registerBeanDefinitionDecoratorForAttribute("cache-name",
                new JCacheInitializingBeanDefinitionDecorator());
    }
}
