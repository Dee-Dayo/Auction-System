package africa.semicolon.AuctionSystem.dto.requests;

import lombok.Data;

@Data
public class BidItemRequest {
    private String itemId;
    private String bidderName;
    private int bidAmount;
}
