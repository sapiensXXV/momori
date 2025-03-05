package com.momori.quizdraft.presentation.dto.imagemcq;

import lombok.Getter;

import java.util.List;


@Getter
public class DraftImageMcqDetailResponse {
    private final String draftId;
    private final String quizType;
    private final String thumbnailUrl;
    private final String title;
    private final String description;
    private final List<DraftImageMcqQuestionResponse> questions;

    private DraftImageMcqDetailResponse(
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

    public static DraftImageMcqDetailResponse of(
        final String draftId,
        final String quizType,
        final String thumbnailUrl,
        final String title,
        final String description,
        final List<DraftImageMcqQuestionResponse> questions
    ) {

        return new DraftImageMcqDetailResponse(draftId, quizType, thumbnailUrl, title, description, questions);
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
        private final String content;
        private final boolean answer;

        private DraftImageMcqChoiceResponse(
            final String content,
            final boolean answer
        ) {
            this.content = content;
            this.answer = answer;
        }

        public static DraftImageMcqChoiceResponse of(
            final String content,
            final boolean isAnswer
        ) {
            return new DraftImageMcqChoiceResponse(content, isAnswer);
        }
    }

}


