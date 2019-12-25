package com.BlankSPace.blankrain;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;


public class SubGraph {

    private float maxHeight;
    private float minHeight;
    private Paint fillColor;
    private Paint strokeColor;
    private Paint coloumColor;
    private Paint darkerColoumColor;
    private Paint backgroundColor;
    private String defaultFillColor = "#00b3ff";
    private String defaultStrokeColor = "#007aad";
    private String defaultColoumnColor = "#d6d6d6";
    private String defaultDarkerColoumnColor = "#5c5c5c";
    private String defaultBackgrounfColor = "#f5f5f5";

    public SubGraph(float minHeight, float maxHeight){
        this.maxHeight = maxHeight;
        this.minHeight = minHeight;

        this.fillColor = new Paint();
        this.fillColor.setAntiAlias(true);
        this.fillColor.setColor(Color.parseColor(defaultFillColor));
        this.fillColor.setStyle(Paint.Style.FILL);
        this.strokeColor = new Paint();
        this.strokeColor.setAntiAlias(true);
        this.strokeColor.setColor(Color.parseColor(defaultStrokeColor));
        this.strokeColor.setStyle(Paint.Style.FILL);
        this.coloumColor = new Paint();
        this.coloumColor.setAntiAlias(true);
        this.coloumColor.setColor(Color.parseColor(defaultColoumnColor));
        this.coloumColor.setStyle(Paint.Style.FILL);
        this.darkerColoumColor = new Paint();
        this.darkerColoumColor.setAntiAlias(true);
        this.darkerColoumColor.setColor(Color.parseColor(defaultDarkerColoumnColor));
        this.darkerColoumColor.setStyle(Paint.Style.FILL);
        this.darkerColoumColor.setStrokeWidth(4.0f);
        this.darkerColoumColor.setTextSize(20);
        this.backgroundColor = new Paint();
        this.backgroundColor.setAntiAlias(true);
        this.backgroundColor.setColor(Color.parseColor(defaultBackgrounfColor));
        this.backgroundColor.setStyle(Paint.Style.FILL);
    }

    public void DrawColoumns(Canvas canvas, PointF[] p){
        for(int i = 0; i < p.length - 1; i++){
            canvas.drawLine(p[i].x, minHeight, p[i].x, maxHeight, coloumColor);
        }
        canvas.drawLine(p[p.length - 1].x, minHeight, p[p.length - 1].x, maxHeight, darkerColoumColor);
        canvas.drawLine(p[1].x, maxHeight, p[p.length - 1].x, maxHeight, darkerColoumColor);
        canvas.drawText("20db", p[p.length - 1].x + 15, minHeight, darkerColoumColor);
        canvas.drawText("-20db", p[p.length - 1].x + 15, maxHeight, darkerColoumColor);
        canvas.drawText("Low", p[1].x, maxHeight + 40, darkerColoumColor);
        this.darkerColoumColor.setTextAlign(Paint.Align.RIGHT);
        canvas.drawText("High", p[p.length - 1].x, maxHeight + 40, darkerColoumColor);
        this.darkerColoumColor.setTextAlign(Paint.Align.CENTER);
        canvas.drawText("M", p[0].x, maxHeight + 40, darkerColoumColor);
        this.darkerColoumColor.setTextAlign(Paint.Align.LEFT);
    }

    public void DrawSubGraph(Canvas canvas, PointF[] p){
        Path path = new Path();
        for(int i = 1; i < p.length - 1; i++){
            canvas.drawLine(p[i].x, p[i].y, p[i+1].x, p[i+1].y, strokeColor);

            if(i == 1){
                path.moveTo(p[i].x, maxHeight);
            }
            path.lineTo(p[i].x, p[i].y);
        }
        path.lineTo(p[p.length - 1].x, p[p.length - 1].y);
        path.lineTo(p[p.length - 1].x, maxHeight);
        canvas.drawPath(path, fillColor);
    }

    public void DrawBackground(Canvas canvas, PointF[] p){
        canvas.drawRect(new RectF(p[1].x, minHeight, p[p.length - 1].x, maxHeight), backgroundColor);
    }

}
