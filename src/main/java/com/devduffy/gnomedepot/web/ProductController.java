package com.devduffy.gnomedepot.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devduffy.gnomedepot.entity.Product;
import com.devduffy.gnomedepot.repository.ProductRepository;
import com.devduffy.gnomedepot.service.ProductService;
import com.devduffy.gnomedepot.service.impl.ProductServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
// @RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/product/searchResults")
    public String getAllProducts(Model model) {
       
        return "home";
    }

    @GetMapping("/product/details")
    public String getProductDetails(Model model, Integer id) {
        model.addAttribute("productDetail", productService.getProduct(id));
        return "productDetails";
    }

    @GetMapping("/product/create")
    public String productForm(Model model) {
      model.addAttribute("product", new Product());
      return "createProduct";
    }
  
    @PostMapping("/product/create")
    public String productSubmit(@ModelAttribute Product product, Model model) {
      model.addAttribute("product", product);
      productService.saveProduct(product);
      return "result";
    }

    @GetMapping("/product/search")
    public String searchProducts(Model model, @RequestParam(value = "productName", required = false) String productName) {


      List<Product> products = productService.getByNameContaining(productName);
      model.addAttribute("products", products);
      return "home";
    }


  }
