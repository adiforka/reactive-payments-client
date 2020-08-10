package reactive_payments_client;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

//lets us place properties into our fields (spring will generate setters for it
@ConfigurationProperties("payments")
@Setter
@Component
@RequiredArgsConstructor
public class PaymentsClientRunner implements CommandLineRunner {

    private String url;
    private String path;
    private final PaymentsRepository paymentsRepository;

    private Flux<Payment> getPayments() {
        return WebClient.builder()
                .baseUrl(url)
                .build()
                .get()
                .uri(path)
                .retrieve()
                .bodyToFlux(Payment.class);

    }

    //get a subscription to the stream of payments
    @Override
    public void run(String... args) throws Exception {
        getPayments()
                .filter(payment -> payment.getAmount() > 5000)
                .buffer(5)
                .subscribe(System.out::println);
    }
}
