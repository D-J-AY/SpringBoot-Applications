package Spring.GopalG.controller;

import Spring.GopalG.dto.ShopRequestDto;
import Spring.GopalG.dto.ShopResponseDto;
import Spring.GopalG.service.ShopService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shops")
public class ShopController {
    private final ShopService shopService;

    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    @PostMapping
    public ShopResponseDto addShop(@Valid @RequestBody ShopRequestDto shopRequestDto)    {
        return shopService.createShop(shopRequestDto);
    }

    @GetMapping
    public List<ShopResponseDto> getShops() {
        return shopService.getAllShops();
    }

    @GetMapping("/{id}")
    public ShopResponseDto getShopById(@PathVariable int id) {
        return shopService.getShopById(id);
    }

    @GetMapping("/get")
    public String test() {
        return "GET /Shops working";
    }
}
