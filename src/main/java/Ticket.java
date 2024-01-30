import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
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

    private String departureZone;

    private String arrivalZone;

    public void setDepartureZone(String origin) {
        this.departureZone = getTimeZone(origin);
    }

    public void setArrivalZone(String destination) {
        this.arrivalZone = getTimeZone(destination);
    }

    private String getTimeZone(String location) {
        return switch (location) {
            case "VVO" -> "Asia/Vladivostok";
            case "UFA" -> "Europe/Ufa";
            case "LRN" -> "Asia/Nicosia";
            case "TLV" -> "Asia/Jerusalem";
            default -> "UTC";
        };
    }
}
