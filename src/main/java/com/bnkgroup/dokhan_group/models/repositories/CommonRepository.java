package com.bnkgroup.dokhan_group.models.repositories;

import com.bnkgroup.dokhan_group.models.entities.Accounts;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

public abstract class CommonRepository<T, ID> {

    //@PersistenceContext(unitName = "UnitDefault")
    @Inject
    private EntityManager eM;

    private final Class<T> entityClass;

    public CommonRepository(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    /**
     * ذخیره رکورد جدید
     *
     * @param entity
     * @return
     */
    public void Insert(T entity) {
        try {
            eM.persist(entity);
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * ویرایش رکورد موجود
     *
     * @param entity
     * @return
     */
    public T Update(T entity) {
        try {
            return eM.merge(entity);
        } catch (Exception e) {
            throw e;
        }
    }


    /**
     * حذف رکورد موجود
     *
     * @param entity
     */
    public void Delete(T entity) {
        try {
            eM.remove(eM.contains(entity) ? entity : eM.merge(entity));
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * یافتن یک رکورد با شناسه رکورد
     *
     * @param id
     * @return
     */
    public T findById(ID id) {
        return eM.find(entityClass, id);
    }

    /**
     * یافتن رکوردها
     *
     * @return
     */
    public List<T> findAll() {
        String jpql = "SELECT e FROM " + entityClass.getSimpleName() + " e";
        return eM.createQuery(jpql, entityClass).getResultList();
    }
}

