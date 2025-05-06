package com.example.handler.callback.task;

import com.example.MyTelegramBot;
import com.example.handler.callback.Callback;
import com.example.handler.callback.CallbackType;
import com.example.model.Task;
import com.example.utils.KeyboardUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Slf4j // Автоматически добавляет логгер (log) в класс с помощью Lombok
@Component // Делаем класс Spring-компонентом, чтобы он автоматически подхватывался и внедрялся
public class Task1 extends Task implements Callback {

    private final MyTelegramBot bot;

    // Конструктор, в котором задаются параметры задания через super():
    // taskCode = "task1"
    // description = "Подпишись на канал"
    // link = "Ссылка"
    // rewardStars = 20
    // isActive = true
    public Task1(MyTelegramBot bot){
        super("task1", "Subscribe to the channel", "Link",
                20, true);
        this.bot = bot;
    }

    // Метод вызывается при нажатии пользователем на кнопку задания
    @Override
    public void apply(Update update) {
        long chatId = update.getCallbackQuery().getMessage().getChatId(); // Получаем ID чата для отправки сообщения
        String userName = update.getCallbackQuery().getFrom().getUserName(); // Имя пользователя
        long userId = update.getCallbackQuery().getFrom().getId(); // Telegram ID пользователя

        // Логируем вызов
        log.info("[{}] Коллбэк {} от пользователя {} [id={}]",
                update.getUpdateId(), getType(), userName, userId);

        // Формируем сообщение с описанием задания и кнопкой "Проверить"
        SendMessage message = SendMessage.builder()
                .chatId(chatId)
                .text(getDescription()) // Например: "Подпишись на канал"
                .replyMarkup(KeyboardUtils.buildCheckButton(getTaskCode())) // Кнопка: "Проверить"
                .build();

        // Отправляем сообщение через нашего Telegram-бота
        bot.sendNewMessage(message);
    }

    // Возвращает уникальный код задания (используется для кнопок и проверки)
    @Override
    public String getTaskCode() {
        return "task1";
    }

    // Возвращает тип коллбэка (чтобы определить, какой обработчик запускать)
    @Override
    public CallbackType getType() {
        return CallbackType.TASK1;
    }
}
