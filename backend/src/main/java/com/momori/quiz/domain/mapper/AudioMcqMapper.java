package com.momori.quiz.domain.mapper;

import com.momori.quiz.domain.AudioMcqQuestion;
import com.momori.quiz.domain.TextMcqChoice;
import com.momori.quiz.presentation.dto.response.detail.AudioMcqQuestionDetailResponse;
import com.momori.quiz.presentation.dto.response.detail.McqChoiceDetail;
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
