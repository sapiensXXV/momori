package com.poolygo.quiz.domain.mapper;

import com.poolygo.quiz.domain.ImageMcqChoice;
import com.poolygo.quiz.domain.ImageMcqQuestion;
import com.poolygo.quiz.presentation.dto.response.detail.ImageMcqQuestionDetailResponse;
import com.poolygo.quiz.presentation.dto.response.detail.McqChoiceDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ImageMcqMapper {

    ImageMcqMapper INSTANCE = Mappers.getMapper(ImageMcqMapper.class);

    // ImageMcqQuestion 을 ImageMcqQuestionDetailResponse 로 매핑
    @Mapping(target = "imageUrl", source = "imageUrl")
    @Mapping(target = "choices", source = "choices")
    ImageMcqQuestionDetailResponse toImageMcqQuestionDetailResponse(ImageMcqQuestion question);

    // ImageMcqChoice 를 ImageMcqChoiceDetailResponse 로 매핑
    default McqChoiceDetail toImageMcqChoiceDetailResponse(ImageMcqChoice choice) {
        return new McqChoiceDetail(choice.getContent(), choice.isAnswer());
    }

    // List<McqChoiceDetail에 대한 매핑은 MapperStruct가 자동으로 처리해준다.
    List<McqChoiceDetail> toMcqChoiceDetailList(List<ImageMcqChoice> choices);
}
