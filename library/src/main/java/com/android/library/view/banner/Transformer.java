package com.android.library.view.banner;

import android.support.v4.view.ViewPager.PageTransformer;

import com.android.library.view.banner.transformer.AccordionTransformer;
import com.android.library.view.banner.transformer.BackgroundToForegroundTransformer;
import com.android.library.view.banner.transformer.CubeInTransformer;
import com.android.library.view.banner.transformer.CubeOutTransformer;
import com.android.library.view.banner.transformer.DefaultTransformer;
import com.android.library.view.banner.transformer.DepthPageTransformer;
import com.android.library.view.banner.transformer.FlipHorizontalTransformer;
import com.android.library.view.banner.transformer.FlipVerticalTransformer;
import com.android.library.view.banner.transformer.ForegroundToBackgroundTransformer;
import com.android.library.view.banner.transformer.RotateDownTransformer;
import com.android.library.view.banner.transformer.RotateUpTransformer;
import com.android.library.view.banner.transformer.ScaleInOutTransformer;
import com.android.library.view.banner.transformer.ScaleTransformer;
import com.android.library.view.banner.transformer.StackTransformer;
import com.android.library.view.banner.transformer.TabletTransformer;
import com.android.library.view.banner.transformer.ZoomInTransformer;
import com.android.library.view.banner.transformer.ZoomOutSlideTransformer;
import com.android.library.view.banner.transformer.ZoomOutTranformer;


public class Transformer {

    public static Class<? extends PageTransformer> Default = DefaultTransformer.class;
    public static Class<? extends PageTransformer> Accordion = AccordionTransformer.class;
    public static Class<? extends PageTransformer> BackgroundToForeground = BackgroundToForegroundTransformer.class;
    public static Class<? extends PageTransformer> ForegroundToBackground = ForegroundToBackgroundTransformer.class;
    public static Class<? extends PageTransformer> CubeIn = CubeInTransformer.class;
    public static Class<? extends PageTransformer> CubeOut = CubeOutTransformer.class;
    public static Class<? extends PageTransformer> DepthPage = DepthPageTransformer.class;
    public static Class<? extends PageTransformer> FlipHorizontal = FlipHorizontalTransformer.class;
    public static Class<? extends PageTransformer> FlipVertical = FlipVerticalTransformer.class;
    public static Class<? extends PageTransformer> RotateDown = RotateDownTransformer.class;
    public static Class<? extends PageTransformer> RotateUp = RotateUpTransformer.class;
    public static Class<? extends PageTransformer> ScaleInOut = ScaleInOutTransformer.class;
    public static Class<? extends PageTransformer> Scale = ScaleTransformer.class;
    public static Class<? extends PageTransformer> Stack = StackTransformer.class;
    public static Class<? extends PageTransformer> Tablet = TabletTransformer.class;
    public static Class<? extends PageTransformer> ZoomIn = ZoomInTransformer.class;
    public static Class<? extends PageTransformer> ZoomOut = ZoomOutTranformer.class;
    public static Class<? extends PageTransformer> ZoomOutSlide = ZoomOutSlideTransformer.class;

}
