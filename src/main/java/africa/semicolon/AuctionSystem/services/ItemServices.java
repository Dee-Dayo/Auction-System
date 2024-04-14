package africa.semicolon.AuctionSystem.services;

import africa.semicolon.AuctionSystem.data.models.Item;
import africa.semicolon.AuctionSystem.dto.requests.BidItemRequest;
import africa.semicolon.AuctionSystem.dto.responses.CreateBidResponse;

import java.util.List;

public interface ItemServices {

    void addItem(Item item);
    List<Item> getAllItems();

    CreateBidResponse addBidToItem(BidItemRequest bidItemRequest);
}
