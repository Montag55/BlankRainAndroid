package com.BlankSPace.blankrain;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import android.widget.Toast;

public class Node {
    public float volume = 0.5f;
    public int index;
    private float radius;
    private float x;
    private float y;
    private float maxHeight;
    private float minHeight;
    private float touchOffset;
    private Paint fillColor;
    private Paint strokeColor;
    private RectF rect;
    private String defaultFillColor = "#f5f5f5";
    private String defaultStrokeColor = "#d6d6d6";
    private String selectedFillColor = "#d6d6d6";

    public Node(float x, float y, float r, float touchOffset, float minHeight, float maxHeight, int index){
        this.x = x;
        this.y = y;
        this.radius = r;
        this.touchOffset = touchOffset;
        this.rect = new RectF(this.x - this.touchOffset, this.y - touchOffset, this.x + touchOffset, this.y + touchOffset);
        this.minHeight = minHeight;
        this.maxHeight = maxHeight;
        this.index = index;

        this.fillColor = new Paint();
        this.fillColor.setAntiAlias(true);
        this.fillColor.setColor(Color.parseColor(defaultFillColor));
        this.fillColor.setStyle(Paint.Style.FILL);
        this.strokeColor = new Paint();
        this.strokeColor.setAntiAlias(true);
        this.strokeColor.setColor(Color.parseColor(defaultStrokeColor));
        this.strokeColor.setStyle(Paint.Style.FILL);
    }

    public PointF getPosition(){
        return new PointF(this.x, this.y);
    }

    public void setY(float y){
        if(y <= maxHeight && y >= minHeight) {
            this.y = y;
        }
        this.volume = (this.maxHeight - this.radius - this.y) * 2 / (this.maxHeight - this.radius);

        if(volume < 0)
            volume = 0;
        if(volume > 1)
            volume = 1;

    }

    public boolean selected(float x, float y){
        if(this.rect.contains(x, y)){
            this.fillColor.setColor(Color.parseColor(selectedFillColor));
            return true;
        }
        return false;
    }

    public void deselect(){
        this.fillColor.setColor(Color.parseColor(defaultFillColor));
        this.rect = new RectF(this.x - this.touchOffset, this.y - touchOffset, this.x + touchOffset, this.y + touchOffset);
    }

    public void drawNode(Canvas canvas){
        canvas.drawCircle(this.x, this.y, this.radius + 5, strokeColor);
        canvas.drawCircle(this.x, this.y, this.radius, fillColor);
    }
}
