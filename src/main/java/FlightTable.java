import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class FlightTable {

    @JsonProperty("tickets")
    private List<Ticket> tickets;

}
