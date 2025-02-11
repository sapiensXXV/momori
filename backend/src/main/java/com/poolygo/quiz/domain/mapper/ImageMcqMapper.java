package com.poolygo.quiz.domain.mapper;

import com.poolygo.quiz.domain.ImageMcqChoice;
import com.poolygo.quiz.domain.ImageMcqQuestion;
import com.poolygo.quiz.presentation.dto.response.detail.ImageMcqQuestionDetailResponse;
import com.poolygo.quiz.presentation.dto.response.detail.McqChoiceDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ImageMcqMapper {

    @Mapping(target = "imageUrl", source = "imageUrl")
    @Mapping(target = "choices", source = "choices")
    ImageMcqQuestionDetailResponse toImageMcqQuestionDetailResponse(ImageMcqQuestion question);

    @Mapping(target = "content", source = "content")
    @Mapping(target = "answer", source = "answer")
    @Mapping(target = "selectedCount", source = "selectedCount")
    McqChoiceDetail toImageMcqChoiceDetailResponse(ImageMcqChoice choice);

    List<McqChoiceDetail> toMcqChoiceDetailList(List<ImageMcqChoice> choices);
}
