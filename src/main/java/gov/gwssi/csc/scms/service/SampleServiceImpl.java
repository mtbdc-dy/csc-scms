package gov.gwssi.csc.scms.service;

import gov.gwssi.csc.scms.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * User: ryan
 * Date: 2/8/13
 */
@Service
public class SampleServiceImpl implements SampleService {

    @Autowired
    UserRepository userRepository;

}
