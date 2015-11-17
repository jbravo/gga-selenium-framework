package com.ggasoftware.jdiuitest.web.selenium.elements.complex;

import com.ggasoftware.jdiuitest.core.interfaces.complex.IGroup;
import com.ggasoftware.jdiuitest.web.selenium.driver.WebDriverByUtils;
import com.ggasoftware.jdiuitest.web.selenium.elements.BaseElement;
import com.ggasoftware.jdiuitest.web.selenium.elements.CascadeInit;
import com.ggasoftware.jdiuitest.web.selenium.elements.base.Element;
import org.openqa.selenium.By;

import static com.ggasoftware.jdiuitest.core.settings.JDISettings.exception;
import static com.ggasoftware.jdiuitest.core.utils.common.EnumUtils.getEnumValue;


/**
 * Created by Roman_Iovlev on 7/8/2015.
 */
public class ElementsGroup<TEnum extends Enum, TType extends Element> extends BaseElement implements IGroup<TEnum, TType> {
    private Class<TType> clazz;

    public ElementsGroup() {
    }

    public ElementsGroup(Class<TType> clazz) {
        this.clazz = clazz;
    }

    public ElementsGroup(By byLocator, Class<TType> clazz) {
        super(byLocator);
        this.clazz = clazz;
    }

    public TType get(TEnum name) {
        return get(getEnumValue(name));
    }

    public TType get(String name, Class<TType> clazz) {
        this.clazz = clazz;
        return get(name);
    }

    public TType get(String name) {
        TType instance;
        try {
            instance = clazz.newInstance();
        } catch (IllegalAccessException | InstantiationException ex) {
            throw exception("Can't get instance of '%s' Element from Elements Group '%s'", name, toString());
        }
        instance.setAvatar(WebDriverByUtils.fillByTemplate(getLocator(), name), getAvatar());
        CascadeInit.InitElements(instance);
        return instance;
    }
}
