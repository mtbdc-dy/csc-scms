package gov.gwssi.csc.scms.service.importlog;

import gov.gwssi.csc.scms.domain.filter.Filter;
import gov.gwssi.csc.scms.domain.importlog.ImportLog;
import gov.gwssi.csc.scms.domain.user.User;
import gov.gwssi.csc.scms.repository.importLog.ImportLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import static org.springframework.data.jpa.domain.Specifications.where;

/**
 * Created by LiZhiSheng on 2015/9/1.
 */
@Service("importLogService")
public class ImportLogService extends ImportLogSpecs{
    @Autowired
    @Qualifier("importLogRepository")
    private ImportLogRepository importLogRepository;
    public Page<ImportLog> getImportLogsPagingByFilter(Filter filter,Integer page,Integer size,String mode,User user,String importClass) {
        Specification<ImportLog> specA = filterIsLike(filter,user,importClass);
//        Specification<Ticket> specB = userIs(user);
        return importLogRepository.findAll(where(specA), new PageRequest(page, size, Sort.Direction.DESC,"created"));
    }
}
