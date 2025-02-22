package com.poolygo.quizdraft.domain;

import lombok.Getter;

@Getter
public class AudioMcqChoiceDraft extends McqChoiceDraft {
    private String content;
    private boolean answer;


    private AudioMcqChoiceDraft(String content, boolean answer) {
        this.content = content;
        this.answer = answer;
    }

    public AudioMcqChoiceDraft of(String content, boolean answer) {
        return new AudioMcqChoiceDraft(content, answer);
    }
}
