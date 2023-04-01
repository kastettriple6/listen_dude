CREATE TABLE voice_entity (
  voiceId          INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT,
  voiceFileId      VARCHAR(255) NOT NULL,
  voiceTitle       VARCHAR(64),
  voiceCaption     VARCHAR(64) NOT NULL);