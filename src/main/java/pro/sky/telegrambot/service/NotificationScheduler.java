package pro.sky.telegrambot.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.model.Notification;
import pro.sky.telegrambot.repository.NotificationRepository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationScheduler {

    private final TelegramBot telegramBot;
    private final NotificationService notificationService;

    public NotificationScheduler(TelegramBot bot, NotificationService service) {
        this.telegramBot = bot;
        this.notificationService = service;
    }

    @Scheduled(fixedRate = 60000)
    public void sendNotification() {
        List<Notification> notifications = notificationService.getNotifications().stream()
                .filter(it -> LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES).equals(it.getDate()))
                .collect(Collectors.toList());

        notifications.forEach(notification -> {
            telegramBot.execute(new SendMessage(
                    notification.getChatId(),
                    notification.getText())
            );
        });

        notificationService.deleteNotification(notifications);
    }
}
