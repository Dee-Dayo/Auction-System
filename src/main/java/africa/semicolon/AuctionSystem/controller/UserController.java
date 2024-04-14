package africa.semicolon.AuctionSystem.controller;

import africa.semicolon.AuctionSystem.data.models.Item;
import africa.semicolon.AuctionSystem.dto.requests.BidItemRequest;
import africa.semicolon.AuctionSystem.dto.requests.CreateItemRequest;
import africa.semicolon.AuctionSystem.dto.requests.UserLoginRequest;
import africa.semicolon.AuctionSystem.dto.requests.UserRegisterRequest;
import africa.semicolon.AuctionSystem.dto.responses.UserRegisterResponse;
import africa.semicolon.AuctionSystem.exceptions.AuctionSystemExceptions;
import africa.semicolon.AuctionSystem.dto.responses.*;
import africa.semicolon.AuctionSystem.services.ItemServices;
import africa.semicolon.AuctionSystem.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.HttpStatus.FORBIDDEN;

@RestController
@RequestMapping("/api/Auction")
public class UserController {

    @Autowired
    UserServices userServices;
    @Autowired
    ItemServices itemServices;

    @PostMapping("/sign_up")
    public ResponseEntity<?> register(@RequestBody UserRegisterRequest userRegisterRequest){
        try {
            UserRegisterResponse response = userServices.register(userRegisterRequest);
            return new ResponseEntity<>(new AuctionApiResponse(true, response), CREATED);
        } catch(AuctionSystemExceptions e){
            return new ResponseEntity<>(new AuctionApiResponse(false, e.getMessage()), BAD_REQUEST);
        }
    }

    @PostMapping("/sign_in")
    public ResponseEntity<?> login(@RequestBody UserLoginRequest userLoginRequest){
        try {
            UserLoginResponse response = userServices.login(userLoginRequest);
            return new ResponseEntity<>(new AuctionApiResponse(true, response), ACCEPTED);
        } catch (AuctionSystemExceptions e){
            return new ResponseEntity<>(new AuctionApiResponse(false, e.getMessage()), FORBIDDEN);
        }
    }

    @GetMapping("/all_items")
    public ResponseEntity<?> getAllItems() {
        try {
            List<Item> response = itemServices.getAllItems();
            return new ResponseEntity<>(new AuctionApiResponse(true, response), OK);
        } catch (AuctionSystemExceptions e){
            return new ResponseEntity<>(new AuctionApiResponse(false, e.getMessage()), BAD_REQUEST);
        }
    }

    @PostMapping("/add_item")
    public ResponseEntity<?> add_item(@RequestBody CreateItemRequest createItemRequest){
        try {
            CreateItemResponse response = userServices.addItem(createItemRequest);
            return new ResponseEntity<>(new AuctionApiResponse(true, response), CREATED);
        } catch (AuctionSystemExceptions e){
            return new ResponseEntity<>(new AuctionApiResponse(false, e.getMessage()), BAD_REQUEST);
        }
    }

    @PutMapping("/add_bid")
    public ResponseEntity<?> add_bid(@RequestBody BidItemRequest bidItemRequest){
        try {
            CreateBidResponse response = userServices.bidItem(bidItemRequest);
            return new ResponseEntity<>(new AuctionApiResponse(true, response), CREATED);

        } catch (AuctionSystemExceptions e){
            return new ResponseEntity<>(new AuctionApiResponse(false, e.getMessage()), BAD_REQUEST);
        }
    }
}
