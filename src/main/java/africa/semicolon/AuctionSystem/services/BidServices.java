package africa.semicolon.AuctionSystem.services;

import africa.semicolon.AuctionSystem.data.models.Bid;
import africa.semicolon.AuctionSystem.dto.requests.BidItemRequest;

public interface BidServices {

    Bid saveBid(BidItemRequest bidItemRequest);
}
