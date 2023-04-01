package com.bot.listen_dude.bots;

import com.bot.listen_dude.entity.VoiceEntity;
import com.bot.listen_dude.repository.VoiceMongoRepository;
import com.bot.listen_dude.service.VoiceService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.AnswerInlineQuery;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.inlinequery.result.InlineQueryResult;
import org.telegram.telegrambots.meta.api.objects.inlinequery.result.cached.InlineQueryResultCachedVoice;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Component
public class AnswerBot extends TelegramLongPollingBot {
    private static final Integer CACHE_TIME = 1000;

    @Value("bot-token")
    private String token;

    @Value("username")
    private String username;

    @Value("admin-chat-id")
    private Long adminChatId;

    @Autowired
    private VoiceService voiceService;

    @Autowired
    private VoiceMongoRepository voiceRepository;

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        if (update.hasInlineQuery()) {
            if (message == null) {
                List<VoiceEntity> voices = voiceRepository.findAll();
                List<VoiceEntity> voicesWithOutDuplicates = new ArrayList<>(new HashSet<>(voices));
                List<InlineQueryResult> results = new ArrayList<>();
                for (VoiceEntity voiceEntity : voicesWithOutDuplicates) {
                    InlineQueryResultCachedVoice result = InlineQueryResultCachedVoice.builder()
                            .id(voiceEntity.getVoiceId())
                            .voiceFileId(voiceEntity.getVoiceFileId())
                            .caption(voiceEntity.getVoiceCaption())
                            .title(voiceEntity.getVoiceTitle())
                            .build();

                    results.add(result);
                }
                AnswerInlineQuery answer = AnswerInlineQuery.builder()
                        .inlineQueryId(update.getInlineQuery().getId())
                        .results(results)
                        .cacheTime(CACHE_TIME)
                        .nextOffset("")
                        .build();
                try {
                    execute(answer);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else if (message.hasVoice()) {
            if (message.getChatId().equals(adminChatId)) {
                if (message.getCaption() == null) {
                    execute(SendMessage.builder()
                            .text("You must fill captiom!")
                            .chatId(String.valueOf(message.getChatId()))
                            .build());
                }
                try {
                    voiceService.saveVoice(message.getVoice(), message.getCaption());
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public String getBotToken() {
        return token;
    }
}
