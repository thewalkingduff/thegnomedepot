package com.devduffy.gnomedepot.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devduffy.gnomedepot.entity.Product;
import com.devduffy.gnomedepot.repository.ProductRepository;
import com.devduffy.gnomedepot.service.ProductService;
import com.devduffy.gnomedepot.service.ProductServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
// @RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/product/all")
    public String getAllProducts(Model model) {
        model.addAttribute("products", productService.getProducts());
        log.info("simple log");
        log.warn("This is a warning.");
        return "home";
    }

    @GetMapping("/product/details")
    public String getProductDetails(Model model, Integer id) {
        model.addAttribute("productDetail", productService.getProduct(id));
        return "productDetails";
    }

    

}
