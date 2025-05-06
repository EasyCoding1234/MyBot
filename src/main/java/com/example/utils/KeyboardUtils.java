package com.example.utils;

import com.example.handler.callback.CallbackType;
import com.example.model.GiftType;
import com.example.model.User;
import lombok.experimental.UtilityClass;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import java.util.*;

@UtilityClass
public class KeyboardUtils {

    public static ReplyKeyboardMarkup buildMainReplyKeyboard() {
        KeyboardRow row1 = new KeyboardRow();
        row1.add(KeyboardButton.builder().text("⭐ Зработать звёзды").build());
        row1.add(KeyboardButton.builder().text("\uD83D\uDCD8 Инструкция").build());
        row1.add(KeyboardButton.builder().text("\uD83C\uDF81 Вывести звёзды").build());

        return ReplyKeyboardMarkup.builder()
                .keyboardRow(row1)
                .resizeKeyboard(true)
                .selective(false)
                .isPersistent(true)
                .build();

    }

    public static InlineKeyboardMarkup buildMenuInlineKeyboard() {
        List<InlineKeyboardButton> row1 = new ArrayList<>();
        row1.add(InlineKeyboardButton.builder()
                .text(CallbackType.EARN.getButtonText())
                .callbackData(CallbackType.EARN.toString())
                .build());

        List<InlineKeyboardButton> row2 = new ArrayList<>();
        row2.add(InlineKeyboardButton.builder()
                .text(CallbackType.INSTRUCTION.getButtonText())
                .callbackData(CallbackType.INSTRUCTION.toString())
                .build());

        List<InlineKeyboardButton> row3 = new ArrayList<>();
        row3.add(InlineKeyboardButton.builder()
                .text(CallbackType.WITHDRAW.getButtonText())
                .callbackData(CallbackType.WITHDRAW.toString())
                .build());


        List<InlineKeyboardButton> row4 = new ArrayList<>();
        row4.add(InlineKeyboardButton.builder()
                .text(CallbackType.REFERRAL.getButtonText())
                .callbackData(CallbackType.REFERRAL.toString())
                .build());

        List<InlineKeyboardButton> row5 = new ArrayList<>();
        row5.add(InlineKeyboardButton.builder()
                .text(CallbackType.HELP.getButtonText())
                .callbackData(CallbackType.HELP.toString())
                .build());

        return InlineKeyboardMarkup.builder()
                .keyboard(List.of(row1, row2, row3, row4, row5))
                .build();
    }

    //Кнопки для выбора заданий
    public static InlineKeyboardMarkup getTasksBoardKeyboard(int page) {
        int tasksPerPage = 5;
        int totalTasks = 50; // допустим 10 заданий, можно заменить на dynamic
        int totalPages = (int) Math.ceil((double) totalTasks / tasksPerPage);

        List<List<InlineKeyboardButton>> rows = new ArrayList<>();

        List<InlineKeyboardButton> task1 = new ArrayList<>();
        task1.add(InlineKeyboardButton.builder()
                .text(CallbackType.TASK1.getButtonText())
                .callbackData(CallbackType.TASK1.toString())
                .build());

        rows.add(task1);

        // Навигация (если страниц больше одной)
        List<InlineKeyboardButton> navigationRow = new ArrayList<>();
        if (page < totalPages) {
            InlineKeyboardButton next = new InlineKeyboardButton();
            next.setText("➡ Следующая страница");
            next.setCallbackData("TASKS_PAGE_" + (page + 1));
            navigationRow.add(next);
        }
        if (page > 1) {
            InlineKeyboardButton prev = new InlineKeyboardButton();
            prev.setText("⬅ Предыдущая страница");
            prev.setCallbackData("TASKS_PAGE_" + (page - 1));
            navigationRow.add(0, prev);
        }
        if (!navigationRow.isEmpty()) rows.add(navigationRow);

        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        markup.setKeyboard(rows);
        return markup;
    }

    //Кнопка проверки заданий
    public static InlineKeyboardMarkup buildCheckButton(String taskCode ) {
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();

        List<InlineKeyboardButton> task1 = new ArrayList<>();
        task1.add(InlineKeyboardButton.builder()
                .text(CallbackType.CHECK.getButtonText())
                .callbackData("CHECK:" + taskCode)
                .build());

        rows.add(task1);

        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        markup.setKeyboard(rows);

        return markup;
    }

    /**
     * Клавиатура со списком подарков
     */
    public InlineKeyboardMarkup buildGiftSelectionKeyboard() {
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();

        rows.add(List.of(createGiftButton(GiftType.BEAR)));
        rows.add(List.of(createGiftButton(GiftType.HEART)));
        rows.add(List.of(createGiftButton(GiftType.ROSE)));
        rows.add(List.of(createGiftButton(GiftType.PRESENT)));
        rows.add(List.of(createGiftButton(GiftType.CAKE)));
        rows.add(List.of(createGiftButton(GiftType.FLOWERS)));
        rows.add(List.of(createGiftButton(GiftType.ROCKET)));
        rows.add(List.of(createGiftButton(GiftType.CHAMPAGNE)));
        rows.add(List.of(createGiftButton(GiftType.TROPHY)));
        rows.add(List.of(createGiftButton(GiftType.RING)));
        rows.add(List.of(createGiftButton(GiftType.DIAMOND)));

        return new InlineKeyboardMarkup(rows);
    }

    /**
     * Создание кнопки с подарком
     */
    private InlineKeyboardButton createGiftButton(GiftType gift) {
        return InlineKeyboardButton.builder()
                .text(gift.getTitle() + " — " + gift.getCost() + "⭐️")
                .callbackData("SELECT_GIFT:" + gift.name() + ":" + gift.getCost())
                .build();
    }

    /**
     * Кнопка подтверждения подарка
     */
    public InlineKeyboardMarkup createConfirmWithdraw(GiftType gift) {
        List<InlineKeyboardButton> row = List.of(
                InlineKeyboardButton.builder()
                        .text("Подтвердить подарок")
                        .callbackData("CONFIRM_WITHDRAW:" + gift.name() + ":" + gift.getCost())
                        .build()
        );
        return new InlineKeyboardMarkup(List.of(row));
    }

    /**
     * Кнопка "Не выполнено" для админа
     */
    public InlineKeyboardMarkup createGiftDoneKeyboard(User user) {
        List<InlineKeyboardButton> row = List.of(
                InlineKeyboardButton.builder()
                        .text("❌ Не выполнено")
                        .callbackData("GIFT_DONE:" + user.getTelegramId())
                        .build()
        );
        return new InlineKeyboardMarkup(List.of(row));
    }
}



