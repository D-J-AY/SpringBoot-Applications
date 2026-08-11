package Spring.GopalG.controller;

import Spring.GopalG.dto.ProductRequestDto;
import Spring.GopalG.dto.ProductResponseDto;
import Spring.GopalG.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ProductResponseDto addProduct(@Valid @RequestBody ProductRequestDto productRequestDto){
        return productService.createProduct(productRequestDto);
    }

    @GetMapping
    public List<ProductResponseDto> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ProductResponseDto getProductById(@PathVariable int id){
        return productService.getProductById(id);
    }

    @PatchMapping("/{id}/status")
    public ProductResponseDto changeStatus(@PathVariable int id,@RequestParam boolean status){
        return productService.changeProductStatus(id,status);
    }
}
