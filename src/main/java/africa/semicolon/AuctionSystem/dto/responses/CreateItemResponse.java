package africa.semicolon.AuctionSystem.dto.responses;

import lombok.Data;

@Data
public class CreateItemResponse {
    private String itemId;
    private String itemName;
    private String itemDescription;
    private int itemPrice;
}
