package Spring.GopalG.service;

import Spring.GopalG.dto.ShopRequestDto;
import Spring.GopalG.dto.ShopResponseDto;
import Spring.GopalG.entity.Shop;
import Spring.GopalG.repository.ShopRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopService {

    private final ShopRepository shopRepository;

    public ShopService(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }

    public ShopResponseDto createShop(ShopRequestDto shopRequestDto) {
        Shop shop = new Shop();
        shop.setShopName(shopRequestDto.getShopName());
        shop.setPhone(shopRequestDto.getPhone());
        shop.setAddress(shopRequestDto.getAddress());
        shop.setActiveStatus(true);

        Shop savedShop = shopRepository.save(shop);
        return toResponse(savedShop);
    }

    public List<ShopResponseDto> getAllShops() {
        return shopRepository.findAll().stream().map(this::toResponse).toList();
    }

    public ShopResponseDto getShopById(int Id) {
        Shop shop = shopRepository.findById(Id)
                .orElseThrow(()->new RuntimeException("Shop not found with id " + Id));
        return toResponse(shop);
    }

    public ShopResponseDto toResponse(Shop s){
        ShopResponseDto shopResponseDto = new ShopResponseDto();
        shopResponseDto.setId(s.getId());
        shopResponseDto.setShopName(s.getShopName());
        shopResponseDto.setPhone(s.getPhone());
        shopResponseDto.setAddress(s.getAddress());
        shopResponseDto.setActiveStatus(s.getActiveStatus());
        shopResponseDto.setCreatedAt(s.getCreated());
        shopResponseDto.setUpdatedAt(s.getUpdated());

        return shopResponseDto;
    }
}
