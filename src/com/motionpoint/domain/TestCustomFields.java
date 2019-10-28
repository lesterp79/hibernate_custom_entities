/*
 * TestCustomFields.java
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

public class TestCustomFields {
    private static final String TEST_FIELD_NAME = "ENCRYPTEDEMAIL";
    private static final String TEST_VALUE = "testencrypted@test.com";

    public static void main(String[] args) {

        Session session = HibernateUtil.getInstance().getCurrentSession();

        Transaction tx = session.beginTransaction();
        try {

            CustomerDAO customerDAO = new CustomerDAO(true);

            CustomerEntity contact = new CustomerEntity();
            contact.setCompanyname("Client 1");
            contact.setContactname("lpino");
            customerDAO.setEmail(contact, TEST_VALUE);
            Serializable id = session.save(contact);
            tx.commit();

            contact = (CustomerEntity) session.get(CustomerEntity.class, id);

            Object value = customerDAO.getEmail(contact);

            System.out.println("value = " + value);

        } catch (Exception e) {
            tx.rollback();
            System.out.println("e = " + e);
        }
    }
}
