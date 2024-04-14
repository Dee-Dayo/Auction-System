package africa.semicolon.AuctionSystem.services;

import africa.semicolon.AuctionSystem.data.models.User;
import africa.semicolon.AuctionSystem.dto.requests.BidItemRequest;
import africa.semicolon.AuctionSystem.dto.requests.CreateItemRequest;
import africa.semicolon.AuctionSystem.dto.requests.UserLoginRequest;
import africa.semicolon.AuctionSystem.dto.requests.UserRegisterRequest;
import africa.semicolon.AuctionSystem.dto.responses.CreateBidResponse;
import africa.semicolon.AuctionSystem.dto.responses.CreateItemResponse;
import africa.semicolon.AuctionSystem.dto.responses.UserLoginResponse;
import africa.semicolon.AuctionSystem.dto.responses.UserRegisterResponse;

public interface UserServices {
    UserRegisterResponse register(UserRegisterRequest userRegisterRequest);
    UserLoginResponse login(UserLoginRequest userLoginRequest);
    Long countNoOfUsers();
    boolean isUserLoggedIn(String username);
    CreateItemResponse addItem(CreateItemRequest createItemRequest);
    User findUserByName(String username);
    CreateBidResponse bidItem(BidItemRequest bidItemRequest);
}
