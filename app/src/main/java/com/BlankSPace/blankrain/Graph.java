package com.BlankSPace.blankrain;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.media.MediaPlayer;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.List;

public class Graph extends View {
    Context context;
    public List<MediaPlayer> mPs;
    private Node selectedNode = null;
    private float mY;
    private static final float TOLERANCE = 5;
    private Node[] nodes = new Node[11];
    private SubGraph subgraph;
    public boolean initialized = false;


    public Graph(Context c, AttributeSet attrs) {
        super(c, attrs);
        context = c;
    }

    public void InitNodes(){
        initialized = true;
        for(int i = 0; i < nodes.length; i++){
            nodes[i] = new Node(getWidth() * 0.083f * (i+1), getHeight() / 2, 15, 50, getHeight() * 0.33f, getHeight() * 0.66f, i);
        }
        subgraph = new SubGraph(getHeight() * 0.33f, getHeight() * 0.66f);
    }

    public void SetVolume(){
        for(int i = 1; i < nodes.length; i++){
            mPs.get((nodes[i].index - 1) * 2).setVolume(nodes[i].volume * nodes[0].volume, nodes[i].volume * nodes[0].volume);
            mPs.get((nodes[i].index - 1) * 2 + 1).setVolume(nodes[i].volume * nodes[0].volume, nodes[i].volume * nodes[0].volume);
        }
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {}

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(initialized){
            PointF[] tmp_p = new PointF[nodes.length];
            for (int i = 0; i < nodes.length; i++) {
                tmp_p[i] = nodes[i].getPosition();
            }

            subgraph.DrawBackground(canvas, tmp_p);
            subgraph.DrawSubGraph(canvas, tmp_p);
            subgraph.DrawColoumns(canvas, tmp_p);

            for (Node n : nodes) {
                n.drawNode(canvas);
            }
        }
    }

    private void startTouch(float x, float y) {
        for (Node n : nodes) {
            if(n.selected(x, y)){
                mY = y;
                selectedNode = n;
            }
        }
    }

    private void moveTouch(float x, float y) {
        if(selectedNode != null) {
            float dy = Math.abs(y - mY);
            if (dy >= TOLERANCE) {
                selectedNode.setY((y + mY) / 2);
                mY = y;
                SetVolume();
            }
        }
    }

    public void clearCanvas() {
        invalidate();
    }

    private void upTouch() {
        if(selectedNode != null) {
            selectedNode.deselect();
            selectedNode = null;
        }

    }

    public void ResetNodes(){
        initialized = false;
        InitNodes();
        invalidate();
    }

    public void SetToPresets(float[] preset){
        for(int i = 0; i < preset.length; i++){
            nodes[i].setY(preset[i]);
        }
        SetVolume();
        invalidate();
    }

    public Node[] getNodes(){
        return nodes;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startTouch(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                moveTouch(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                upTouch();
                invalidate();
                break;
        }
        return true;
    }

}
