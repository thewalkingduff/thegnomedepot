package com.devduffy.gnomedepot.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.devduffy.gnomedepot.dto.ProductQuantityDTO;
import com.devduffy.gnomedepot.security.AuthenticatedUserService;
import com.devduffy.gnomedepot.service.ProductService;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

@Slf4j
@Controller
public class IndexController {
    
    @Autowired
    ProductService productService;

    @Autowired
	private AuthenticatedUserService authService;

    @GetMapping(value = { "/", "/index", "/index.html" })
    public String getAllProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
		if ( authService.isAuthenticated() ) {
            boolean isAdmin = authService.isUserInRole("ADMIN");
			log.debug(authService.getCurrentUsername() + " is current logged in and admin access = " + isAdmin);
			log.debug(authService.getCurrentUser() + "");
		} else {
			log.debug("USER NOT LOGGED IN");
		}
        model.addAttribute("productQuantityDTO", new ProductQuantityDTO());
        return "home";
    }

    @GetMapping("fileUpload")
    public String uploadFile() {
        return "admin/fileUpload";
    }

    @PostMapping("/fileUploadSubmit")
    public String uploadFileSubmit(Model model, @RequestParam("file") MultipartFile file) throws IOException {
        log.debug("Filename = " + file.getOriginalFilename());
        log.debug("File size = " + file.getSize() + " bytes");

        File targetFile = new File("./src/main/resources/static/img/" + file.getOriginalFilename());
	    FileUtils.copyInputStreamToFile(file.getInputStream(), targetFile);
	    model.addAttribute("filename", "/resources/static/img/" + file.getOriginalFilename());

        return "admin/fileUpload";
    }
}
