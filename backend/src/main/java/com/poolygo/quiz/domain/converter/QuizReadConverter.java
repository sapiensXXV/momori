package com.poolygo.quiz.domain.converter;

import com.poolygo.global.exception.ExceptionCode;
import com.poolygo.global.exception.QuizException;
import com.poolygo.quiz.domain.*;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Component
@RequiredArgsConstructor
public class QuizReadConverter implements Converter<Document, Quiz> {

    private final MappingMongoConverter mappingMongoConverter;

    @Override
    public Quiz convert(Document source) {
        // 공통 필드 매핑
        String quizId = source.getString("_id");
        LocalDateTime createdAt = (LocalDateTime) source.get("createdAt");
        String description = (String) source.get("description");
        String quizType = (String) source.get("type");
        String thumbnailUrl = (String) source.get("thumbnailUrl");
        int views = (Integer) source.get("views");
        int tries = (Integer) source.get("tries");
        int likes = (Integer) source.get("likes");
        List<Integer> scoreDistribution = (List<Integer>) source.get("scoreDistribution");

        List<Document> questionDocs = (List<Document>) source.get("questions");
        List<Question> questions = new ArrayList<>();

        for (Document doc : questionDocs) {
            String typeAlias = doc.getString("_class");
            Question question = null;

            switch (typeAlias) {
                case "imageMcqQuestion":
                    question = mappingMongoConverter.read(ImageMcqQuestion.class, doc);
                    break;
                case "imageSubQuestion":
                    question = mappingMongoConverter.read(ImageSubQuestion.class, doc);
                    break;
                case "audioMcqQuestion":
                    question = mappingMongoConverter.read(AudioMcqQuestion.class, doc);
                    break;
                case "audioSubQuestion":
                    question = mappingMongoConverter.read(AudioSubQuestion.class, doc);
                    break;
                case "imageBinaryQuestion":
                    question = mappingMongoConverter.read(BinaryChoiceQuestion.class, doc);
                    break;
                default:
                    throw new QuizException(ExceptionCode.QUIZ_DB_MAPPING_FAIL);
            }

            questions.add(question);
        }

        return Quiz.builder()
            .id(quizId)
            .createdAt(createdAt)
            .description(description)
            .thumbnailUrl(thumbnailUrl)
            .type(QuizType.from(quizType))
            .views(views)
            .tries(tries)
            .likes(likes)
            .scoreDistribution(scoreDistribution)
            .questions(questions)
            .build();

    }
}
