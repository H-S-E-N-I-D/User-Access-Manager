package com.dbs.userhierarchy.service;

import com.dbs.userhierarchy.model.TeamHierarchy;
import com.dbs.userhierarchy.model.User;
import com.dbs.userhierarchy.model.UserAccess;
import com.dbs.userhierarchy.repository.TeamHierarchyRepository;
import com.dbs.userhierarchy.repository.UserAccessRepository;
import com.dbs.userhierarchy.repository.UserRepository;
import com.dbs.userhierarchy.request.UserAccessRequest;
import com.dbs.userhierarchy.response.UserAccessResponse;
import com.dbs.userhierarchy.util.UserAccessResults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionInterceptor;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
@Transactional
public class UserAccessService {

    @Autowired
    private TeamHierarchyRepository teamHierarchyRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserAccessRepository userAccessRepository;

    @Autowired
    private MessageSource messageSource;

    private final static String TOP_MGR = "Miller";

    public UserAccessResponse addUserAccess(UserAccessRequest request) {
        UserAccessResponse userAccessResponse = new UserAccessResponse();
        String empId = request.getEmpId();
        UserAccessResults userAccessResults = this.addNewUserAccess(empId);
        userAccessResponse.setErrors(userAccessResults.getErrors());
        if (CollectionUtils.isEmpty(userAccessResults.getErrors())) {
            userAccessRepository.saveAll(userAccessResults.getUserAccessList());
            userAccessResponse.setStatus(messageSource.getMessage("req.status.success",
                    null, Locale.ENGLISH));
            userAccessResponse.setMessage(messageSource.getMessage("req.status.success.msg",
                    null, Locale.ENGLISH));
        } else {
            userAccessResponse.setStatus(messageSource.getMessage("req.status.failed",
                    null, Locale.ENGLISH));

            userAccessResponse.setMessage(messageSource.getMessage("req.status.failed.msg",
                    null, Locale.ENGLISH));
        }
        return userAccessResponse;

    }

    public UserAccessResponse addAllUserAccess(UserAccessRequest request) {
        UserAccessResponse userAccessResponse = new UserAccessResponse();
        List<String> empList = request.getEmpList();
        List<String> errorList = new ArrayList<>();
        List<UserAccess> userAccessList = new ArrayList<>();
        empList.forEach(empId -> {
            UserAccessResults userAccessResults = this.addNewUserAccess(empId);
            errorList.addAll(userAccessResults.getErrors());
            if (CollectionUtils.isEmpty(userAccessResults.getErrors())) {
                userAccessRepository.saveAll(userAccessResults.getUserAccessList());
            }
        });
        userAccessResponse.setErrors(errorList);
        if (CollectionUtils.isEmpty(errorList)) {
            userAccessResponse.setStatus(messageSource.getMessage("req.status.success",
                    null, Locale.ENGLISH));
            userAccessResponse.setMessage(messageSource.getMessage("req.status.success.msg",
                    null, Locale.ENGLISH));
        } else {
            userAccessResponse.setStatus(messageSource.getMessage("req.status.failed",
                    null, Locale.ENGLISH));

            userAccessResponse.setMessage(messageSource.getMessage("req.status.failed.msg",
                    null, Locale.ENGLISH));
            TransactionInterceptor.currentTransactionStatus().setRollbackOnly();


        }
        return userAccessResponse;

    }


    public UserAccessResults addNewUserAccess(String empId) {
        UserAccessResults accessResults = new UserAccessResults();
        List<UserAccess> newUserAccessList = new ArrayList<>();
        List<String> errorsList = new ArrayList<>();

        Optional<TeamHierarchy> optionalTh = teamHierarchyRepository.findByEmpId(empId);
        Optional<User> optionalUser = userRepository.findByEmpId(empId);
        if (optionalUser.isPresent()) {
            if (optionalTh.isPresent()) {
                TeamHierarchy teamHierarchy = optionalTh.get();
                User user = optionalUser.get();
                String managerId = teamHierarchy.getManagerId();
                Optional<User> optionalMgrUser = userRepository.findByEmpId(managerId);
                if (optionalMgrUser.isPresent()) {
                    User mgrUser = optionalMgrUser.get();

                    newUserAccessList.add(new UserAccess(managerId, mgrUser.getAccessKey(), mgrUser.getCountry(), empId, user.getAccessKey(), user.getCountry()));

                    if (!managerId.equals(this.TOP_MGR)) {
                        newUserAccessList.add(new UserAccess(empId, user.getAccessKey(), user.getCountry(), empId, user.getAccessKey(), user.getCountry()));
                        List<UserAccess> userAccessList = userAccessRepository.findByEmpId(managerId);
                        userAccessList.forEach(userAccess -> {
                            newUserAccessList.add(new UserAccess(empId, user.getAccessKey(), user.getCountry(), userAccess.getSubuser(), userAccess.getSubuserAccesskey(), userAccess.getSubuserCountry()));
                            if (!userAccess.getSubuser().equals(managerId)) {
                                newUserAccessList.add(new UserAccess(userAccess.getSubuser(), userAccess.getSubuserAccesskey(), userAccess.getSubuserCountry(), empId, user.getAccessKey(), user.getCountry()));
                            }
                        });
                        String mgr = managerId;
                        while (!mgr.equals(this.TOP_MGR)) {
                            Optional<TeamHierarchy> optionalMgrTh = teamHierarchyRepository.findByEmpId(mgr);
                            TeamHierarchy th = optionalMgrTh.get();
                            mgr = th.getManagerId();
                            Optional<User> optionalSuperMgrUser = userRepository.findByEmpId(mgr);
                            User superMgrUser = optionalSuperMgrUser.get();
                            newUserAccessList.add(new UserAccess(superMgrUser.getEmpId(), superMgrUser.getAccessKey(), superMgrUser.getCountry(), empId, user.getAccessKey(), user.getCountry()));

                        }

                    } else {
                        newUserAccessList.add(new UserAccess(empId, user.getAccessKey(), user.getCountry(), empId, user.getAccessKey(), user.getCountry()));

                    }
                    accessResults.setUserAccessList(newUserAccessList);

                } else {
                    errorsList.add(messageSource.getMessage("employee.mgr.unavailable.hierarchy",
                            new Object[]{managerId}, Locale.ENGLISH));

                }
            } else {
                errorsList.add(messageSource.getMessage("employee.unavailable.hierarchy",
                        new Object[]{empId}, Locale.ENGLISH));

            }

        } else {

            errorsList.add(messageSource.getMessage("employee.invalid",
                    new Object[]{empId}, Locale.ENGLISH));
        }
        accessResults.setErrors(errorsList);

        return accessResults;

    }


}
