package com.momori.global.config.mongo;


import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.momori.quiz.domain.converter.QuizReadConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

@Configuration
@RequiredArgsConstructor
public class MongoConfig extends AbstractMongoClientConfiguration {

    @Value("${spring.data.mongodb.uri}")
    private String mongoUri;

    @Value("${spring.data.mongodb.database}")
    private String databaseName;

    @Override
    public MongoClient mongoClient() {
        return MongoClients.create(mongoUri);
    }

    @Override
    protected String getDatabaseName() {
        return databaseName;
    }

    @Override
    protected void configureConverters(
        MongoCustomConversions.MongoConverterConfigurationAdapter adapter
    ) {
        super.configureConverters(adapter);
        adapter.registerConverter(new QuizReadConverter());
    }
}
