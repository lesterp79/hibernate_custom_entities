/*
 * TestAddCustomFields.java
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

import java.io.Serializable;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class TestAddCustomFields {
    private static final String TEST_FIELD_NAME = "encryptedEmail";
    private static final String TEST_VALUE = "testencrypted@test.com";

    public static void main(String[] args) {
        HibernateUtil.getInstance().getCurrentSession();


        Session session = HibernateUtil.getInstance().getCurrentSession();

        Transaction tx = session.beginTransaction();
        try {

            CustomerEntity contact = new CustomerEntity();
            contact.setCompanyname("Client 1");
            contact.setContactname("lpino");
            contact.setValueOfCustomField(TEST_FIELD_NAME, TEST_VALUE);
            Serializable id = session.save(contact);
            tx.commit();

            contact = (CustomerEntity) session.get(CustomerEntity.class, id);
            Object value = contact.getValueOfCustomField(TEST_FIELD_NAME);
            System.out.println("value = " + value);

        } catch (Exception e) {
            tx.rollback();
            System.out.println("e = " + e);
        }
    }
}
