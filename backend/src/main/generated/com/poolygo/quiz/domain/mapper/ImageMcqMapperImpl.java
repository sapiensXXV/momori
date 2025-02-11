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
    date = "2025-02-12T01:09:28+0900",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.11.1.jar, environment: Java 21.0.4 (Eclipse Adoptium)"
)
@Component
public class ImageMcqMapperImpl implements ImageMcqMapper {

    @Override
    public ImageMcqQuestionDetailResponse toImageMcqQuestionDetailResponse(ImageMcqQuestion question) {
        if ( question == null ) {
            return null;
        }

        ImageMcqQuestionDetailResponse.ImageMcqQuestionDetailResponseBuilder imageMcqQuestionDetailResponse = ImageMcqQuestionDetailResponse.builder();

        imageMcqQuestionDetailResponse.imageUrl( question.getImageUrl() );
        imageMcqQuestionDetailResponse.choices( toMcqChoiceDetailList( question.getChoices() ) );

        return imageMcqQuestionDetailResponse.build();
    }

    @Override
    public McqChoiceDetail toImageMcqChoiceDetailResponse(ImageMcqChoice choice) {
        if ( choice == null ) {
            return null;
        }

        McqChoiceDetail.McqChoiceDetailBuilder mcqChoiceDetail = McqChoiceDetail.builder();

        mcqChoiceDetail.content( choice.getContent() );
        mcqChoiceDetail.answer( choice.isAnswer() );
        mcqChoiceDetail.selectedCount( choice.getSelectedCount() );

        return mcqChoiceDetail.build();
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
