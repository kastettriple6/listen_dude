package com.bot.listen_dude.service;

import com.bot.listen_dude.entity.VoiceEntity;
import com.bot.listen_dude.repository.VoiceMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Voice;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class VoiceService {

    @Autowired
    private VoiceMongoRepository repository;

    public void saveVoice(Voice voice, String title) {
        VoiceEntity voiceEntity = new VoiceEntity();
        voiceEntity.setVoiceId("voice_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")));
        voiceEntity.setVoiceTitle(title);
        voiceEntity.setVoiceFileId(voice.getFileId());

        repository.insert(voiceEntity);
    }
}
