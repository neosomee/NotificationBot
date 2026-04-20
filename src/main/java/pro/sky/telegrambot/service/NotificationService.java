package pro.sky.telegrambot.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.model.Notification;
import pro.sky.telegrambot.repository.NotificationRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository; // ← final!

    private static final Pattern PATTERN =
            Pattern.compile("(\\d{2}\\.\\d{2}\\.\\d{4}\\s\\d{2}:\\d{2})(\\s+)(.+)");

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"); // двоеточие!

    public void saveNotification(Long chatId, String message) {
        Matcher matcher = PATTERN.matcher(message);
        if (matcher.matches()) {
            String date = matcher.group(1); // "01.01.2028 20:00"
            String text = matcher.group(3);

            Notification notification = new Notification()
                    .setChatId(chatId)
                    .setText(text)
                    .setDate(LocalDateTime.parse(date, FORMATTER));

            notificationRepository.save(notification);
        }
    }

    public List<Notification> getNotifications() {
        return notificationRepository.findAll();
    }

    public void deleteNotification(List<Notification> notifications) {
        notificationRepository.deleteAll(notifications);
    }
}

