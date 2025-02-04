package com.poolygo.quizdraft.presentation.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;


@Getter
public class DraftImageMcqResponse {
    private final String draftId;
    private final String quizType;
    private final String thumbnailUrl;
    private final String title;
    private final String description;
    private final List<DraftImageMcqQuestionResponse> questions;

    private DraftImageMcqResponse(
        final String draftId,
        final String quizType,
        final String thumbnailUrl,
        final String title,
        final String description,
        final List<DraftImageMcqQuestionResponse> questions
    ) {
        this.draftId = draftId;
        this.quizType = quizType;
        this.thumbnailUrl = thumbnailUrl;
        this.title = title;
        this.description = description;
        this.questions = questions;
    }

    public static DraftImageMcqResponse of(
        final String draftId,
        final String quizType,
        final String thumbnailUrl,
        final String title,
        final String description,
        final List<DraftImageMcqQuestionResponse> questions
    ) {

        return new DraftImageMcqResponse(draftId, quizType, thumbnailUrl, title, description, questions);
    }

    @Getter
    public static class DraftImageMcqQuestionResponse {
        private final String imageUrl;
        private final List<DraftImageMcqChoiceResponse> choices;

        private DraftImageMcqQuestionResponse(
            final String imageUrl,
            final List<DraftImageMcqChoiceResponse> choices
        ) {
            this.imageUrl = imageUrl;
            this.choices = choices;
        }

        public static DraftImageMcqQuestionResponse of(
            final String imageUrl,
            final List<DraftImageMcqChoiceResponse> choices
        ) {
            return new DraftImageMcqQuestionResponse(imageUrl, choices);
        }
    }

//    @Getter
    public static class DraftImageMcqChoiceResponse {
        @JsonProperty("content")
        private final String content;

        @JsonProperty("isAnswer")
        private final boolean isAnswer;

        private DraftImageMcqChoiceResponse(
            final String content,
            final boolean isAnswer
        ) {
            this.content = content;
            this.isAnswer = isAnswer;
        }

        public static DraftImageMcqChoiceResponse of(
            final String content,
            final boolean isAnswer
        ) {
            return new DraftImageMcqChoiceResponse(content, isAnswer);
        }
    }

}


