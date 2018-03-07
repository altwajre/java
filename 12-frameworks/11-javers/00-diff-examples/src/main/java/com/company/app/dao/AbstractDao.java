package com.company.app.dao;

import com.company.app.dao.interfaces.Dao;
import org.hibernate.criterion.Criterion;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public class AbstractDao<T, ID extends Serializable> implements Dao<T, ID> {

  private Class<T> persistentClass;
  private EntityManager entityManager;

  @SuppressWarnings("unchecked")
  public AbstractDao() {
    this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
  }

  public Class<T> getPersistentClass() {
    return persistentClass;
  }

  @SuppressWarnings("unchecked")
  @Override
  public T findById(ID id) {
    return getEntityManager().find(this.getPersistentClass(), id);
  }

  @Override
  public List<T> findAll() {
    return this.findByCriteria();
  }

  @SuppressWarnings("unchecked")
  protected List<T> findByCriteria(Criterion... criterion) {

    CriteriaBuilder builder = entityManager.getCriteriaBuilder();
    CriteriaQuery<T> query = builder.createQuery(this.getPersistentClass());
    Root<T> root = query.from(this.getPersistentClass());
    query.orderBy(builder.desc(root.get("name")));
    return entityManager.createQuery(query).getResultList();

  }

  @Override
  public T save(T entity) {
    this.getEntityManager().persist(entity);
    return entity;
  }

  @Override
  public void delete(T entity) {
    this.getEntityManager().remove(entity);
  }

  @Override
  public void flush() {
    this.getEntityManager().flush();
  }

  @Override
  public void clear() {
    this.getEntityManager().clear();
  }

  @Override
  public void setEntityManager(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  protected EntityManager getEntityManager() {
    if (this.entityManager == null) {
      this.entityManager = Persistence.createEntityManagerFactory("infinite-finances")
          .createEntityManager();
    }
    return this.entityManager;
  }
}
