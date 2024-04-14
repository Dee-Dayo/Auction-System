package africa.semicolon.AuctionSystem.dto.requests;


import lombok.Data;

@Data
public class CreateItemRequest {
    private String itemOwner;
    private String itemName;
    private String description;
    private int itemPrice;
}
