/*package com.bot.listen_dude.service;

import com.bot.listen_dude.entity.VideoEntity;
import com.bot.listen_dude.entity.VoiceEntity;
import com.bot.listen_dude.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Video;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class VideoService {

    @Autowired
    private VideoRepository repository;

    public void saveVoice(Video video, String title) {
        VideoEntity videoEntity = VideoEntity.builder()
                .id("video_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")))
                .fileId(video.getFileId())
                .title(title)
                .build();

        repository.save(videoEntity);
    }

    public VoiceEntity getVoice(String title) {
        return repository.findByTitle(title);
    }
}*/
