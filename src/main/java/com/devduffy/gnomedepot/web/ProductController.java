package com.devduffy.gnomedepot.web;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.devduffy.gnomedepot.dto.CartItem;
import com.devduffy.gnomedepot.dto.OrderDetailsDTO;
import com.devduffy.gnomedepot.dto.ProductDTO;
import com.devduffy.gnomedepot.dto.ProductQuantityDTO;
import com.devduffy.gnomedepot.entity.Order;
import com.devduffy.gnomedepot.entity.OrderDetails;
import com.devduffy.gnomedepot.entity.Product;
import com.devduffy.gnomedepot.entity.User;
import com.devduffy.gnomedepot.repository.ProductRepository;
import com.devduffy.gnomedepot.security.AuthenticatedUserService;
import com.devduffy.gnomedepot.service.OrderDetailsService;
import com.devduffy.gnomedepot.service.OrderService;
import com.devduffy.gnomedepot.service.ProductService;
import com.devduffy.gnomedepot.service.UserService;
import com.devduffy.gnomedepot.service.impl.ProductServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ProductController {

  OrderService orderService;
  OrderDetailsService orderDetailsService;
  ProductService productService;
  UserService userService;
  AuthenticatedUserService authenticatedUserService;
 

  public ProductController(OrderService orderService, ProductService productService, UserService userService,
      AuthenticatedUserService authenticatedUserService,
      OrderDetailsService orderDetailsService) {
    this.orderService = orderService;
    this.productService = productService;
    this.userService = userService;
    this.authenticatedUserService = authenticatedUserService;
    this.orderDetailsService = orderDetailsService;
  }

  @GetMapping("/product/searchResults")
  public String getAllProducts(Model model) {

    return "home";
  }

  @GetMapping("/product/details")
  public String getProductDetails(Model model, @RequestParam("id") Integer id) {

    Product product = productService.getProduct(id);
    ProductDTO productDTO = product.toDTO();
    model.addAttribute("productDTO", productDTO);
    model.addAttribute("productQuantityDTO", new ProductQuantityDTO());
    return "productDetails";
  }

  @GetMapping("/product/create")
  public String productForm(Model model) {
    model.addAttribute("product", new Product());
    return "admin/createProduct";
  }

  @PostMapping("/product/create")
  public String productSubmit(@ModelAttribute Product product, Model model) {
    model.addAttribute("product", product);
    productService.saveProduct(product);
    return "admin/result";
  }

  @GetMapping("/product/search")
  public String searchProducts(Model model, @RequestParam(value = "productName", required = false) String productName) {

    List<Product> products = productService.getByNameContaining(productName);
    model.addAttribute("products", products);
    return "home";
  }

}
