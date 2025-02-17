package com.poolygo.quiz.domain.converter;

import com.poolygo.global.exception.ExceptionCode;
import com.poolygo.global.exception.QuizException;
import com.poolygo.quiz.domain.*;
import org.bson.Document;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@ReadingConverter
public class QuizReadConverter implements Converter<Document, Quiz> {
    @Override
    public Quiz convert(Document source) {
        // Quiz 공통 필드 매핑
        String quizId = source.getObjectId("_id").toString(); // 퀴즈 ID
        LocalDateTime createdAt = getLocalDateTimeFromDate(source, "createdAt"); // 생성 날짜
        String title = source.getString("title"); // 제목
        String description = source.getString("description"); // 설명
        String quizType = source.getString("type"); // 퀴즈 타입
        String thumbnailUrl = source.getString("thumbnailUrl"); // 썸네일 URL
        int views = source.getInteger("views", 0); // 조회수
        int tries = source.getInteger("tries", 0); // 시도 횟수
        int likes = source.getInteger("likes", 0); // 좋아요 횟수
        List<Integer> scoreDistribution = (List<Integer>) source.get("scoreDistribution"); // 점수 분포

        // 사용자 정보
        Document userInfoDoc = (Document) source.get("userInfo");
        UserInfo userInfo = new UserInfo(userInfoDoc.getString("identifier"), userInfoDoc.getString("provider"));

        List<Document> questionDocs = (List<Document>) source.get("questions"); // 질문 목록
        List<Question> questions = new ArrayList<>();

        if (questionDocs != null) {
            for (Document doc : questionDocs) {
                String typeAlias = doc.getString("_class");
                Question question = convertQuestion(doc, typeAlias);
                questions.add(question);
            }
        }

        return Quiz.builder()
            .id(quizId)
            .createdAt(createdAt)
            .userInfo(userInfo)
            .title(title)
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

    private LocalDateTime getLocalDateTimeFromDate(Document source, String name) {
        Date date = source.getDate(name);
        return date.toInstant()
            .atZone(ZoneId.systemDefault())
            .toLocalDateTime();
    }

    private Question convertQuestion(Document doc, String typeAlias) {
        switch (typeAlias) {
            case "imageMcqQuestion":
                return convertImageMcqQuestion(doc);
            case "imageSubQuestion":
                return convertImageSubQuestion(doc);
            case "audioMcqQuestion":
                return convertAudioMcqQuestion(doc);
            case "audioSubQuestion":
                return convertAudioSubQuestion(doc);
            case "imageBinaryQuestion":
                return convertBinaryChoiceQuestion(doc);
            default:
                throw new QuizException(ExceptionCode.QUIZ_DB_MAPPING_FAIL);
        }
    }

    private ImageMcqQuestion convertImageMcqQuestion(Document doc) {
        String questionId = doc.getString("questionId");
        int tryCount = doc.getInteger("tryCount");
        int correctCount = doc.getInteger("correctCount");
        String imageUrl = doc.getString("imageUrl");
        List<Document> choicesDocs = (List<Document>) doc.get("choices");
        List<TextMcqChoice> choices = new ArrayList<>();
        if (choicesDocs != null) {
            for (Document choiceDoc : choicesDocs) {
                String content = choiceDoc.getString("content");
                boolean answer = choiceDoc.getBoolean("answer");
                int selectedCount = choiceDoc.getInteger("selectedCount", 0);
                // TextMcqChoice 빌더 또는 생성자를 이용하여 객체 생성
                TextMcqChoice choice = TextMcqChoice.builder()
                    .content(content)
                    .answer(answer)
                    .selectedCount(selectedCount)
                    .build();
                choices.add(choice);
            }
        }
        return ImageMcqQuestion.builder()
            .questionId(questionId)
            .tryCount(tryCount)
            .correctCount(correctCount)
            .imageUrl(imageUrl)
            .choices(choices)
            .build();
    }

    private ImageSubQuestion convertImageSubQuestion(Document doc) {
        String questionId = doc.getString("questionId");
        int tryCount = doc.getInteger("tryCount");
        int correctCount = doc.getInteger("correctCount");
        String imageUrl = doc.getString("imageUrl");
        List<String> answers = (List<String>) doc.get("answers");
        return ImageSubQuestion.builder()
            .questionId(questionId)
            .tryCount(tryCount)
            .correctCount(correctCount)
            .imageUrl(imageUrl)
            .answers(answers)
            .build();
    }

    private AudioMcqQuestion convertAudioMcqQuestion(Document doc) {
        String questionId = doc.getString("questionId");
        int tryCount = doc.getInteger("tryCount");
        int correctCount = doc.getInteger("correctCount");
        String audioUrl = doc.getString("audioUrl");
        String content = doc.getString("content");
        boolean answer = doc.getBoolean("answer");
        List<Document> choicesDocs = (List<Document>) doc.get("choices");
        List<TextMcqChoice> choices = new ArrayList<>();
        if (choicesDocs != null) {
            for (Document choiceDoc : choicesDocs) {
                String text = choiceDoc.getString("text");
                int selectedCount = choiceDoc.getInteger("selectedCount", 0);
                TextMcqChoice choice = TextMcqChoice.builder()
                    .content(content)
                    .answer(answer)
                    .selectedCount(selectedCount)
                    .build();
                choices.add(choice);
            }
        }
        return AudioMcqQuestion.builder()
            .questionId(questionId)
            .tryCount(tryCount)
            .correctCount(correctCount)
            .audioUrl(audioUrl)
            .choices(choices)
            .build();
    }

    private AudioSubQuestion convertAudioSubQuestion(Document doc) {
        String questionId = doc.getString("questionId");
        int tryCount = doc.getInteger("tryCount");
        int correctCount = doc.getInteger("correctCount");
        String audioUrl = doc.getString("audioUrl");
        List<String> answers = (List<String>) doc.get("answers");
        return AudioSubQuestion.builder()
            .questionId(questionId)
            .tryCount(tryCount)
            .correctCount(correctCount)
            .audioUrl(audioUrl)
            .answers(answers)
            .build();
    }

    private BinaryChoiceQuestion convertBinaryChoiceQuestion(Document doc) {
        String questionId = doc.getString("questionId");
        int tryCount = doc.getInteger("tryCount");
        int correctCount = doc.getInteger("correctCount");
        Document firstDoc = (Document) doc.get("first");
        Document secondDoc = (Document) doc.get("second");
        BinaryChoiceItem first = convertBinaryChoiceItem(firstDoc);
        BinaryChoiceItem second = convertBinaryChoiceItem(secondDoc);
        return BinaryChoiceQuestion.builder()
            .questionId(questionId)
            .tryCount(tryCount)
            .correctCount(correctCount)
            .first(first)
            .second(second)
            .build();
    }

    private BinaryChoiceItem convertBinaryChoiceItem(Document doc) {
        if (doc == null) {
            return null;
        }
        String imageUrl = doc.getString("imageUrl");
        String description = doc.getString("description");
        int count = doc.getInteger("count", 0);
        return BinaryChoiceItem.builder()
            .imageUrl(imageUrl)
            .description(description)
            .selectedCount(count)
            .build();
    }

}

