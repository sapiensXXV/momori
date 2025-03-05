package com.momori.quizdraft.domain.factory;


import com.momori.quizdraft.domain.AudioMcqChoiceDraft;
import com.momori.quizdraft.domain.ImageMcqChoiceDraft;
import com.momori.quizdraft.presentation.dto.audiomcq.DraftAudioMcqChoiceRequest;
import com.momori.quizdraft.presentation.dto.imagemcq.DraftImageMcqChoiceRequest;
import org.springframework.stereotype.Component;

@Component
public class ChoiceDraftFactory {

    public ImageMcqChoiceDraft from(DraftImageMcqChoiceRequest request) {
        String content = request.getContent();
        boolean isAnswer = request.isAnswer();

        return ImageMcqChoiceDraft.of(content, isAnswer);
    }

    public AudioMcqChoiceDraft from(DraftAudioMcqChoiceRequest request) {
        String content = request.getContent();
        boolean answer = request.isAnswer();

        return AudioMcqChoiceDraft.of(content, answer);
    }

}
