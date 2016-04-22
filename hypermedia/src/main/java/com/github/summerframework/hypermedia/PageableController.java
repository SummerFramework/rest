package com.github.summerframework.hypermedia;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import com.github.summerframework.core.data.BaseJpaRepository;
import com.github.summerframework.core.model.DomainEntity;
import com.github.summerframework.core.model.valueobject.DomainVO;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;

/**
 * Created by renan on 27/02/16.
 */
@RestController
public abstract class PageableController<E extends DomainEntity, VO extends DomainVO> extends BaseController {

    BaseService<E, VO> baseService;

    public PageableController(BaseJpaRepository baseJpaRepository){
        this.baseService = new BaseService<>(baseJpaRepository);
    }
    @RequestMapping( method = RequestMethod.GET)
    public Page<VO> findAll(Pageable pageable) throws IOException, IllegalAccessException, InstantiationException {
        return baseService.findAll(pageable);
    }

    @RequestMapping(value = "/actives", method = RequestMethod.GET)
    public Page<VO> findActives(Pageable pageable) throws IOException, IllegalAccessException, InstantiationException {
        return baseService.findActives(pageable);
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public Page<VO> search(Pageable pageable, HttpServletRequest httpServletRequest) throws IOException, IllegalAccessException, InstantiationException {
        return baseService.search(pageable, httpServletRequest);
    }

    @RequestMapping(value = "/{uuid}", method = RequestMethod.GET)
    public VO findUnique ( @PathVariable String uuid ) throws IOException, InstantiationException, IllegalAccessException {
        return this.baseService.findUnique(uuid);
    }

    @RequestMapping( method = RequestMethod.POST)
    public VO create ( @Valid @RequestBody VO domainVO) throws IOException, InstantiationException, IllegalAccessException {
        return this.baseService.save(domainVO);
    }

    @RequestMapping(value = "/{uuid}", method = RequestMethod.PUT)
    public VO update ( @PathVariable String uuid, @Valid @RequestBody VO domainVO ) throws IOException, InstantiationException, IllegalAccessException {
        return this.baseService.save(domainVO);
    }

    @RequestMapping(value = "/{uuid}", method = RequestMethod.DELETE)
    public void delete ( @PathVariable String uuid) throws IOException {
        this.baseService.delete(uuid);
    }
}
