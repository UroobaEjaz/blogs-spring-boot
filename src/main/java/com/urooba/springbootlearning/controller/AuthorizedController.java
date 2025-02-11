package com.urooba.springbootlearning.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthorizedController {

    //APIs for admin and users

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/forAdminOnly")
    public String AdminOnly(){ return "For Admin Use Only"; }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/forUserOnly")
    public String UserOnly(){  return "For Users"; }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/forBoth")
    public String forBoth(){  return "For All"; }

}


