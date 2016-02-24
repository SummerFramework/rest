package com.rbarbioni.core.data;

import com.rbarbioni.core.model.DomainEntity;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;

/**
 * Created by renan on 11/01/16.
 */
public class JpaRepositoryImpBase<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements JpaRepositoryBase<T,ID> {

    private final JpaEntityInformation entityInformation;

    private final EntityManager entityManager;

    public JpaRepositoryImpBase(JpaEntityInformation entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityInformation = entityInformation;
        this.entityManager = entityManager;
    }

    @Override
    public T findByUUID(String uuid) {
        if ( uuid == null){
            return null;
        }

        final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        final Class javaType = entityInformation.getJavaType();
        final CriteriaQuery<T> criteriaQuery = builder.createQuery(javaType);
        final Root<T> root = criteriaQuery.from(javaType);
        criteriaQuery.select(root);
        criteriaQuery.where( builder.equal(root.get(DomainEntity.UUID),uuid));
        final TypedQuery<T> query = this.entityManager.createQuery(criteriaQuery);
        return query.getSingleResult();
    }

    @Override
    public List<T> findActives() {
        final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        final Class javaType = entityInformation.getJavaType();
        final CriteriaQuery<T> criteriaQuery = builder.createQuery(javaType);
        final Root<T> root = criteriaQuery.from(javaType);
        criteriaQuery.select(root);
        criteriaQuery.where( builder.equal(root.get(DomainEntity.ACTIVE),true));
        return this.entityManager.createQuery(criteriaQuery).getResultList();
    }

    public Object getDeclaredType(){
        try {
            return this.entityInformation.getJavaType().newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
