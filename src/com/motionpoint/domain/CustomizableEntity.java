/*
 * CustomizableEntity.java
 *
 * Copyright (c) 2000-2019 MotionPoint Corporation. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * MotionPoint Corp. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with MotionPoint.
 */
package com.motionpoint.domain;

import java.util.Map;
import java.util.HashMap;
import javax.persistence.Transient;

public abstract class CustomizableEntity {
    @Transient
    private Map customProperties;

    public Map getCustomProperties() {
        if (customProperties == null)
            customProperties = new HashMap();
        return customProperties;
    }

    public void setCustomProperties(Map customProperties) {
        this.customProperties = customProperties;
    }

    public Object getValueOfCustomField(String name) {
        return getCustomProperties().get(name);
    }

    public void setValueOfCustomField(String name, Object value) {
        getCustomProperties().put(name, value);
    }

}