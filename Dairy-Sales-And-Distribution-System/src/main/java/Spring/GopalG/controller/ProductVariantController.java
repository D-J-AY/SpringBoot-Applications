package Spring.GopalG.controller;

import Spring.GopalG.dto.ProductVariantRequestDto;
import Spring.GopalG.dto.ProductVariantResponseDto;
import Spring.GopalG.entity.ProductVariant;
import Spring.GopalG.service.ProductVariantService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/variants")
public class ProductVariantController {
    private final ProductVariantService productVariantService;
    public ProductVariantController(ProductVariantService productVariantService) {
        this.productVariantService = productVariantService;
    }

    @PostMapping
    public ProductVariantResponseDto createVariant(@RequestBody ProductVariantRequestDto productVariantRequestDto){
        return productVariantService.createVariant(productVariantRequestDto);
    }

    @GetMapping
    public List<ProductVariantResponseDto> getVariants(){
        return productVariantService.getAllProductVariants();
    }

    @PutMapping("/{id}")
    public ProductVariantResponseDto updateVariant(@PathVariable int id,@RequestBody ProductVariantRequestDto productVariantRequestDto){
        return productVariantService.updateProductVariant(id, productVariantRequestDto);
    }

    @PatchMapping("/{id}/status")
    public ProductVariantResponseDto patchVariant(@PathVariable int id,@RequestParam boolean status){
        return productVariantService.updateProductVariantStatus(id, status);
    }

    @DeleteMapping("/{variantId}")
    public ProductVariantResponseDto deleteVariant(@PathVariable int variantId) {
        return productVariantService.deleteProductVariant(variantId);
    }

    @GetMapping("/{id}")
    public ProductVariantResponseDto getVariantsById(@PathVariable int id){
        return productVariantService.getProductVariantById(id);
    }
}
