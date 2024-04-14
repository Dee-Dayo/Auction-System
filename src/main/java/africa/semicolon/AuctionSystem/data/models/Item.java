package africa.semicolon.AuctionSystem.data.models;


import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document("Items")
public class Item {
    private String id;
    private String name;
    private String description;
    private int startingBidAmount;
    private List<Bid> bids = new ArrayList<>();
}
