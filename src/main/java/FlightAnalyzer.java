import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FlightAnalyzer {
    private final List<Ticket> tickets;

    public FlightAnalyzer(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public Map<String, Duration> getMinimumFlightDurations() {
        Map<String, Duration> minDurations = new HashMap<>();

        for (Ticket t : tickets) {
            if (filterTicket(t)) {
                String carrier = t.getCarrier();
                Duration duration = getDuration(t);

                if (!minDurations.containsKey(carrier) || minDurations.get(carrier).compareTo(duration) > 0) {
                    minDurations.put(carrier, duration);
                }
            }
        }

        return minDurations;
    }

    private Duration getDuration(Ticket ticket) {
        ZonedDateTime departureTime = ticket.getDepartureZonedTime();
        ZonedDateTime arrivalTime = ticket.getArrivalZonedTime();
        return Duration.between(departureTime, arrivalTime);
    }

    public double getPriceDifference() {
        double avgPrice = getAveragePrice();
        double medPrice = getMedianPrice();
        return avgPrice - medPrice;
    }

    private double getAveragePrice() {
        return tickets.stream()
                .filter(this::filterTicket)
                .mapToDouble(Ticket::getPrice)
                .average()
                .orElse(0.0);
    }

    private boolean filterTicket(Ticket t) {
        return t.getDestinationName().equals("Тель-Авив") && t.getOriginName().equals("Владивосток");
    }

    private double getMedianPrice() {
        List<Integer> prices = tickets.stream()
                .filter(this::filterTicket)
                .map(Ticket::getPrice)
                .sorted()
                .toList();

        if (prices.size() % 2 == 0) {
            return (prices.get(prices.size() / 2 - 1) + prices.get(prices.size() / 2 + 1)) / 2.0;
        } else {
            return prices.get(prices.size() / 2);
        }
    }
}
