package com.poolygo.quiz.domain.mapper;


import com.poolygo.quiz.domain.ImageSubQuestion;
import com.poolygo.quiz.presentation.dto.response.detail.ImageSubQuestionDetailResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ImageSubMapper {

    ImageSubMapper INSTANCE = Mappers.getMapper(ImageSubMapper.class);

    ImageSubQuestionDetailResponse toImageSubQuestionDetailResponse(ImageSubQuestion question);
}
