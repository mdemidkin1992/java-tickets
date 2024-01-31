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

    public Map<String, Duration> getMinimumFlightDurations(String destination, String origin) {
        Map<String, Duration> minDurations = new HashMap<>();

        for (Ticket t : tickets) {
            if (filterTicket(t, destination, origin)) {
                String carrier = t.getCarrier();
                Duration duration = getDuration(t);

                if (!minDurations.containsKey(carrier) || minDurations.get(carrier).compareTo(duration) > 0) {
                    minDurations.put(carrier, duration);
                }
            }
        }

        if (minDurations.isEmpty()) {
            System.out.println("Для данных городов билеты не найдены");
        }

        return minDurations;
    }

    private Duration getDuration(Ticket t) {
        ZonedDateTime departure = DateTimeUtil.toZonedDateTime(t.getDepartureDate(), t.getDepartureTime(), t.getOrigin());
        ZonedDateTime arrival = DateTimeUtil.toZonedDateTime(t.getArrivalDate(), t.getArrivalTime(), t.getDestination());
        return Duration.between(departure, arrival);
    }

    public double getPriceDifference(String destination, String origin) {
        double avgPrice = getAveragePrice(destination, origin);
        double medPrice = getMedianPrice(destination, origin);
        return avgPrice - medPrice;
    }

    private double getAveragePrice(String destination, String origin) {
        return tickets.stream()
                .filter(t -> filterTicket(t, destination, origin))
                .mapToDouble(Ticket::getPrice)
                .average()
                .orElse(0.0);
    }

    private double getMedianPrice(String destination, String origin) {
        List<Integer> prices = tickets.stream()
                .filter(t -> filterTicket(t, destination, origin))
                .map(Ticket::getPrice)
                .sorted()
                .toList();

        if (prices.size() % 2 == 0) {
            return (prices.get(prices.size() / 2 - 1) + prices.get(prices.size() / 2 + 1)) / 2.0;
        } else {
            return prices.get(prices.size() / 2);
        }
    }

    private boolean filterTicket(Ticket t, String destination, String origin) {
        return t.getDestination().equals(destination) && t.getOrigin().equals(origin);
    }
}
