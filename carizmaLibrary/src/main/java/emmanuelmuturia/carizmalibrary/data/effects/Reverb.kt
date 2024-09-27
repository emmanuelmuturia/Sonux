package emmanuelmuturia.carizmalibrary.data.effects

import android.media.MediaPlayer
import android.media.audiofx.EnvironmentalReverb

/**
 * This is the Reverb Effect that creates the illusion of sound occurring in a specific space...
 */

internal fun applyReverb(mediaPlayer: MediaPlayer) {
    EnvironmentalReverb(0, mediaPlayer.audioSessionId).apply {
        enabled = true
        roomLevel = 0 // This is the maximum room size based on a 100% Room Scale...
        roomHFLevel = -4500 // This is 50% HF Damping...
        decayTime = 10000 // This is 50% Reverberance (Decay Time in Milliseconds)...
        decayHFRatio = 1000 // This is the balanced Decay for Low and High Frequencies...
        reflectionsLevel = -2000 // This is a moderately early Reflection Level...
        reflectionsDelay = 0 // This is a 0 ms Pre-Delay...
        reverbLevel = 0 // This is a Wet Gain (Maximum Reverb Intensity)...
        reverbDelay = 0 // This is a 0 ms Delay before Reverberation starts...
        diffusion = 1000 // This is Maximum Stereo Depth (100%)...
        density = 1000 // Maximum Density (100%)
    }
    mediaPlayer.start()
}