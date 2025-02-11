package com.poolygo.quiz.domain.mapper;

import com.poolygo.quiz.domain.ImageMcqChoice;
import com.poolygo.quiz.domain.ImageMcqQuestion;
import com.poolygo.quiz.presentation.dto.response.detail.ImageMcqQuestionDetailResponse;
import com.poolygo.quiz.presentation.dto.response.detail.McqChoiceDetail;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-11T22:51:03+0900",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.11.1.jar, environment: Java 21.0.4 (Eclipse Adoptium)"
)
@Component
public class ImageMcqMapperImpl implements ImageMcqMapper {

    @Override
    public ImageMcqQuestionDetailResponse toImageMcqQuestionDetailResponse(ImageMcqQuestion question) {
        if ( question == null ) {
            return null;
        }

        ImageMcqQuestionDetailResponse imageMcqQuestionDetailResponse = new ImageMcqQuestionDetailResponse();

        imageMcqQuestionDetailResponse.setImageUrl( question.getImageUrl() );
        imageMcqQuestionDetailResponse.setChoices( toMcqChoiceDetailList( question.getChoices() ) );
        imageMcqQuestionDetailResponse.setQuestionId( question.getQuestionId() );
        imageMcqQuestionDetailResponse.setTryCount( question.getTryCount() );
        imageMcqQuestionDetailResponse.setCorrectCount( question.getCorrectCount() );

        return imageMcqQuestionDetailResponse;
    }

    @Override
    public McqChoiceDetail toImageMcqChoiceDetailResponse(ImageMcqChoice choice) {
        if ( choice == null ) {
            return null;
        }

        String content = null;
        boolean answer = false;
        int selectedCount = 0;

        content = choice.getContent();
        answer = choice.isAnswer();
        selectedCount = choice.getSelectedCount();

        McqChoiceDetail mcqChoiceDetail = new McqChoiceDetail( content, answer, selectedCount );

        return mcqChoiceDetail;
    }

    @Override
    public List<McqChoiceDetail> toMcqChoiceDetailList(List<ImageMcqChoice> choices) {
        if ( choices == null ) {
            return null;
        }

        List<McqChoiceDetail> list = new ArrayList<McqChoiceDetail>( choices.size() );
        for ( ImageMcqChoice imageMcqChoice : choices ) {
            list.add( toImageMcqChoiceDetailResponse( imageMcqChoice ) );
        }

        return list;
    }
}
