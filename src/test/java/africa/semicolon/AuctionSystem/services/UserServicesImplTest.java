package africa.semicolon.AuctionSystem.services;

import africa.semicolon.AuctionSystem.data.repositories.UserRepository;
import africa.semicolon.AuctionSystem.dto.requests.BidItemRequest;
import africa.semicolon.AuctionSystem.dto.requests.CreateItemRequest;
import africa.semicolon.AuctionSystem.dto.requests.UserLoginRequest;
import africa.semicolon.AuctionSystem.dto.requests.UserRegisterRequest;
import africa.semicolon.AuctionSystem.data.models.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class UserServicesImplTest {

    @Autowired
    UserServices userServices;
    @Autowired
    UserRepository userRepository;


    private UserRegisterRequest userRegisterRequest;
    private UserLoginRequest userLoginRequest;
    private CreateItemRequest createItemRequest;


    @BeforeEach
    public void setUp() {
        userRepository.deleteAll();

        userRegisterRequest = new UserRegisterRequest();
        userRegisterRequest.setFirstName("Firstname");
        userRegisterRequest.setLastName("Lastname");
        userRegisterRequest.setPassword("password");
        userRegisterRequest.setUsername("username");

        userLoginRequest = new UserLoginRequest();
        userLoginRequest.setUsername("username");
        userLoginRequest.setPassword("password");

        createItemRequest = new CreateItemRequest();
        createItemRequest.setItemOwner("username");
        createItemRequest.setItemName("Art");
        createItemRequest.setDescription("Picture by Da Vinci");
        createItemRequest.setItemPrice(1000);
    }

    @Test
    public void userRegisters_userCountIsOne() {
        userServices.register(userRegisterRequest);
        assertEquals(1, userRepository.count());
    }

    @Test
    public void registerOneUser_userCanLogin() {
        userServices.register(userRegisterRequest);
        assertEquals(1, userServices.countNoOfUsers());
        assertFalse(userServices.isUserLoggedIn("username"));

        userServices.login(userLoginRequest);
        assertTrue(userServices.isUserLoggedIn("useRNAME"));
    }

    @Test
    public void registeredUserCanHaveItem() {
        userServices.register(userRegisterRequest);
        userServices.login(userLoginRequest);

        userServices.addItem(createItemRequest);
        assertEquals(1, userServices.findUserByName("username").getItems().size());
    }

    @Test
    public void registeredUserCanBidForItem() {
        userServices.register(userRegisterRequest);
        userServices.login(userLoginRequest);

        userServices.addItem(createItemRequest);
        Item item = userServices.findUserByName("username").getItems().getFirst();

        BidItemRequest bidItemRequest = new BidItemRequest();
        bidItemRequest.setBidderName("username");
        bidItemRequest.setItemId(item.getId());
        bidItemRequest.setBidAmount(1200);
        userServices.bidItem(bidItemRequest);

        assertEquals(1, userServices.findUserByName("username").getItems().getFirst().getBids().size());
    }
}