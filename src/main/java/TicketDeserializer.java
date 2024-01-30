import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class TicketDeserializer extends JsonDeserializer<Ticket> {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yy H:mm");

    private static final Map<String, String> ZONE_ID_MAP = Map.of(
            "VVO", "Asia/Vladivostok",
            "UFA", "Asia/Yekaterinburg",
            "LRN", "Asia/Nicosia",
            "TLV", "Asia/Jerusalem"
    );

    @Override
    public Ticket deserialize(JsonParser p, DeserializationContext context) throws IOException {
        JsonNode node = p.getCodec().readTree(p);

        Ticket ticket = new Ticket();

        ticket.setOrigin(node.get("origin").asText());
        ticket.setOriginName(node.get("origin_name").asText());
        ticket.setDestination(node.get("destination").asText());
        ticket.setDestinationName(node.get("destination_name").asText());
        ticket.setCarrier(node.get("carrier").asText());
        ticket.setStops(node.get("stops").asInt());
        ticket.setPrice(node.get("price").asInt());

        LocalDateTime arrivalDateTime = parseDateTime(node, "arrival_date", "arrival_time");
        ticket.setArrivalZonedTime(getZonedDateTime(node, arrivalDateTime, "destination"));

        LocalDateTime departureDateTime = parseDateTime(node, "departure_date", "departure_time");
        ticket.setDepartureZonedTime(getZonedDateTime(node, departureDateTime, "origin"));

        return ticket;
    }

    private LocalDateTime parseDateTime(JsonNode node, String dateField, String timeField) {
        String date = node.get(dateField).asText();
        String time = node.get(timeField).asText();
        return LocalDateTime.parse(date + " " + time, FORMATTER);
    }

    private ZonedDateTime getZonedDateTime(JsonNode node, LocalDateTime localDateTime, String zoneField) {
        String zone = node.get(zoneField).asText();
        ZoneId zoneId = ZoneId.of(ZONE_ID_MAP.getOrDefault(zone, "UTC"));
        return ZonedDateTime.of(localDateTime, zoneId);
    }
}