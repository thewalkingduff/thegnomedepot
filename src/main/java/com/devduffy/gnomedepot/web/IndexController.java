package com.devduffy.gnomedepot.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.devduffy.gnomedepot.entity.Product;
import com.devduffy.gnomedepot.security.AuthenticatedUserService;
import com.devduffy.gnomedepot.service.ProductService;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Slf4j
@Controller
public class IndexController {
    
    @Autowired
    ProductService productService;

    @Autowired
	private AuthenticatedUserService authService;

    @GetMapping(value = { "/", "/index", "/index.html" })
    public String getAllProducts(Model model) {
        // model.addAttribute("products", productService.getByName(productName));
        log.info("simple log");
        log.warn("This is a warning.");
        model.addAttribute("products", productService.getByNameContaining("gnome"));
        // if the user is authenticated
		if ( authService.isAuthenticated() ) {

            boolean isAdmin = authService.isUserInRole("ADMIN");
			log.debug(authService.getCurrentUsername() + " is current logged in and admin access = " + isAdmin);
			log.debug(authService.getCurrentUser() + "");
		} else {
			log.debug("USER NOT LOGGED IN");
		}

        return "home";
    }

    @GetMapping("fileUpload")
    public String uploadFile() {
        return "fileUpload";
    }

    @PostMapping("/fileUploadSubmit")
    public String uploadFileSubmit(Model model, @RequestParam("file") MultipartFile file) throws IOException {
        log.debug("Filename = " + file.getOriginalFilename());
        log.debug("File size = " + file.getSize() + " bytes");

        File targetFile = new File("./src/main/resources/static/img/" + file.getOriginalFilename());
		
	    FileUtils.copyInputStreamToFile(file.getInputStream(), targetFile);
	   
	    model.addAttribute("filename", "/resources/static/img/" + file.getOriginalFilename());

        return "fileUpload";
    }
}
