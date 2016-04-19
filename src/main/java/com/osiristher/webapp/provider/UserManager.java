package com.osiristher.webapp.provider;

import org.apache.axis.ConfigurationException;
import org.apache.log4j.BasicConfigurator;
import com.osiristher.webapp.provider.soap.portal.company.*;
import com.osiristher.webapp.provider.soap.portal.user.*;
import com.osiristher.webapp.provider.soap.portal.role.*;

import java.net.URL;
import java.rmi.RemoteException;

/**
 * Created by DesiresDesigner on 12/14/15.
 */
public class UserManager {

//    private String remoteUser;
//    private String password;

    private final String serviceUserName = "Portal_UserService";
    private final String serviceCompanyName = "Portal_CompanyService";
    private final String serviceRole = "Portal_RoleService";
    private final String virtualHost = "localhost";

    private CompanyServiceSoapServiceLocator companyServiceSoapServiceLocator;
    private UserServiceSoapServiceLocator userServiceSoapServiceLocator;
    private RoleServiceSoapServiceLocator roleServiceSoapServiceLocator;

//    private CompanyServiceSoap companyServiceSoap;
//    private UserServiceSoap userServiceSoap;
//    private RoleServiceSoap roleServiceSoap;
//
//    private CompanySoap companySoap;
//    private UserSoap userSoap;
//    private RoleSoap roleSoap;


    UserManager() {
        BasicConfigurator.configure();

        companyServiceSoapServiceLocator = new CompanyServiceSoapServiceLocator();
        userServiceSoapServiceLocator = new UserServiceSoapServiceLocator();
        roleServiceSoapServiceLocator = new RoleServiceSoapServiceLocator();
    }

    UserInfo getUserInfo(String userName, String password) throws Exception {
        UserInfo userInfo = new UserInfo();

        CompanyServiceSoap soapCompany =
                companyServiceSoapServiceLocator.getPortal_CompanyService(
                        _getURL(userName, password, serviceCompanyName,
                                true));

        CompanySoap companySoap =
                soapCompany.getCompanyByVirtualHost(virtualHost);

        UserServiceSoap userServiceSoap =
                userServiceSoapServiceLocator
                        .getPortal_UserService(
                                _getURL(userName, password, serviceUserName, true));

        UserSoap user = userServiceSoap.getUserByScreenName(companySoap.getCompanyId(), userName);

        RoleServiceSoap roleServiceSoap =
                roleServiceSoapServiceLocator
                        .getPortal_RoleService(
                                _getURL(userName, password, serviceRole, true));


        RoleSoap[] roles = roleServiceSoap.getUserRoles(user.getUserId());
//        System.out.println("IsStud: " + roleServiceSoap.hasUserRole(user.getUserId(), companySoap.getCompanyId(), "STUDENT", false));
//        System.out.println("IsTrainer: " + roleServiceSoap.hasUserRole(user.getUserId(), companySoap.getCompanyId(), "TRAINER", false));

        userInfo.setLr_id(user.getUserId());
        userInfo.setScreenName(userName);
        if (roleServiceSoap.hasUserRole(user.getUserId(), companySoap.getCompanyId(), "STUDENT", false))
            userInfo.setRole("ROLE_STUDENT");
        else
            userInfo.setRole("ROLE_TRAINER");

        return userInfo;
    }

    private URL _getURL(String remoteUser, String password,
                               String serviceName, boolean authenticate)
            throws Exception {

        // Unauthenticated url
        String url = "http://127.0.0.1:8008/api/axis/" + serviceName;

        // Authenticated url
        if (authenticate) {
            url = "http://" + remoteUser + ":" + password
                    + "@127.0.0.1:8008/api/axis/"
                    + serviceName;
        }

        return new URL(url);
    }
}
