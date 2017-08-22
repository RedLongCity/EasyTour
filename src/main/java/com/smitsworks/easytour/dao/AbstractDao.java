package com.smitsworks.easytour.dao;

import java.io.Serializable;
import org.hibernate.Session;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import java.lang.reflect.ParameterizedType;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author redlongcity
 */

public abstract class AbstractDao<PK extends Serializable, T> {

private final Class<T> persistentClass;

@SuppressWarnings("unchecked")
public AbstractDao(){
    this.persistentClass =(Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass
()).getActualTypeArguments()[1];
}

@Autowired
private SessionFactory sessionFactory;

protected Session getSession(){
    return sessionFactory.getCurrentSession();
}

@SuppressWarnings("unchecked")
public T getByKey(PK key){
    return (T) getSession().get(persistentClass, key);
}

public void persist(T entity){
    getSession().persist(entity);
}

public void delete(T entity){
    getSession().delete(entity);
}

public Criteria createCriteria(){
    return getSession().createCriteria(persistentClass);
}
}
