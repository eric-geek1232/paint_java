package com.example.paint.Features;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.MotionEvent;
import android.view.View;

public class PaintView extends View {
    private String pencilColor = "#9Fa9df";
    private int pencilSize = 14;
    private final Paint bitMapPaint;
    private float lastPosX, lastPosY;

    private Paint circlePaint; // styles
    private Path circlePath; // shapes

    private Canvas canvas;
    private Bitmap bitmap; // save shapes

    private Paint brushPaint;
    private Path brushPath;

    public PaintView(Context context) {
        super(context);
        this.createCircle();
        this.bitMapPaint = new Paint();
        this.createBrush();

        this.setBackgroundColor(Color.WHITE);
    }

    public void setClean() {
        this.brushPath.reset();
        this.circlePath.reset();
        this.canvas.drawColor(Color.WHITE);
        this.invalidate(); // update draw
        this.setPencil();
    }

    public void setPencil() {
        this.brushPaint.setStrokeWidth(pencilSize);
        this.brushPaint.setColor(Color.parseColor(pencilColor));
        this.circlePaint.setColor(Color.parseColor(pencilColor));
    }

    public void setEraser(String colorWhite) {
        this.brushPaint.setStrokeWidth(pencilSize + 20);
        this.brushPaint.setColor(Color.WHITE);
        this.circlePaint.setColor(Color.parseColor(colorWhite));
    }

    private void createCircle() {
        this.circlePaint = new Paint();
        this.circlePath = new Path();

        this.circlePaint.setDither(true);
        this.circlePaint.setAntiAlias(true);
        this.circlePaint.setColor(Color.parseColor(pencilColor));
        this.circlePaint.setStyle(Paint.Style.STROKE);
        this.circlePaint.setStrokeWidth(pencilSize);
    }

    private void createBrush() {
        this.brushPaint = new Paint();
        this.brushPath = new Path();

        this.brushPaint.setDither(true);
        this.brushPaint.setAntiAlias(true);
        this.brushPaint.setColor(Color.parseColor(pencilColor));
        this.brushPaint.setStyle(Paint.Style.STROKE);
        this.brushPaint.setStrokeWidth(pencilSize);
        this.brushPaint.setStrokeCap(Paint.Cap.ROUND);
    }

    private void actionMove(float posX, float posY) {
        this.circlePath.reset();
        this.brushPath.quadTo(this.lastPosX, this.lastPosY, (posX + this.lastPosX) / 2, (posY + this.lastPosY) / 2); // draw curves
        this.lastPosY = posY;
        this.lastPosX = posX;

        this.circlePath.addCircle(posX, posY, 30, Path.Direction.CCW);
    }

    private void actionUp() {
        this.circlePath.reset();
        this.canvas.drawPath(this.brushPath, this.brushPaint);
        this.brushPath.reset();
    }

    private void actionDown(float posX, float posY) {
        this.lastPosX = posX;
        this.lastPosY = posY;

        this.circlePath.reset();
        this.brushPath.reset();
        this.brushPath.moveTo(lastPosX, lastPosY);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(this.bitmap, 0, 0, this.bitMapPaint); // recycle shapes
        canvas.drawPath(this.brushPath, this.brushPaint);
        canvas.drawPath(this.circlePath, this.circlePaint);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float posX = event.getX();
        float posY = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                this.actionDown(posX, posY);
                break;

            case MotionEvent.ACTION_MOVE:
                this.actionMove(posX, posY);
                break;

            case MotionEvent.ACTION_UP:
                this.actionUp();
                break;
        }

        this.invalidate();
        return true;
    }

    @Override
    protected void onSizeChanged(int width, int height, int oldWidth, int oldHeight) {
        super.onSizeChanged(width, height, oldWidth, oldHeight);
        this.bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        this.canvas = new Canvas(this.bitmap);
    }

    public void setPencilColor(String pencilColor) {
        this.pencilColor = pencilColor;
        setPencil();
        createCircle();
    }

    public int getPencilSize() {
        return pencilSize;
    }

    public void setPencilSize(int pencilSize) {
        this.pencilSize = pencilSize;
        setPencil();
        createCircle();
    }
}
