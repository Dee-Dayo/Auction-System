package africa.semicolon.AuctionSystem.utils;
import africa.semicolon.AuctionSystem.data.models.Bid;
import africa.semicolon.AuctionSystem.data.models.Item;
import africa.semicolon.AuctionSystem.data.models.User;
import africa.semicolon.AuctionSystem.dto.requests.BidItemRequest;
import africa.semicolon.AuctionSystem.dto.requests.CreateItemRequest;
import africa.semicolon.AuctionSystem.dto.requests.UserLoginRequest;
import africa.semicolon.AuctionSystem.dto.requests.UserRegisterRequest;
import africa.semicolon.AuctionSystem.dto.responses.CreateItemResponse;
import africa.semicolon.AuctionSystem.dto.responses.UserLoginResponse;
import africa.semicolon.AuctionSystem.dto.responses.*;

import java.time.format.DateTimeFormatter;

public class Mapper {

    public static User requestMap(UserRegisterRequest userRegisterRequest){
        User user = new User();
        user.setUsername(userRegisterRequest.getUsername().toLowerCase());
        user.setPassword(userRegisterRequest.getPassword());
        user.setFirstName(userRegisterRequest.getFirstName());
        user.setLastName(userRegisterRequest.getLastName());
        return user;
    }

    public static Item requestMap(CreateItemRequest createItemRequest){
        Item item = new Item();
        item.setName(createItemRequest.getItemName());
        item.setDescription(createItemRequest.getDescription());
        item.setStartingBidAmount(createItemRequest.getItemPrice());
        return item;
    }

    public static Bid requestMap(BidItemRequest bidItemRequest){
        Bid bid = new Bid();
        bid.setBidderName(bidItemRequest.getBidderName());
        bid.setItemId(bidItemRequest.getItemId());
        bid.setBidAmount(bidItemRequest.getBidAmount());
        return bid;
    }

     public static UserRegisterResponse responseMap(User user){
        UserRegisterResponse response = new UserRegisterResponse();
        response.setUsername(user.getUsername());
        response.setId(user.getId());
        response.setDateCreated(DateTimeFormatter.ofPattern("dd/MM/yyyy, hh:mm:ss").format(user.getDateCreated()));
        return response;
    }

    public static UserLoginResponse loginResponseMap(User user){
        UserLoginResponse response = new UserLoginResponse();
        response.setUsername(user.getUsername());
        response.setId(user.getId());
        return response;
    }

    public static CreateItemResponse createItemResponse(Item item){
        CreateItemResponse response = new CreateItemResponse();
        response.setItemId(item.getId());
        response.setItemName(item.getName());
        response.setItemDescription(item.getDescription());
        response.setItemPrice(item.getStartingBidAmount());
        return response;
    }

    public static CreateBidResponse createBidResponse(Bid bid){
        CreateBidResponse response = new CreateBidResponse();
        response.setBidId(bid.getId());
        response.setBidderName(bid.getBidderName());
        response.setBiddingPrice(bid.getBidAmount());
        return response;
    }
}
