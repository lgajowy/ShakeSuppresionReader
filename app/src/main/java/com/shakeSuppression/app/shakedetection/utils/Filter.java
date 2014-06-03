package com.shakeSuppression.app.shakedetection.utils;

import com.shakeSuppression.app.animation.ShakeParameters;

public class Filter {

    public static float[] lowPass(float[] input, float[] output) {
        if (output == null) return input;
        for (int i = 0; i < input.length; i++) {
            output[i] = output[i] + ShakeParameters.LOW_PASS_ALPHA * (input[i] - output[i]);
        }
        return output;
    }
}
