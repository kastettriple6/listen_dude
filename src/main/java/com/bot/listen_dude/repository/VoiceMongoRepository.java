package com.bot.listen_dude.repository;

import com.bot.listen_dude.entity.VoiceEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VoiceMongoRepository extends MongoRepository<VoiceEntity, String> {
}
