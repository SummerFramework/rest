package com.rbarbioni.core.base;

import com.rbarbioni.core.data.JpaRepositoryBase;
import com.rbarbioni.core.model.DomainEntity;
import com.rbarbioni.core.model.valueobject.DomainVO;
import com.rbarbioni.core.utils.ObjectMapperUtils;
import com.rbarbioni.core.utils.ReflectionUtils;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by renan on 20/02/16.
 */
public class ServiceBase<E extends DomainEntity, VO extends DomainVO> {

    private JpaRepositoryBase jpaRepositoryBase;

    public ServiceBase (JpaRepositoryBase jpaRepositoryBase){
        this.jpaRepositoryBase = jpaRepositoryBase;
    }

    public List<VO> findAll () throws IOException, InstantiationException, IllegalAccessException {

        final List<E> entities = this.jpaRepositoryBase.findAll();

        List<VO> vos = new ArrayList<>(entities.size());
        for (E entity : entities) {
            vos.add((VO) entity.toVO(getVOType()));
        }
        return vos;
    }

    public List<VO> findActives () throws IOException, InstantiationException, IllegalAccessException {
        final List<E> applications = this.jpaRepositoryBase.findActives();
        List<VO> vos = new ArrayList<>(applications.size());
        for (E entity : applications) {
            if (entity.isActive())
                vos.add((VO) entity.toVO(getVOType()));
        }
        return vos;
    }

    public VO save (VO domainVO) throws IOException, IllegalAccessException, InstantiationException {

        E entityToSave;

        E entity = (E) this.jpaRepositoryBase.findByUUID(domainVO.getUUID());

        if (entity != null){
            entityToSave = (E) ObjectMapperUtils.parse(domainVO, entity.getClass());
            ReflectionUtils.merge(entity, entityToSave);
        }else {
            entityToSave = (E) jpaRepositoryBase.getDeclaredType();
            domainVO = (VO) ReflectionUtils.merge(domainVO, entityToSave);
        }

        entityToSave.validate();
        entity = (E) this.jpaRepositoryBase.save(entityToSave);
        ReflectionUtils.merge(entity, domainVO);
        return domainVO;
    }

    public VO findUnique (String uuid) throws InstantiationException, IllegalAccessException {
        E entity = (E) this.jpaRepositoryBase.findByUUID(uuid);
        if ( entity == null){
            return null;
        }

        return (VO) entity.toVO(getVOType());
    }

    public void delete (String uuid){
        final Object entity = this.jpaRepositoryBase.findByUUID(uuid);
        if ( entity == null){
            throw new RuntimeException("Resource not found");
        }

        this.jpaRepositoryBase.delete(entity);
    }

    @SuppressWarnings("unchecked")
    private Class<E> getEntityType() {
        try {
            String className = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0].getTypeName();
            Class<?> clazz = Class.forName(className);
            return (Class<E>) clazz;
        } catch (Exception e) {
            throw new IllegalStateException("Class is not parametrized with generic type!!! Please use extends <> ");
        }
    }

    @SuppressWarnings("unchecked")
    private Class<VO> getVOType() {
        try {
            String className = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1].getTypeName();
            Class<?> clazz = Class.forName(className);
            return (Class<VO>) clazz;
        } catch (Exception e) {
            throw new IllegalStateException("Class is not parametrized with generic type!!! Please use extends <> ");
        }
    }
}
