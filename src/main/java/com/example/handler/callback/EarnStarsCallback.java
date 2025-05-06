package com.example.handler.callback;

import com.example.MyTelegramBot;
import com.example.utils.KeyboardUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Slf4j
@Component
@RequiredArgsConstructor
public class EarnStarsCallback implements Callback {

    private final MyTelegramBot bot;

    @Override
    public void apply(Update update) {
        long chatId = update.getCallbackQuery().getMessage().getChatId();
        String userName = update.getCallbackQuery().getFrom().getUserName();
        long userId = update.getCallbackQuery().getFrom().getId();
        log.info("[{}] Коллбэк {} от пользователя {} [id={}]", update.getUpdateId(), getType(), userName, userId);

        SendMessage message = SendMessage.builder()
                .chatId(chatId)
                .text("""
                        To get stars perform tasks, click on any of them👇
                        """)
                .replyMarkup(KeyboardUtils.getTasksBoardKeyboard(1))
                .build();

        bot.sendNewMessage(message);
    }

    @Override
    public CallbackType getType() {
        return CallbackType.EARN;
    }
}
