package africa.semicolon.AuctionSystem.services;

import africa.semicolon.AuctionSystem.data.models.Item;
import africa.semicolon.AuctionSystem.data.models.User;
import africa.semicolon.AuctionSystem.data.repositories.UserRepository;
import africa.semicolon.AuctionSystem.dto.requests.BidItemRequest;
import africa.semicolon.AuctionSystem.dto.requests.CreateItemRequest;
import africa.semicolon.AuctionSystem.dto.requests.UserLoginRequest;
import africa.semicolon.AuctionSystem.dto.requests.UserRegisterRequest;
import africa.semicolon.AuctionSystem.dto.responses.CreateBidResponse;
import africa.semicolon.AuctionSystem.dto.responses.CreateItemResponse;
import africa.semicolon.AuctionSystem.dto.responses.UserLoginResponse;
import africa.semicolon.AuctionSystem.dto.responses.UserRegisterResponse;
import africa.semicolon.AuctionSystem.exceptions.InvalidPasswordException;
import africa.semicolon.AuctionSystem.exceptions.UserAlreadyExistException;
import africa.semicolon.AuctionSystem.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import africa.semicolon.AuctionSystem.exceptions.*;

import static africa.semicolon.AuctionSystem.utils.Mapper.*;

@Service
public class UserServicesImpl implements UserServices {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    ItemServices itemServices;

    @Override
    public UserRegisterResponse register(UserRegisterRequest userRegisterRequest) {
        validateUsername(userRegisterRequest.getUsername());

        User newUser = requestMap(userRegisterRequest);
        userRepository.save(newUser);


        return responseMap(newUser);
    }

    @Override
    public UserLoginResponse login(UserLoginRequest userLoginRequest) {
        User user = findUserByName(userLoginRequest.getUsername().toLowerCase());
        validatePassword(userLoginRequest);
        user.setLoggedIn(true);
        userRepository.save(user);
        return loginResponseMap(user);
    }

    @Override
    public Long countNoOfUsers() {
        return userRepository.count();
    }

    @Override
    public boolean isUserLoggedIn(String username) {
        User user = findUserByName(username.toLowerCase());
        return user.isLoggedIn();
    }

    @Override
    public CreateItemResponse addItem(CreateItemRequest createItemRequest) {
        User foundUser = findUserByName(createItemRequest.getItemOwner());
        if(!foundUser.isLoggedIn()) throw new UserNotLoggedInException("You need to log in to add Items");

        Item item = requestMap(createItemRequest);
        itemServices.addItem(item);
        foundUser.getItems().add(item);
        userRepository.save(foundUser);
        return createItemResponse(item);
    }

    public User findUserByName(String username) {
        User user = userRepository.findByUsername(username.toLowerCase());
        if (user == null) throw new UserNotFoundException(username + " does not exist");
        return user;
    }

    @Override
    public CreateBidResponse bidItem(BidItemRequest bidItemRequest) {
        User foundUser = findUserByName(bidItemRequest.getBidderName());
        if(!foundUser.isLoggedIn()) throw new UserNotLoggedInException("You need to log in to bid for Items");

        userRepository.save(foundUser);
        return itemServices.addBidToItem(bidItemRequest);

    }

    private void validatePassword(UserLoginRequest userLoginRequest) {
        User user = findUserByName(userLoginRequest.getUsername());
        if(!user.getPassword().equals(userLoginRequest.getPassword())) throw new InvalidPasswordException("Wrong password");
    }

    private void validateUsername(String username) {
        boolean userExists = userRepository.existsByUsername(username.toLowerCase());
        if (userExists) throw new UserAlreadyExistException(username + " already exist");
    }
}
