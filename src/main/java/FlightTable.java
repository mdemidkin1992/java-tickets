import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class FlightTable {

    @JsonProperty("tickets")
    private List<Ticket> tickets;

}
