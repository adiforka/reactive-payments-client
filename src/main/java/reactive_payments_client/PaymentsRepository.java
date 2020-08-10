package reactive_payments_client;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

//see what is extended
public interface PaymentsRepository extends ReactiveMongoRepository<Payment, String> {
}
