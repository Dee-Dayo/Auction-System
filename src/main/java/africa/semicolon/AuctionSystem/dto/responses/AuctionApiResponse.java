package africa.semicolon.AuctionSystem.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuctionApiResponse {
    private boolean isSuccessful;
    private Object response;
}
