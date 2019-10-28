/*
 * TestCustomQuery.java
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

import org.hibernate.Session;

public class TestQueryCustomFields {
    public static void main(String[] args) {
        Session session = HibernateUtil.getInstance().getCurrentSession();
        boolean found = DatabaseSchemaExtractor.isColumnPresent(session, "CUSTOMER", "fld_encryptedEmail");
        CustomerDAO dao = new CustomerDAO(found);
        System.out.println("list.size() = " + dao.getCustomerByEmail(session, "testencrypted@test.com"));
    }
}