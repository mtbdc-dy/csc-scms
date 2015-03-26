package gov.gwssi.csc.scms.service;

import gov.gwssi.csc.scms.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by WangZishi on 3/27/2015.
 *
 */
@Service
public class SampleServiceImpl implements SampleService {

    @Autowired
    UserRepository userRepository;

}
