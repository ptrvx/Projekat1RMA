package rs.edu.raf.rma.pvukovic16_projekat1.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

import java.util.ArrayList;
import java.util.List;

import rs.edu.raf.rma.pvukovic16_projekat1.R;
import rs.edu.raf.rma.pvukovic16_projekat1.model.Sum;
import rs.edu.raf.rma.pvukovic16_projekat1.util.Util;

public class PercentageTextView extends AppCompatTextView {

    private static final float CIRCLE_STROKE_WIDTH_DP = 4;

    private int[] mForegroundCircleColor = {R.color.circle1, R.color.circle2, R.color.circle3};
    private int mBackgroundCircleColor = R.color.backgroundCircle;
    private float mCircleStrokeWidthInPx = 40;

    private RectF mRectF;
    private Paint mPaint;

    private List<Sum> sumList = new ArrayList<>();
    private float ukupno = 0;

    public PercentageTextView(Context context) {
        super(context);
        init(null);
    }

    public PercentageTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public PercentageTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        mRectF = new RectF();
        mPaint = new Paint();
    }

    public void setData(List<Sum> sums) {
        sumList.clear();
        sumList.addAll(sums);
        ukupno = 0;
        for (Sum s : sumList) {
            ukupno += (float) s.getSum();
        }
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        int length = Math.min(width, height);

        int newMeasureSpec = MeasureSpec.makeMeasureSpec(length, MeasureSpec.EXACTLY);

        super.onMeasure(newMeasureSpec, newMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float left = 0 + mCircleStrokeWidthInPx;
        float top = 0 + mCircleStrokeWidthInPx;
        float bottom = getHeight() - mCircleStrokeWidthInPx;
        float right = getWidth() - mCircleStrokeWidthInPx;

        mRectF.set(left, top, right, bottom);

        mPaint.setAntiAlias(true);
        mPaint.setColor(getResources().getColor(mBackgroundCircleColor));
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(mCircleStrokeWidthInPx);
        mPaint.setStrokeCap(Paint.Cap.ROUND);

        canvas.drawOval(mRectF, mPaint);

        float startAngle = 0;

        if (ukupno > 0) {
            for (int i = 0; i < sumList.size(); ++i) {
                float number = (float) sumList.get(i).getSum();
                if (i == sumList.size() - 1)
                    mPaint.setColor(getResources().getColor(mBackgroundCircleColor));
                else
                    mPaint.setColor(getResources().getColor(mForegroundCircleColor[i % 3]));
                float sweepAngle = number / ukupno * 360;
                canvas.drawArc(mRectF, startAngle, sweepAngle, false, mPaint);
                startAngle += sweepAngle;
            }
        }
    }


}
