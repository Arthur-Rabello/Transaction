package com.example.system_t.helper;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;

@Component
public class NullAwareBeanUtilsBeans extends BeanUtilsBean {
    @Override
    public void copyProperty(Object dest, String name, Object value) throws InvocationTargetException, IllegalAccessException {
        if (value == null) return;
        super.copyProperty(dest, name, value);
    }
}
