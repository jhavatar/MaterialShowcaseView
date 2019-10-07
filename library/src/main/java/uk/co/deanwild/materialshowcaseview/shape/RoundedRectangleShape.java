package uk.co.deanwild.materialshowcaseview.shape;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import uk.co.deanwild.materialshowcaseview.target.Target;

public class RoundedRectangleShape implements Shape {

    private boolean fullWidth = false;

    private int rx = 0;
    private int ry = 0;
    private int width = 0;
    private int height = 0;
    private boolean adjustToTarget = true;

    private Rect rect;
    private int padding;

    public RoundedRectangleShape(int width, int height, int rx, int ry) {
        this.width = width;
        this.height = height;
        this.rx = rx;
        this.ry = ry;
        init();
    }

    public RoundedRectangleShape(Rect bounds, int rrx, int ry) {
        this(bounds, rrx, ry, false);
    }

    public RoundedRectangleShape(Rect bounds, int rx, int ry, boolean fullWidth) {
        this.fullWidth = fullWidth;
        height = bounds.height();
        if (fullWidth)
            width = Integer.MAX_VALUE;
        else width = bounds.width();
        this.rx = rx;
        this.ry = ry;
        init();
    }

    public boolean isAdjustToTarget() {
        return adjustToTarget;
    }

    public void setAdjustToTarget(boolean adjustToTarget) {
        this.adjustToTarget = adjustToTarget;
    }

    private void init() {
        rect = new Rect(-width / 2, -height / 2, width / 2, height / 2);
    }

    @Override
    public void draw(Canvas canvas, Paint paint, int x, int y) {
        if (!rect.isEmpty()) {
            canvas.drawRoundRect(
                    rect.left + x - padding,
                    rect.top + y - padding,
                    rect.right + x + padding,
                    rect.bottom + y + padding,
                    rx,
                    ry,
                    paint
            );
        }
    }

    @Override
    public void updateTarget(Target target) {
        if (adjustToTarget) {
            Rect bounds = target.getBounds();
            height = bounds.height();
            if (fullWidth)
                width = Integer.MAX_VALUE;
            else width = bounds.width();
            init();
        }
    }

    @Override
    public int getTotalRadius() {
        return (height / 2) + padding;
    }

    @Override
    public void setPadding(int padding) {
        this.padding = padding;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }
}
