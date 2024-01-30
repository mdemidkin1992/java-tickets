import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
@JsonDeserialize(using = TicketDeserializer.class)
public class Ticket {
    private String origin;
    private String originName;
    private String destination;
    private String destinationName;
    private ZonedDateTime departureZonedTime;
    private ZonedDateTime arrivalZonedTime;
    private String carrier;
    private Integer stops;
    private Integer price;
}
