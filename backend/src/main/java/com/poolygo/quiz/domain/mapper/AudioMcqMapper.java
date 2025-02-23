package com.poolygo.quiz.domain.mapper;

import com.poolygo.quiz.domain.AudioMcqQuestion;
import com.poolygo.quiz.domain.TextMcqChoice;
import com.poolygo.quiz.presentation.dto.response.detail.AudioMcqQuestionDetailResponse;
import com.poolygo.quiz.presentation.dto.response.detail.McqChoiceDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AudioMcqMapper {

    AudioMcqMapper INSTANCE = Mappers.getMapper(AudioMcqMapper.class);

    AudioMcqQuestionDetailResponse toImageMcqQuestionDetailResponse(AudioMcqQuestion question);

    @Mapping(source="answer", target="answer")
    McqChoiceDetail toMcqChoiceDetail(TextMcqChoice choice);

    List<McqChoiceDetail> toMcqChoiceDetailList(List<TextMcqChoice> choices);
}
