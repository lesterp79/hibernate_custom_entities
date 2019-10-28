/*
 * TestRemoveCustomFields.java
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

public class TestRemoveCustomFields {
    public static void main(String[] args) {
        HibernateUtil.getInstance().getCurrentSession();

        CustomizableEntityManager contactEntityManager = new CustomizableEntityManagerImpl(CustomerEntity.class);

        contactEntityManager.removeCustomField("encryptedEmail");

        Session session = HibernateUtil.getInstance().getCurrentSession();

        Transaction tx = session.beginTransaction();
        try {

            CustomerEntity contact = new CustomerEntity();
            contact.setCompanyname("Client 2");
            contact.setContactname("lpino2");
            Serializable id = session.save(contact);
            tx.commit();

            contact = (CustomerEntity) session.get(CustomerEntity.class, id);
            System.out.println("value = " + contact.getCompanyname());

        } catch (Exception e) {
            tx.rollback();
            System.out.println("e = " + e);
        }
    }
}

