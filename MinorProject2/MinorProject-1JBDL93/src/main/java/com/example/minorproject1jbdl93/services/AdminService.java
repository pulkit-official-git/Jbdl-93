package com.example.minorproject1jbdl93.services;

import com.example.minorproject1jbdl93.dtos.GetStudentDetailsResponse;
import com.example.minorproject1jbdl93.models.Admin;
import com.example.minorproject1jbdl93.models.Authority;
import com.example.minorproject1jbdl93.models.Student;
import com.example.minorproject1jbdl93.models.User;
import com.example.minorproject1jbdl93.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    UserService userService;

    public Long create(Admin admin) {
        User user = admin.getUser();
        user = this.userService.createUser(user, Authority.ADMIN);
        admin.setUser(user);
        admin = this.adminRepository.save(admin);
        return admin.getId();
    }
}
