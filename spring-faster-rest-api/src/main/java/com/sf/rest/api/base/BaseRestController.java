package com.sf.rest.api.base;
import com.sf.rest.api.data.BaseJpaRepository;
import com.sf.rest.api.model.DomainEntity;
import com.sf.rest.api.model.valueobject.DomainVO;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

/**
 * Created by renan on 20/02/16.
 */
@RestController
public abstract class BaseRestController<E extends DomainEntity, VO extends DomainVO> extends BaseController {

    BaseService<E, VO> baseService;

    public BaseRestController(BaseJpaRepository baseJpaRepository){
        this.baseService = new BaseService<>(baseJpaRepository);
        this.baseService.setMessageI18nService(getMessageI18nService());
    }

    @RequestMapping( method = RequestMethod.GET)
    public List<VO> findAll () throws IOException, IllegalAccessException, InstantiationException {
        return this.baseService.findAll();
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public List<VO> search (HttpServletRequest httpServletRequest) throws IOException, IllegalAccessException, InstantiationException {
        return this.baseService.search(httpServletRequest);
    }

    @RequestMapping(value = "/actives", method = RequestMethod.GET)
    public List<VO> findActives () throws IOException, IllegalAccessException, InstantiationException {
        return this.baseService.findActives();
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