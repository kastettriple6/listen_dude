package com.bot.listen_dude.config;

import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MongoConfig {
    @Bean
    public MongoClient mongo() {
        ConnectionString uri = new ConnectionString("mongodb+srv://listen_dude_bot_aplication:H4RExIMMrvQn8FTK@cluster0.ad3ssen.mongodb.net/?retryWrites=true&w=majority");

        MongoClient mongoClient = MongoClients.create(uri);
        MongoDatabase database = mongoClient.getDatabase("test");
        return mongoClient;
    }
}
