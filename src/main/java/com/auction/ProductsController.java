package com.auction;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@RestController
@RequestMapping("api")

public class ProductsController {

    private static final String template = "Hello, %s!";

    @GetMapping("/products")
    public List<Product> allProducts() {
        try {

           // File f = ProductsController.class.getResource("products.json");

            ObjectMapper mapper = new ObjectMapper();

            Resource resource = new ClassPathResource("products.json");

            InputStream is = resource.getInputStream();

           //InputStream is = ProductsController.class.getResourceAsStream("products.json");
            Product[] testObj = mapper.readValue(is, Product[].class);
            return Arrays.asList(testObj);

        } catch (Exception e) {

            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/products/{id}")
    public Product getOne(@PathVariable Integer id) {
        try {

            // File f = ProductsController.class.getResource("products.json");

            ObjectMapper mapper = new ObjectMapper();

            Resource resource = new ClassPathResource("products.json");

            InputStream is = resource.getInputStream();

            //InputStream is = ProductsController.class.getResourceAsStream("products.json");
            Product[] products = mapper.readValue(is, Product[].class);


            return Arrays.stream(products).filter( product -> product.id == id).findFirst().get();

        } catch (Exception e) {

            e.printStackTrace();
            return null;
        }
    }
    @RequestMapping("/test")
    public String test() {
        return template;
    }
}
