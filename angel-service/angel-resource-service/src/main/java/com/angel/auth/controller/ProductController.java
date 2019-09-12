package com.angel.auth.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ahmed on 30.5.18.
 */

@RestController
@RequestMapping(value = "/product")
public class ProductController {


    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_PRODUCT_ADMIN')")
    public String getProduct(@PathVariable("id") Long id) {
        return ""+id;
    }

}
