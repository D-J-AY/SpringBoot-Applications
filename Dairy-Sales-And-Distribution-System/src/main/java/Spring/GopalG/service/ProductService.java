package Spring.GopalG.service;

import Spring.GopalG.dto.ProductRequestDto;
import Spring.GopalG.dto.ProductResponseDto;
import Spring.GopalG.dto.ProductVariantUpdateDto;
import Spring.GopalG.entity.Product;
import Spring.GopalG.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductResponseDto createProduct(ProductRequestDto productRequestDto) {
        Product product = new Product();
        product.setProductName(productRequestDto.getProductName());
        product.setProductUnit(productRequestDto.getProductUnit());
        product.setActiveStatus(true);

        Product savedProduct = productRepository.save(product);
        return toResponse(savedProduct);
    }

    public ProductResponseDto changeProductStatus(int id, boolean status) {
        Product product = productRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Product not found with id: "+id));
        product.setActiveStatus(status);

        productRepository.save(product);
        return toResponse(product);
    }

    public List<ProductResponseDto> getAllProducts() {
        return productRepository.findAll().stream().map(this::toResponse).toList();
    }

    public ProductResponseDto getProductById(int id) {
        Product product = productRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Product not found with id: "+id));
        return toResponse(product);
    }

    public ProductResponseDto toResponse(Product p) {
        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setProductId(p.getProductId());
        productResponseDto.setProductName(p.getProductName());
        productResponseDto.setDescription(p.getDescription());
        productResponseDto.setProductUnit(p.getProductUnit());
        productResponseDto.setActiveStatus(p.isActiveStatus());
        productResponseDto.setCreatedAt(p.getCreated());
        productResponseDto.setUpdatedAt(p.getUpdated());

        return productResponseDto;

    }
}
