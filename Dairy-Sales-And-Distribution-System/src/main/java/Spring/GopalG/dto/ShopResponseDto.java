package Spring.GopalG.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class ShopResponseDto {
    private int id;
    private String shopName;
    private String phone;
    private String address;
    private Boolean activeStatus;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
