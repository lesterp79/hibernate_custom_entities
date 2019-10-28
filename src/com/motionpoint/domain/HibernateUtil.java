/*
 * HibernateUtil.java
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

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import javax.persistence.Entity;
import org.apache.commons.logging.Log;
import org.dom4j.Document;
import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.mapping.PersistentClass;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateUtil {

    private static HibernateUtil instance;
    private Configuration configuration;
    private SessionFactory sessionFactory;
    private Session session;

    public synchronized static HibernateUtil getInstance() {
        if (instance == null) {
            instance = new HibernateUtil();
        }
        return instance;
    }

    private synchronized SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            //            sessionFactory = getConfiguration().buildSessionFactory();
            // Hibernate 4

            ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(getConfiguration().getProperties())
                            .buildServiceRegistry();
            sessionFactory = getConfiguration().buildSessionFactory(serviceRegistry);
            return sessionFactory;
        }
        return sessionFactory;
    }

    public synchronized Session getCurrentSession() {
        if (session == null) {
            session = getSessionFactory().openSession();
            session.setFlushMode(FlushMode.COMMIT);
            System.out.println("session opened.");
        }
        return session;
    }

    private synchronized Configuration getConfiguration() {
        if (configuration == null) {
            System.out.print("configuring Hibernate ... ");
            try {
                // Hibernate 4
                configuration = new Configuration();
                configuration.configure();
                System.out.println("ok");
            } catch (HibernateException e) {
                System.out.println("failure");
                e.printStackTrace();
            }
        }
        return configuration;
    }

    public void reset() {
        Session session = getCurrentSession();
        if (session != null) {
            session.flush();
            if (session.isOpen()) {
                System.out.print("closing session ... ");
                session.close();
                System.out.println("ok");
            }
        }
        SessionFactory sf = getSessionFactory();
        if (sf != null) {
            System.out.print("closing session factory ... ");
            sf.close();
            System.out.println("ok");
        }
        this.configuration = null;
        this.sessionFactory = null;
        this.session = null;
    }

    public PersistentClass getClassMapping(Class entityClass) {
        return getConfiguration().getClassMapping(entityClass.getName());
    }

    private static final class DynamicConfiguration extends Configuration {

        private static final long serialVersionUID = 2859578760267201365L;

        private List<String> packages = new ArrayList<String>();

        @Override
        public Configuration addPackage(String packageName) throws MappingException {
            if (!packages.contains(packageName)) {
                packages.add(packageName);
            }
            return this;
            //return super.addPackage(packageName);
        }

        @Override
        protected Configuration doConfigure(Document doc) throws HibernateException {
            Configuration configuration = super.doConfigure(doc);

            for (String packageName : packages) {
                addVoClasses(packageName);
            }

            return configuration;
        }

        private void addVoClasses(String packageName) {

            String loadFrom = packageName.replace(".", "/");
            URL packageUrl = getClass().getClassLoader().getResource(loadFrom);

            if (packageUrl == null) {
                return;
            }

//            String path = packageUrl.toString();

//            if (path.startsWith("jar:file:/")) {
//
//                Integer jarFileEnd = path.indexOf("!");
//                String os = System.getProperty("os.name");
//                Integer subStringFrom = os.startsWith("Window") ? 10 : 9;
//                String jarFileName = path.substring(subStringFrom, jarFileEnd);
//
//                JarFile jarFile;
//                try {
//                    jarFile = new JarFile(jarFileName);
//                } catch (IOException e1) {
//                    jarFile = null;
//                }
//
//                Enumeration<JarEntry> entries = jarFile.entries();
//                while (entries.hasMoreElements()) {
//
//                    String entryName = entries.nextElement().getName();
//                    Integer index = entryName.lastIndexOf("/");
//
//                    if (index != -1) {
//
//                        String entryPackage = entryName.substring(0, index).replace("/", ".");
//                        String className = entryName.substring(index + 1);
//
//                        if (className.endsWith("Vo.class") && packages.contains(entryPackage)) {
//
//                            className = className.substring(0, className.lastIndexOf("."));
//
//                            addEntity(entryPackage, className);
//                        }
//                    }
//                }
//
//            } else {

            File packageDir = null;
            try {
                packageDir = new File(packageUrl.toURI());
            } catch (URISyntaxException e) {
            }

            if (packageDir == null || !packageDir.exists()) {
                return;
            }

            for (File file : packageDir.listFiles()) {

                String className = file.getName();

                // contine if its not VO
                if (!className.endsWith("Vo.class")) {
                    continue;
                }

                // get class name
                className = className.substring(0, className.lastIndexOf("."));

                addEntity(packageName, className);
            }
        }

        private void addEntity(String packageName, String className) {

            Class<?> clazz = null;
            try {
                clazz = Class.forName(packageName + "." + className);
            } catch (ClassNotFoundException e) {
            }

            Entity entity = clazz.getAnnotation(Entity.class);
            if (entity != null) {
                addAnnotatedClass(clazz);
            }
        }
    }
}