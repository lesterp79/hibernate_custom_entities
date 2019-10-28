package com.motionpoint.domain;/*
 * TestCustomEntities.java
 *
 * Copyright (c) 2000-2019 MotionPoint Corporation. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * MotionPoint Corp. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with MotionPoint.
 */

import java.io.Serializable;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class TestCustomEntitiesUpdateXML {
    private static final String TEST_FIELD_NAME = "ENCRYPTEDEMAIL";
    private static final String TEST_VALUE = "testencrypted@test.com";

    public static void main(String[] args) {

        Session session = HibernateUtil.getInstance().getCurrentSession();

        boolean found = DatabaseSchemaExtractor.isColumnPresent(session, "CUSTOMER", "ENCRYPTEDEMAIL");

        CustomizableEntityManager contactEntityManager = new CustomizableEntityManagerImpl(CustomerEntity.class);

        if (found) {
            contactEntityManager.addCustomField(TEST_FIELD_NAME);
        }

        session = HibernateUtil.getInstance().getCurrentSession();

        Transaction tx = session.beginTransaction();
        try {

            CustomerDAO customerDAO = new CustomerDAO(found);

            CustomerEntity customer = new CustomerEntity();
            customer.setId(3L);
            customer.setCompanyname("Client 3");
            customer.setContactname("lpino3");
            customerDAO.setEmail(customer, TEST_VALUE);
            Serializable id = session.save(customer);
            tx.commit();

            customer = (CustomerEntity) session.get(CustomerEntity.class, id);

            Object value = customerDAO.getEmail(customer);

            System.out.println("value = " + value);

        } catch (Exception e) {
            tx.rollback();
            System.out.println("e = " + e);
        }
    }
}