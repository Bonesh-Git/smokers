package com.bnkgroup.dokhan_group.config;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class HelloService {
    public String greet() {
        return "Weld is working!";
    }
}
