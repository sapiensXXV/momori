package com.poolygo.quiz.domain.mapper;


import com.poolygo.quiz.domain.AudioSubQuestion;
import com.poolygo.quiz.presentation.dto.response.detail.AudioSubQuestionDetailResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AudioSubMapper {

    AudioSubMapper INSTANCE = Mappers.getMapper(AudioSubMapper.class);

    AudioSubQuestionDetailResponse toAudioSubQuestionDetailResponse(AudioSubQuestion question);

}
