package africa.semicolon.AuctionSystem.services;

import africa.semicolon.AuctionSystem.data.models.Bid;
import africa.semicolon.AuctionSystem.data.models.Item;
import africa.semicolon.AuctionSystem.data.repositories.ItemRepository;
import africa.semicolon.AuctionSystem.dto.requests.BidItemRequest;
import africa.semicolon.AuctionSystem.dto.responses.CreateBidResponse;
import africa.semicolon.AuctionSystem.exceptions.ItemNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static africa.semicolon.AuctionSystem.utils.Mapper.createBidResponse;

@Service
public class ItemServicesImpl implements ItemServices {

    @Autowired
    ItemRepository itemRepository;
    @Autowired
    BidServices bidServices;

    @Override
    public void addItem(Item item) {
        itemRepository.save(item);
    }

    @Override
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    @Override
    public CreateBidResponse addBidToItem(BidItemRequest bidItemRequest) {
        Item item = findItemById(bidItemRequest.getItemId());
        item.setStartingBidAmount(bidItemRequest.getBidAmount());

        Bid bid = bidServices.saveBid(bidItemRequest);
        item.getBids().add(bid);
        itemRepository.save(item);
        return createBidResponse(bid);
    }

    public Item findItemById(String itemId){
        Optional<Item> item = itemRepository.findById(itemId);

        if (item.isEmpty()) throw new ItemNotFoundException("Item doesn't exist");
        return item.get();
    }

}
