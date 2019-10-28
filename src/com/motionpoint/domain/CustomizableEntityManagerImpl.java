package com.motionpoint.domain;

import java.util.Iterator;
import org.hibernate.mapping.Column;
import org.hibernate.mapping.Component;
import org.hibernate.mapping.PersistentClass;
import org.hibernate.mapping.Property;
import org.hibernate.mapping.SimpleValue;

public class CustomizableEntityManagerImpl implements CustomizableEntityManager {
    private Component customProperties;
    private Class entityClass;

    public CustomizableEntityManagerImpl(Class entityClass) {
        this.entityClass = entityClass;
    }

    public Class getEntityClass() {
        return entityClass;
    }

    public Component getCustomProperties() {
        if (customProperties == null) {
            PersistentClass persistentClass = getPersistentClass();
            Property property = persistentClass.getProperty(CUSTOM_COMPONENT_NAME);
            customProperties = (Component) property.getValue();
        }
        return customProperties;
    }

    public void addCustomField(String name) {
        SimpleValue simpleValue = new SimpleValue(this.getCustomProperties().getMappings());
        simpleValue.addColumn(new Column(name.toUpperCase()));
        simpleValue.setTypeName(String.class.getName());

        PersistentClass persistentClass = getPersistentClass();
        simpleValue.setTable(persistentClass.getTable());

        Property property = new Property();
        property.setName(name);
        property.setValue(simpleValue);
        getCustomProperties().addProperty(property);

        updateMapping();
    }

    public void removeCustomField(String name) {
        Iterator propertyIterator = getCustomProperties().getPropertyIterator();

        while (propertyIterator.hasNext()) {
            Property property = (Property) propertyIterator.next();
            if (property.getName().equals(name)) {
                propertyIterator.remove();
                updateMapping();
                HibernateUtil.getInstance().reset();
                return;
            }
        }
    }


    private synchronized void updateMapping() {
        MappingManager.updateClassMapping(this);
        HibernateUtil.getInstance().reset();
        //        updateDBSchema();
    }

    private PersistentClass getPersistentClass() {
        return HibernateUtil.getInstance().getClassMapping(this.entityClass);
    }
}