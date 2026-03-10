package pro.sky.telegrambot.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "notifications")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "chat_id", nullable = false)
    private Long chatId;

    @Column(name = "text", nullable = false, columnDefinition = "TEXT")
    private String text;

    @Column(name = "date", nullable = false)
    private LocalDateTime date;

    public Long getId() { return id; }
    public Notification setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getChatId() { return chatId; }
    public Notification setChatId(Long chatId) {
        this.chatId = chatId;
        return this;
    }

    public String getText() { return text; }
    public Notification setText(String text) {
        this.text = text;
        return this;
    }

    public LocalDateTime getDate() { return date; }
    public Notification setDate(LocalDateTime date) {
        this.date = date;
        return this;
    }
}
