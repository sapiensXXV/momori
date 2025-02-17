package com.poolygo.global.config.mongo;


import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.poolygo.quiz.domain.converter.QuizReadConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

@Configuration
@RequiredArgsConstructor
public class MongoConfig extends AbstractMongoClientConfiguration {

    @Override
    public MongoClient mongoClient() {
        return MongoClients.create("mongodb+srv://jh:jh980101^^@poolygo-cluster.g7kmw.mongodb.net/?retryWrites=true&w=majority&appName=poolygo-cluster");
    }

    @Override
    protected String getDatabaseName() {
        return "poolygo";
    }

    @Override
    protected void configureConverters(
        MongoCustomConversions.MongoConverterConfigurationAdapter adapter
    ) {
        super.configureConverters(adapter);
        adapter.registerConverter(new QuizReadConverter());
    }
}
