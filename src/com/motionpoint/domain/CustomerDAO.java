/*
 * CustomerDAO.java
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

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;


public class CustomerDAO {

    private static final String TEST_FIELD_NAME = "ENCRYPTEDEMAIL";
    private boolean isEmailEncrypted;

    public CustomerDAO(boolean aIsEmailEncrypted) {
        isEmailEncrypted = aIsEmailEncrypted;
    }

    public String getEmail(CustomerEntity aCustomerEntity) {
        if (isEmailEncrypted) {
            return (String) aCustomerEntity.getValueOfCustomField("encryptedEmail");
        } else {
            return aCustomerEntity.getEmail();
        }
    }

    public void setEmail(CustomerEntity aCustomerEntity, String email) {
        if (isEmailEncrypted) {
            aCustomerEntity.setValueOfCustomField(TEST_FIELD_NAME, email);
        } else {
            aCustomerEntity.setEmail(email);
        }
    }

    public List<CustomerEntity> getCustomerByEmail(Session aSession, String email) {
        Criteria criteria = aSession.createCriteria(CustomerEntity.class);
        criteria.add(Restrictions.eq(getEmailPropertyName(), email));
        List list = criteria.list();
        return list;
    }

    private String getEmailPropertyName() {
        return CustomizableEntityManager.CUSTOM_COMPONENT_NAME + (isEmailEncrypted ? ".encryptedEmail" : "email");
    }

}
