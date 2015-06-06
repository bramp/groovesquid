package com.groovesquid.service;

import com.groovesquid.model.Track;

public interface PlaybackListener extends DownloadListener {
    void playbackStarted(Track track);

    void playbackPaused(Track track, int audioPosition);

    void playbackFinished(Track track, int audioPosition);

    void positionChanged(Track track, int audioPosition);

    void exception(Track track, Exception ex);
}
