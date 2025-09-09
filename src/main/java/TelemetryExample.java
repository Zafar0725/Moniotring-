import io.micrometer.core.instrument.Gauge;
import io.micrometer.prometheus.PrometheusMeterRegistry;
import java.util.Random;

public class TelemetryExample {
    public static void main(String[] args) throws InterruptedException {
        // Create a Prometheus registry
        PrometheusMeterRegistry registry =
                new PrometheusMeterRegistry(io.micrometer.prometheus.PrometheusConfig.DEFAULT);

        Random random = new Random();

        // Register a temperature gauge (20°C to 30°C)
        Gauge.builder("temperature_celsius", () -> random.nextInt(20, 30))
                .description("Temperature in Celsius")
                .register(registry);

        // Continuously print metrics every 5 seconds
        while (true) {
            System.out.println(registry.scrape());
            Thread.sleep(5000);
        }
    }
}
