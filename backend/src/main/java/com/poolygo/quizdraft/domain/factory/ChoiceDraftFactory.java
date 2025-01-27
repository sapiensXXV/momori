package com.poolygo.quizdraft.domain.factory;


import com.poolygo.quizdraft.domain.ImageMcqChoiceDraft;
import com.poolygo.quizdraft.presentation.dto.DraftImageMcqChoiceRequest;
import org.springframework.stereotype.Component;

@Component
public class ChoiceDraftFactory {

    public ImageMcqChoiceDraft from(DraftImageMcqChoiceRequest request) {
        String content = request.getContent();
        boolean isAnswer = request.isAnswer();

        return ImageMcqChoiceDraft.of(content, isAnswer);
    }

}
