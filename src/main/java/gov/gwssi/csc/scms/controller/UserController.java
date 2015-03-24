package gov.gwssi.csc.scms.controller                                          ;

import org.springframework.web.bind.annotation.PathVariable                    ;
import org.springframework.web.bind.annotation.RequestMapping                  ;
import org.springframework.web.bind.annotation.RequestMethod                   ;
import org.springframework.web.bind.annotation.RestController                  ;


/**
 * Created by WangZishi on 3/19/2015.
 */
@RestController("userController")
@RequestMapping("/service/user")
public class UserController                                                    {

    @RequestMapping                                                            (
        value   = "/{id}"                                                      ,
        method  = RequestMethod.GET                                            ,
        headers = "Accept=application/json"                                    )
    public String
    getUser(@PathVariable int id)                                              {
        String user = id + ""                                                  ;
        return user                                                           ;}

    @RequestMapping                                                            (
        method  = RequestMethod.GET                                            ,
        headers = "Accept=application/json"                                    )
    public String
    getAllUsers()                                                              {
        String users =
            "[{"                                                               +
                "\"cscNumber\"      : \"CSC20150101\","                        +
                "\"ppName\"         : \"BRUCE WAYNE\","                        +
                "\"nationality\"    : \"United States\","                      +
                "\"dispatchWay\"    : \"way one\","                            +
                "\"placeProperties\": \"pro one\","                            +
                "\"projectType\"    : \"type one\","                           +
                "\"projectName\"    : \"name one\""                            +
            "},{"                                                              +
                "\"cscNumber\"      : \"CSC20150102\","                        +
                "\"ppName\"         : \"BRUCE WAYNE TWO\","                    +
                "\"nationality\"    : \"United States\","                      +
                "\"dispatchWay\"    : \"way two\","                            +
                "\"placeProperties\": \"pro two\","                            +
                "\"projectType\"    : \"type two\","                           +
                "\"projectName\"    : \"name two\""                            +
            "},{"                                                              +
                "\"cscNumber\"      : \"CSC20150103\","                        +
                "\"ppName\"         : \"BRUCE WAYNE THREE\","                  +
                "\"nationality\"    : \"United States\","                      +
                "\"dispatchWay\"    : \"way three\","                          +
                "\"placeProperties\": \"pro three\","                          +
                "\"projectType\"    : \"type three\","                         +
                "\"projectName\"    : \"name three\""                          +
            "},{"                                                              +
                "\"cscNumber\"      : \"CSC20150104\","                        +
                "\"ppName\"         : \"BRUCE WAYNE FOUR\","                   +
                "\"nationality\"    : \"United States\","                      +
                "\"dispatchWay\"    : \"way four\","                           +
                "\"placeProperties\": \"pro four\","                           +
                "\"projectType\"    : \"type four\","                          +
                "\"projectName\"    : \"name four\""                           +
            "},{"                                                              +
                "\"cscNumber\"      : \"CSC20150105\","                        +
                "\"ppName\"         : \"BRUCE WAYNE FIVE\","                   +
                "\"nationality\"    : \"United States\","                      +
                "\"dispatchWay\"    : \"way five\","                           +
                "\"placeProperties\": \"pro five\","                           +
                "\"projectType\"    : \"type five\","                          +
                "\"projectName\"    : \"name five\""                           +
            "},{"                                                              +
                "\"cscNumber\"      : \"CSC20150106\","                        +
                "\"ppName\"         : \"BRUCE WAYNE SIX\","                    +
                "\"nationality\"    : \"United States\","                      +
                "\"dispatchWay\"    : \"way six\","                            +
                "\"placeProperties\": \"pro six\","                            +
                "\"projectType\"    : \"type six\","                           +
                "\"projectName\"    : \"name six\""                            +
            "}]"                                                               ;
        return users                                                         ;}}