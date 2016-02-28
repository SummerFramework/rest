package com.sf.rest.api.base;

import com.sf.rest.api.data.BaseJpaRepository;
import com.sf.rest.api.model.DomainEntity;
import com.sf.rest.api.model.DomainPageImpl;
import com.sf.rest.api.model.valueobject.DomainVO;
import com.sf.rest.api.service.MessageI18nService;
import com.sf.rest.api.utils.ObjectMapperUtils;
import com.sf.rest.api.utils.ReflectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by renan on 20/02/16.
 */

public class BaseService<E extends DomainEntity, VO extends DomainVO> {

    public static final Logger LOGGER = LoggerFactory.getLogger(BaseService.class);

    BaseJpaRepository<E, Long> baseJpaRepository;

    private MessageI18nService messageI18nService;

    public BaseService(BaseJpaRepository baseJpaRepository){
        this.baseJpaRepository = baseJpaRepository;
    }

    public List<VO> findAll () throws IOException, InstantiationException, IllegalAccessException {
        return convert(this.baseJpaRepository.findAll(), false);
    }

    public List<VO> search (HttpServletRequest httpServletRequest) throws IllegalAccessException, InstantiationException {
        Map<String, String> queryParameters = getParametersMap(httpServletRequest);
        return convert(this.baseJpaRepository.findByQuery(queryParameters), false);
    }

    private Map<String, String> getParametersMap(HttpServletRequest httpServletRequest) {
        Map<String, String> queryParameters = new HashMap<>();

        String query = httpServletRequest.getParameter("q");
        String[] allItems = query.split(",");
        for (String items : allItems) {
            String[] item = items.split(":");
            queryParameters.put(item[0], item[1]);
        }

        return queryParameters;
    }

    public DomainPageImpl search(Pageable pageable, HttpServletRequest httpServletRequest) throws IllegalAccessException, InstantiationException {
        Map<String, String> queryParameters = getParametersMap(httpServletRequest);
        Page<E> page = baseJpaRepository.findByQuery(queryParameters, pageable);
        DomainPageImpl domainPage = new DomainPageImpl(page.getContent(), pageable, page.getTotalElements());
        domainPage.map(pageConverter);
        return domainPage;
    }

    public List<VO> findActives () throws IOException, InstantiationException, IllegalAccessException {
        final List<E> applications = this.baseJpaRepository.findActives();
        List<VO> vos = new ArrayList<>(applications.size());
        for (E entity : applications) {
            if (entity.isActive())
                vos.add((VO) entity.toVO());
        }
        return vos;
    }

    public Page<VO> findAll (Pageable pageable){
        Page page = baseJpaRepository.findAll(pageable);
        DomainPageImpl domainPage = new DomainPageImpl(page.getContent(), pageable, page.getTotalElements());
        domainPage.map(pageConverter);
        return domainPage;
    }

    public List<VO> findAll (Iterable<Long> ids) throws IllegalAccessException, InstantiationException {
        return convert(baseJpaRepository.findAll(ids), false);
    }

    public Page<VO> findActives (Pageable pageable){
        Page page = baseJpaRepository.findPageableActives(pageable);
        DomainPageImpl domainPage = new DomainPageImpl(page.getContent(), pageable, page.getTotalElements());
        domainPage.map(pageConverter);
        return domainPage;
    }

    public VO findById (Long id) throws InstantiationException, IllegalAccessException {
        E e = baseJpaRepository.findOne(id);
        if ( e != null)
            return e.toVO();
        else
            return null;
    }

    public VO save (VO domainVO) throws IOException, IllegalAccessException, InstantiationException {

        E entityToSave;

        E entity = this.baseJpaRepository.findByUUID(domainVO.getUUID());

        if (entity != null){
            entityToSave = (E) ObjectMapperUtils.parse(domainVO, entity.getClass());
            ReflectionUtils.merge(entity, entityToSave);
        }else {
            entityToSave = (E) baseJpaRepository.getDeclaredType();
            domainVO = (VO) ReflectionUtils.merge(domainVO, entityToSave);
        }

        entityToSave.validate();
        entity = this.baseJpaRepository.save(entityToSave);
        ReflectionUtils.merge(entity, domainVO);
        return domainVO;
    }

    public VO findUnique (String uuid) throws InstantiationException, IllegalAccessException {
        E entity = this.baseJpaRepository.findByUUID(uuid);
        if ( entity == null){
            return null;
        }

        return entity.toVO();
    }

    public void delete (String uuid){
        final E entity = this.baseJpaRepository.findByUUID(uuid);
        if ( entity == null){
            throw new RuntimeException(messageI18nService.get("object.not.found"));
        }

        this.baseJpaRepository.delete(entity);
    }

    List<VO> convert (List<E> entities, boolean onlyActive) throws InstantiationException, IllegalAccessException {

        if ( entities == null ){
            return null;
        }

        List<VO> vos = new ArrayList<>(entities.size());
        for (E entity : entities) {
            if ( onlyActive ){
                if (entity.isActive())
                    vos.add(entity.toVO());
            }else{
                vos.add(entity.toVO());
            }
        }
        return vos;
    }

    Converter<E, VO> pageConverter = o ->{
        try {
            return o.toVO();
        } catch (IllegalAccessException|InstantiationException e) {
            LOGGER.error("Error", e);
        }
        return null;
    };

    public void setMessageI18nService(MessageI18nService messageI18nService) {
        this.messageI18nService = messageI18nService;
    }
}