package africa.semicolon.AuctionSystem.data.models;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("Bids")
public class Bid {
    private String id;
    private String itemId;
    private String bidderName;
    private int bidAmount;
}
