package data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "USER_METRICS")
public class UserMetrics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dataTime")
    @NotNull
    private LocalDateTime dateTime;

    @Column(name = "INCOMING")
    @NotNull
    private int incoming;

    @Column(name = "OUT")
    @NotNull
    private int outgoing;

    @Column(name = "MISSED")
    @NotNull
    private int missed;

    @Column(name = "INBOX_SMS")
    @NotNull
    private int inboxSMS;

    @Column(name = "OUTBOX_SMS")
    @NotNull
    private int outboxSMS;

}
