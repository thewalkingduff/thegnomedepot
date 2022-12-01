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
    // ProductServiceImpl productServiceImpl;

    @Autowired
    ProductService productService;

    // @GetMapping("/")
    // public String homePage(Model model, @RequestParam(required = false) String productName, @RequestParam(required = false) String productCategory ) {
    //     model.addAttribute("product", new Product(2, "Northlight Set of 3 Red and Gray Bearded Chubby Santa Christmas Gnomes 10.5", "christmas indoor", "./src/main/resources/static/img/products/bearded-chubby.webp", "Gnomes are best known for guarding Earthly treasures and are perfect for protecting your holiday goodies. Add a pop of color, a ton of cuteness, and luck to your holiday decor. Makes the perfect addition to your tree, your mantle, window, or anywhere you want to add that whimsical touch. Product Features: Chubby Santa gnome holiday figures Featuring 3 gnomes, one in red and gray, one in red and one in cream and brown Hats have a patch accent on the front Weighted bottoms for tabletop use Recommended for indoor use only Dimensions: 10.5\"H x 4.5\"W x 3.5\"D Material(s): felt/wool/wood Note: Set of 3 includes 1 of each figure pictured", 5.0, 167, 23, 26.39));
    //     return "home";
    // }

    @GetMapping("/")
    public String getAllProducts(Model model) {

        model.addAttribute("products", productService.getProducts());
        log.info("simple log");
        log.warn("This is a warning.");
        return "home";
        // return new ResponseEntity<>(productService.getProducts(), HttpStatus.OK);  
    }

    @GetMapping("/productDetails")
    public String getProductDetails(Model model, Integer id) {
        model.addAttribute("productDetail", productService.getProduct(id));
        return "productDetails";
    }

}
