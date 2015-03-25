package gov.gwssi.csc.scms.repository;

import gov.gwssi.csc.scms.domain.Warning;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by WangZishi on 3/26/2015.
 *
 */
@Repository("warningRepository")
public interface WarningRepository extends CrudRepository<Warning, Integer>{

    @Override
    List<Warning> findAll();

    Warning findById(int id);

    Warning findByAddedTime();

    Warning findByStudent_CscNumber(String cscNumber);

}
