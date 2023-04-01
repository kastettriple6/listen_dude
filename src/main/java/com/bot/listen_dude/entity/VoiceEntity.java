package com.bot.listen_dude.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Column;
import javax.persistence.Id;

@Data
@Document("voice_entity")
public class VoiceEntity {
    @Id
    @Column(name = "voice_id")
    private String voiceId;
    @Column(name = "voice_file_id")
    private String voiceFileId;
    @Column(name = "voice_title")
    private String voiceTitle;
    @Column(name = "voice_caption")
    private String voiceCaption;

    public VoiceEntity() {
    }
}
