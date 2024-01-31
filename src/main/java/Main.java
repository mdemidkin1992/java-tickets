import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Ticket> tickets = deserializeTickets("tickets.json");
        FlightAnalyzer analyzer = new FlightAnalyzer(tickets);

        System.out.println("Минимальное время полета между городами Владивосток и Тель-Авив " +
                "для каждого авиаперевозчика:");

        for (var entry : analyzer.getMinimumFlightDurations("TLV", "VVO").entrySet()) {
            System.out.print(entry.getKey() + ": ");
            System.out.print(entry.getValue().toHoursPart() + " часов " + entry.getValue().toMinutesPart() + " минут\n");
        }

        System.out.println("Разница между средней ценой и медианой для " +
                "полета между городами Владивосток и Тель-Авив:");
        System.out.print(analyzer.getPriceDifference("TLV", "VVO"));
    }

    private static List<Ticket> deserializeTickets(String filename) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            return objectMapper.readValue(new File(filename), FlightTable.class).getTickets();
        } catch (IOException ex) {
            ex.printStackTrace();
            return Collections.emptyList();
        }
    }
}
