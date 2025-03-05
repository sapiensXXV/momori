package com.momori.quiz.domain.mapper;


import com.momori.quiz.domain.ImageSubQuestion;
import com.momori.quiz.presentation.dto.response.detail.ImageSubQuestionDetailResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ImageSubMapper {

    ImageSubMapper INSTANCE = Mappers.getMapper(ImageSubMapper.class);

    ImageSubQuestionDetailResponse toImageSubQuestionDetailResponse(ImageSubQuestion question);
}
