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

    // 매퍼 클래스에서 ImageMcqMapper 를 찾을 수 있도록 하는 방법
    ImageMcqMapper INSTANCE = Mappers.getMapper(ImageMcqMapper.class);

    ImageMcqQuestionDetailResponse toImageMcqQuestionDetailResponse(ImageMcqQuestion question);

    @Mapping(source = "answer", target = "answer")
    McqChoiceDetail toImageMcqChoiceDetailResponse(ImageMcqChoice choice);

    List<McqChoiceDetail> toMcqChoiceDetailList(List<ImageMcqChoice> choices);
}
