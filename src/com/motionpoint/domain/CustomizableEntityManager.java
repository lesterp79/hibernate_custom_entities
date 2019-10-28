/*
 * CustomizableEntityManager.java
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

import org.hibernate.mapping.Component;

public interface CustomizableEntityManager {
    String CUSTOM_COMPONENT_NAME = "customProperties";

    void addCustomField(String name);

    void removeCustomField(String name);

    Component getCustomProperties();

    Class getEntityClass();
}
