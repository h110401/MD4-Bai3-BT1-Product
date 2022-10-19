package rikkei.academy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import rikkei.academy.model.Product;
import rikkei.academy.service.IProductService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = {"/", "/product"})
public class ProductController {

    final
    IProductService productService;

    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String showList(Model model) {
        model.addAttribute("productList", productService.findAll());
        return "list";
    }

    @GetMapping("/create")
    public String formCreate(Model model) {
        model.addAttribute("product", new Product());
        return "create";
    }

    @PostMapping("/save")
    public String save(Product product) {
        product.setId(productService.findAll().get(productService.findAll().size() - 1).getId() + 1);
        productService.save(product);
        return "redirect:/product";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable int id, Model model) {
        model.addAttribute("product", productService.findById(id));
        return "edit";
    }

    @PostMapping("/update")
    public String update(Product product) {
        productService.update(product.getId(), product);
        return "redirect:/product";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable int id, Model model) {
        model.addAttribute("product", productService.findById(id));
        return "delete";
    }

    @PostMapping("/remove")
    public String remove(Product product) {
        productService.remove(product.getId());
        return "redirect:/product";
    }

    @PostMapping("/search")
    public String search(@RequestParam String search,  Model model) {
        List<Product> products = new ArrayList<>();
        for (Product product : productService.findAll()) {
            if (product.getName().toLowerCase().contains(search.trim().toLowerCase())) {
                products.add(product);
            }
        }
        model.addAttribute("productList",products);
        System.out.println(products);
        return "list";
    }

}
