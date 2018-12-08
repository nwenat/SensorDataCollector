package data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    public UserMetrics() {
    }

    public UserMetrics(@NotNull LocalDateTime dateTime, @NotNull int incoming, @NotNull int outgoing, @NotNull int missed, @NotNull int inboxSMS, @NotNull int outboxSMS) {
        this.dateTime = dateTime;
        this.incoming = incoming;
        this.outgoing = outgoing;
        this.missed = missed;
        this.inboxSMS = inboxSMS;
        this.outboxSMS = outboxSMS;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
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

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
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

    public String getDataString () {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return dateTime.format(formatter);
    }
}
