package africa.semicolon.AuctionSystem.dto.responses;

import lombok.Data;

@Data
public class CreateBidResponse {
    private String bidId;
    private int biddingPrice;
    private String bidderName;
}
