package io.caster.customviews.util;

import android.content.res.Resources;
import android.view.ViewGroup;

import io.caster.customviews.R;

/**
 * Utilities for view measurement and for displaying measurement information.
 */
public class MeasureUtils {

    /**
     * Converts a {@link android.view.ViewGroup.LayoutParams#width} or
     * {@link android.view.ViewGroup.LayoutParams#height} to a string.
     *
     * @param resources A reference to the {@link Resources} in the current context.
     * @param param     A valid {@link android.view.ViewGroup.LayoutParams#width} or
     *                  {@link android.view.ViewGroup.LayoutParams#height} value.
     * @return A string representing the {@code param}.
     */
    public static String layoutParamToString(Resources resources, int param) {
        switch (param) {
            case ViewGroup.LayoutParams.MATCH_PARENT:
                return resources.getString(R.string.match_parent);
            case ViewGroup.LayoutParams.WRAP_CONTENT:
                return resources.getString(R.string.wrap_content);
            default:
                return String.valueOf(pixelsToDPString(resources, param));
        }
    }

    /**
     * Converts a pixel value to a density independent pixels (DPs).
     *
     * @param resources A reference to the {@link Resources} in the current context.
     * @param pixels    A measurement in pixels.
     * @return The value of {@code pixels} in DPs.
     */
    public static int pixelsToDP(Resources resources, int pixels) {
        return Math.round(pixels / resources.getDisplayMetrics().density);
    }

    /**
     * Converts a pixel value to a density independent pixel (DP) string representation.
     *
     * @param resources A reference to the {@link Resources} in the current context.
     * @param pixels    A measurement in pixels.
     * @return A string that is the value of {@code pixels} converted to DPs.
     */
    public static String pixelsToDPString(Resources resources, int pixels) {
        return pixelsToDP(resources, pixels) + "dp";
    }

    //
    // Constructors
    //

    /**
     * Private constructor to prevent instantiation.
     */
    private MeasureUtils() {
        throw new AssertionError("Cannot instantiate " + getClass().getName());
    }
}
