package com.example.handler.command;

import com.example.MyTelegramBot;
import com.example.utils.KeyboardUtils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TaskCommand implements Command  {

    private final MyTelegramBot bot;

    public void apply(Update update) {
        var message = update.getMessage();
        var chatId = message.getChatId();

        long userId = message.getFrom().getId();
        String userName = message.getFrom().getUserName();
        log.info("[{}] Команда {} от пользователя {} [id={}]", update.getUpdateId(), getType(), userName, userId);

        var deleteMessage = DeleteMessage.builder()
                .chatId(chatId)
                .messageId(message.getMessageId())
                .build();
        bot.deleteMessage(deleteMessage);

        SendMessage taskMessage = SendMessage.builder()
                .chatId(chatId)
                .text("To get stars perform tasks, click on any of them👇")
                .parseMode("Markdown")
                .replyMarkup(KeyboardUtils.getTasksBoardKeyboard(1))
                .build();

        bot.sendNewMessage(taskMessage);
    }

    @Override
    public CommandType getType() {
        return CommandType.TASKS;
    }
}
