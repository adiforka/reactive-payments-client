import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.lang.annotation.Documented;
import java.time.Instant;

//Mongo's entity
@Document
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Payment {

    @Id
    private String id;
    @NonNull
    private String transactionId;
    @NonNull
    private Long amount;
    @NonNull
    private Instant timestamp;

    boolean hasTransactionId() {
        return !transactionId.isEmpty();
    }
}
