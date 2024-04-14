package africa.semicolon.AuctionSystem.services;

import africa.semicolon.AuctionSystem.data.models.Bid;
import africa.semicolon.AuctionSystem.data.repositories.BidRepository;
import africa.semicolon.AuctionSystem.dto.requests.BidItemRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static africa.semicolon.AuctionSystem.utils.Mapper.requestMap;

@Service
public class BidServicesImpl implements BidServices{

    @Autowired
    private BidRepository bidRepository;


    @Override
    public Bid saveBid(BidItemRequest bidItemRequest) {
        Bid bid = requestMap(bidItemRequest);
        bidRepository.save(bid);
        return bid;
    }
}
