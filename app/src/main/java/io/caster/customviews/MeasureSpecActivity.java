package io.caster.customviews;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import io.caster.customviews.R;
import io.caster.customviews.TimerView;
import io.caster.customviews.util.MeasureUtils;

/**
 * Activity that compares the layout parameters, parent-given measure specs, and ultimate size
 * of {@link TimerView} instances based on the parent's dimensions.
 */
public class MeasureSpecActivity extends AppCompatActivity {

    private static final boolean SHOW_ALL_WIDTH_HEIGHT = true;

    @BindView(R.id.timer_a) TimerView timerViewA;
    @BindView(R.id.timer_b) TimerView timerViewB;
    @BindView(R.id.timer_c) TimerView timerViewC;
    @BindView(R.id.timer_d) TimerView timerViewD;
    @BindViews({R.id.timer_a, R.id.timer_b, R.id.timer_c, R.id.timer_d}) List<TimerView> timerViews;

    @BindView(R.id.frame_a) View frameA;
    @BindView(R.id.frame_b) View frameB;
    @BindView(R.id.frame_c) View frameC;
    @BindView(R.id.frame_d) View frameD;

    @BindView(R.id.layout_params_a) TextView layoutParamInfoA;
    @BindView(R.id.layout_params_b) TextView layoutParamInfoB;
    @BindView(R.id.layout_params_c) TextView layoutParamInfoC;
    @BindView(R.id.layout_params_d) TextView layoutParamInfoD;

    @BindView(R.id.dimensions_a) TextView dimensionsA;
    @BindView(R.id.dimensions_b) TextView dimensionsB;
    @BindView(R.id.dimensions_c) TextView dimensionsC;
    @BindView(R.id.dimensions_d) TextView dimensionsD;

    static final ButterKnife.Action<TimerView> START = new ButterKnife.Action<TimerView>() {
        @Override
        public void apply(@NonNull TimerView view, int index) {
            view.start();
        }
    };

    static final ButterKnife.Action<TimerView> STOP = new ButterKnife.Action<TimerView>() {
        @Override
        public void apply(@NonNull TimerView view, int index) {
            view.start();
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_measurespec);

        ButterKnife.bind(this);

        timerViewA.getViewTreeObserver().addOnGlobalLayoutListener(
            new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    updateLayoutParamInfo(frameA, timerViewA, layoutParamInfoA, dimensionsA);
                    updateLayoutParamInfo(frameB, timerViewB, layoutParamInfoB, dimensionsB);
                    updateLayoutParamInfo(frameC, timerViewC, layoutParamInfoC, dimensionsC);
                    updateLayoutParamInfo(frameD, timerViewD, layoutParamInfoD, dimensionsD);
                }
            });
    }

    @Override
    protected void onResume() {
        super.onResume();

        ButterKnife.apply(timerViews, START);
    }

    @Override
    protected void onPause() {
        super.onPause();

        ButterKnife.apply(timerViews, STOP);
    }

    private void updateLayoutParamInfo(View parent, View timerView,
                                       TextView infoTextView, TextView dimensionsTextView) {
        Resources resources = getResources();
        ViewGroup.LayoutParams parentLayoutParams = parent.getLayoutParams();
        ViewGroup.LayoutParams timerLayoutParams = timerView.getLayoutParams();
        infoTextView.setText(getString(
            R.string.parent_timer_layout_params_format,
            MeasureUtils.layoutParamToString(resources, parentLayoutParams.width),
            MeasureUtils.layoutParamToString(resources, parentLayoutParams.height),
            MeasureUtils.layoutParamToString(resources, timerLayoutParams.height),
            MeasureUtils.layoutParamToString(resources, timerLayoutParams.height)
        ));
        if (SHOW_ALL_WIDTH_HEIGHT) {
            dimensionsTextView.setText(getString(
                R.string.timer_all_width_height,
                MeasureUtils.pixelsToDPString(resources, timerView.getMeasuredWidth()),
                MeasureUtils.pixelsToDPString(resources, timerView.getMeasuredHeight()),
                MeasureUtils.pixelsToDPString(resources, timerView.getWidth()),
                MeasureUtils.pixelsToDPString(resources, timerView.getHeight())
            ));
        } else {
            dimensionsTextView.setText(getString(
                R.string.timer_width_height,
                MeasureUtils.pixelsToDPString(resources, timerView.getMeasuredWidth()),
                MeasureUtils.pixelsToDPString(resources, timerView.getMeasuredHeight())
            ));
        }
    }
}
