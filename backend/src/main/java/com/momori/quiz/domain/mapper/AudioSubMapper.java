package com.momori.quiz.domain.mapper;


import com.momori.quiz.domain.AudioSubQuestion;
import com.momori.quiz.presentation.dto.response.detail.AudioSubQuestionDetailResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AudioSubMapper {

    AudioSubMapper INSTANCE = Mappers.getMapper(AudioSubMapper.class);

    AudioSubQuestionDetailResponse toAudioSubQuestionDetailResponse(AudioSubQuestion question);

}
