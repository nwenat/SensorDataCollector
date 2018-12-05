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

//    @Column(name = "dataTime")
//    @NotNull
//    private LocalDateTime dateTime;

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

    public UserMetrics() {
    }

    public UserMetrics(@NotNull int incoming, @NotNull int outgoing, @NotNull int missed, @NotNull int inboxSMS, @NotNull int outboxSMS) {
        this.incoming = incoming;
        this.outgoing = outgoing;
        this.missed = missed;
        this.inboxSMS = inboxSMS;
        this.outboxSMS = outboxSMS;
    }

    public Long getId() {
        return id;
    }

    public int getIncoming() {
        return incoming;
    }

    public int getOutgoing() {
        return outgoing;
    }

    public int getMissed() {
        return missed;
    }

    public int getInboxSMS() {
        return inboxSMS;
    }

    public int getOutboxSMS() {
        return outboxSMS;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setIncoming(int incoming) {
        this.incoming = incoming;
    }

    public void setOutgoing(int outgoing) {
        this.outgoing = outgoing;
    }

    public void setMissed(int missed) {
        this.missed = missed;
    }

    public void setInboxSMS(int inboxSMS) {
        this.inboxSMS = inboxSMS;
    }

    public void setOutboxSMS(int outboxSMS) {
        this.outboxSMS = outboxSMS;
    }
}
