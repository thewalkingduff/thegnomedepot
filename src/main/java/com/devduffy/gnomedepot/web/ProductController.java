package com.devduffy.gnomedepot.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.devduffy.gnomedepot.entity.Product;
import com.devduffy.gnomedepot.repository.ProductRepository;
import com.devduffy.gnomedepot.service.ProductService;


@Controller
public class ProductController {

    @GetMapping("/")
    public String homePage(Model model, @RequestParam(required = false) String productName, @RequestParam(required = false) String productCategory ) {
        model.addAttribute("product", new Product(2, "Northlight Set of 3 Red and Gray Bearded Chubby Santa Christmas Gnomes 10.5", "christmas indoor", "./src/main/resources/static/img/products/bearded-chubby.webp", "Gnomes are best known for guarding Earthly treasures and are perfect for protecting your holiday goodies. Add a pop of color, a ton of cuteness, and luck to your holiday decor. Makes the perfect addition to your tree, your mantle, window, or anywhere you want to add that whimsical touch. Product Features: Chubby Santa gnome holiday figures Featuring 3 gnomes, one in red and gray, one in red and one in cream and brown Hats have a patch accent on the front Weighted bottoms for tabletop use Recommended for indoor use only Dimensions: 10.5\"H x 4.5\"W x 3.5\"D Material(s): felt/wool/wood Note: Set of 3 includes 1 of each figure pictured", 5.0, 167, 23, 26.39));
        return "home";
    }

}
