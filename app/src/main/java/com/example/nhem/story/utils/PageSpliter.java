package com.example.nhem.story.utils;

import android.graphics.Color;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;

import java.util.ArrayList;
import java.util.List;

public class PageSpliter {
    private static String content;
    private static int width;
    private static int height;

    public PageSpliter(String content, int width, int height) {
        PageSpliter.content = content;
        PageSpliter.width = width;
        PageSpliter.height = height;
    }

    public static StaticLayout creatStaticLayout(String content) {
        TextPaint textPaint = new TextPaint();
        textPaint.setTextSize(50);
        textPaint.setAntiAlias(true);
       //textPaint.setColor(Color.parseColor(0000));

        return new StaticLayout(content, textPaint, width,
                Layout.Alignment.ALIGN_NORMAL, 0.5f, 50f, true);
    }

    public static List<String> splitPage(StaticLayout staticLayout) {
        List<String> listPage = new ArrayList<>();
        int startLineNumber = 0;
        while (startLineNumber < staticLayout.getLineCount()) {
            int startLineCoordinate = staticLayout.getLineTop(startLineNumber);
            int endLineNumber = staticLayout.getLineForVertical(startLineCoordinate + height);

            int startIndex = staticLayout.getLineStart(startLineNumber);

            int endLineCoordinate = staticLayout.getLineBottom(endLineNumber);

            int rightEndLineNumber;
            if (endLineCoordinate > startLineCoordinate + height) {
                rightEndLineNumber = endLineNumber - 1;
            } else {
                rightEndLineNumber = endLineNumber;
            }

            int endIndex = staticLayout.getLineEnd(rightEndLineNumber);

            listPage.add(content.substring(startIndex, endIndex));
            startLineNumber = rightEndLineNumber + 1;
        }
        return listPage;
    }
}
