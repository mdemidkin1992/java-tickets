import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class Ticket {
    @JsonProperty("origin")
    private String origin;

    @JsonProperty("origin_name")
    private String originName;

    @JsonProperty("destination")
    private String destination;

    @JsonProperty("destination_name")
    private String destinationName;

    @JsonFormat(pattern = "dd.MM.yy")
    @JsonProperty("departure_date")
    private LocalDate departureDate;

    @JsonFormat(pattern = "H:mm")
    @JsonProperty("departure_time")
    private LocalTime departureTime;

    @JsonFormat(pattern = "dd.MM.yy")
    @JsonProperty("arrival_date")
    private LocalDate arrivalDate;

    @JsonFormat(pattern = "H:mm")
    @JsonProperty("arrival_time")
    private LocalTime arrivalTime;

    @JsonProperty("carrier")
    private String carrier;

    @JsonProperty("stops")
    private Integer stops;

    @JsonProperty("price")
    private Integer price;
}
