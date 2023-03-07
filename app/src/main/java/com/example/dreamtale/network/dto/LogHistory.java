package com.example.dreamtale.network.dto;

import com.google.gson.annotations.SerializedName;

public class LogHistory {
    private int audioBookId;
    private int audioBookEpId;
    private long progress;
    private long duration;

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public int getAudioBookId() {
        return audioBookId;
    }

    public void setAudioBookId(int audioBookId) {
        this.audioBookId = audioBookId;
    }

    public int getAudioBookEpId() {
        return audioBookEpId;
    }

    public void setAudioBookEpId(int audioBookEpId) {
        this.audioBookEpId = audioBookEpId;
    }

    public long getProgress() {
        return progress;
    }

    public void setProgress(long progress) {
        this.progress = progress;
    }
}
