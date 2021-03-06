package com.example.springmvc.springmvc.controller;

import com.example.springmvc.springmvc.Service.ProductService;
import com.example.springmvc.springmvc.model.Product;
import com.example.springmvc.springmvc.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by mpumelelomashabane on 15/01/2018.
 */

@Controller
public class ProductController {

    private ProductRepository productRepository;
    private ProductService productService;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(path = "/")
    public String index(){
        return "index";
    }

//    @RequestMapping(path = "/products/add", method = RequestMethod.GET)
//    public String createProdct(Model model){
//     model.addAttribute("product", new Product());
//     return "edit";
//    }

    @RequestMapping(path = "/products/add", method = RequestMethod.GET)
    public String createProduct(Model model){
        productService.addItem(model);
        return "edit";
    }

    @RequestMapping(path = "/products/total", method = RequestMethod.GET)
    public String getTotal(Model model){
        model.addAttribute("productsTotal", productRepository.findAll());
        return "redirect:/products/";
    }

    @RequestMapping(path = "products", method = RequestMethod.POST)
    public String saveProduct(Product product){
        productRepository.save(product);
        return "redirect:/products/";
    }

    @RequestMapping(path = "/products", method = RequestMethod.GET)
    public String getAllProducts(Model model){
        model.addAttribute("products", productRepository.findAll());
        return "products";
    }

    @RequestMapping(path = "/products/edit/{id}", method = RequestMethod.GET)
    public String editProduct(Model model, @PathVariable(value = "id") String id){
        model.addAttribute("product", productRepository.findOne(id));
        return "edit";
    }

    @RequestMapping(path = "/products/delete/{id}", method = RequestMethod.GET)
    public String deleteProduct(@PathVariable(name = "id") String id){
        productRepository.delete(id);
        return "redirect:/products/";

    }



}
