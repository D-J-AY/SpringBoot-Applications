package Spring.GopalG.service;

import Spring.GopalG.dto.ProductVariantRequestDto;
import Spring.GopalG.dto.ProductVariantResponseDto;
import Spring.GopalG.entity.Product;
import Spring.GopalG.entity.ProductVariant;
import Spring.GopalG.repository.ProductRepository;
import Spring.GopalG.repository.ProductVariantRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductVariantService {
    private final ProductVariantRepository productVariantRepository;
    private final ProductRepository productRepository;
    public ProductVariantService(ProductVariantRepository productVariantRepository, ProductRepository productRepository) {
        this.productVariantRepository = productVariantRepository;
        this.productRepository = productRepository;
    }


    public ProductVariantResponseDto createVariant(ProductVariantRequestDto productVariantRequestDto) {
        int productId = productVariantRequestDto.getProductId();
        Product product = productRepository.findById(productId)
                .orElseThrow(()->new RuntimeException("Product not found with id: "+productId));
        ProductVariant productVariant = new ProductVariant();
        productVariant.setProduct(product);
        productVariant.setPackSize(productVariantRequestDto.getPackSize());
        productVariant.setVariantUnit(productVariantRequestDto.getUnit());
        productVariant.setPricePerPack(productVariantRequestDto.getPricePerPack());
        productVariant.setActiveStatus(true);

        ProductVariant savedProduct = productVariantRepository.save(productVariant);
        return toResponse(savedProduct);
    }

    public ProductVariantResponseDto updateProductVariant(int variantId,ProductVariantRequestDto productVariantRequestDto) {
        ProductVariant productVariant = productVariantRepository.findById(variantId)
                .orElseThrow(()->new RuntimeException("Product not found with id: "+variantId));

        if(productVariantRequestDto.getProductId() != 0){
            Product product = productRepository.findById(productVariantRequestDto.getProductId())
                    .orElseThrow(()->new RuntimeException("Product not found with id: "+productVariantRequestDto.getProductId()));
            productVariant.setProduct(product);
        }

        productVariant.setPackSize(productVariantRequestDto.getPackSize());
        productVariant.setVariantUnit(productVariantRequestDto.getUnit());
        productVariant.setPricePerPack(productVariantRequestDto.getPricePerPack());

        ProductVariant saved = productVariantRepository.save(productVariant);
        return toResponse(saved);
    }

    public ProductVariantResponseDto updateProductVariantStatus(int variantId,boolean activeStatus){
        ProductVariant productVariant = productVariantRepository.findById(variantId)
                .orElseThrow(()->new RuntimeException("Product not found with id: "+variantId));
        productVariant.setActiveStatus(activeStatus);
        ProductVariant saved = productVariantRepository.save(productVariant);
        return toResponse(saved);
    }

    public ProductVariantResponseDto deleteProductVariant(int variantId) {
        ProductVariant productVariant = productVariantRepository.findById(variantId)
                .orElseThrow(()->new RuntimeException("Product Variant not found with id: "+variantId));
        productVariantRepository.delete(productVariant);
        return toResponse(productVariant);
    }

    public List<ProductVariantResponseDto> getAllProductVariants() {
        return productVariantRepository.findAll().stream().map(this::toResponse).toList();
    }

    public ProductVariantResponseDto getProductVariantById(int id) {
        ProductVariant productVariant =  productVariantRepository.findById(id).orElseThrow(()->new RuntimeException("Product Variant not found by Id "+id));
        return toResponse(productVariant);
    }

    ProductVariantResponseDto toResponse(ProductVariant pv){
        ProductVariantResponseDto productVariantResponseDto = new ProductVariantResponseDto();
        productVariantResponseDto.setId(pv.getVariantId());
        productVariantResponseDto.setProductId(pv.getProduct().getProductId());
        productVariantResponseDto.setPackSize(pv.getPackSize());
        productVariantResponseDto.setUnit(pv.getVariantUnit());
        productVariantResponseDto.setPricePerPack(pv.getPricePerPack());
        productVariantResponseDto.setIsActive(pv.isActiveStatus());
        productVariantResponseDto.setCreatedAt(pv.getCreated());
        productVariantResponseDto.setUpdatedAt(pv.getUpdated());

        return productVariantResponseDto;
    }
}
