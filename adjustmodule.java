import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Build;
import android.text.Editable;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextWatcher;
import android.text.method.SingleLineTransformationMethod;
import android.text.method.TransformationMethod;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

public class adjustmodule {
            private static TextView view;
            private static int fontSize;
            private int mMaxLines;
            private float mMinTextSize;
            private float mMaxTextSize;
            
            private static void getFontSize(TextView view) {
                        fontSize = view.getTextSize();
            }
            private static void changeFontSize(int value) {
                        view.setTextSize(value);
            }
            private static void fontSizePlus() {
                        fontSize++;
                        this.changeFontSize(fontSize);
            }

            private static void fontSizeMinus() {
                        fontSize--;
                        this.changeFontSize(fontSize);
            }
            
            private static float getAutofitTextSize(CharSequence text, TextPaint paint,
                                                    float targetWidth, int maxLines, float low, float high,
                                                    DisplayMetrics displayMetrics) {
                        float mid = (low + high) / 2.0f;
                        int lineCount = 1;
                        StaticLayout layout = null;
                        paint.setTextSize(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, mid,
                                                                    displayMetrics));                       
                        if (maxLines != 1) {
                                    layout = new StaticLayout(text, paint, (int)targetWidth, Layout.Alignment.ALIGN_NORMAL,
                                                              1.0f, 0.0f, true);
                                    lineCount = layout.getLineCount();
                        }
                        if (SPEW) Log.d(TAG, "low=" + low + " high=" + high + " mid=" + mid +
                                        " target=" + targetWidth + " maxLines=" + maxLines + " lineCount=" + lineCount);
                        if (lineCount > maxLines) {                                    
                                    if ((high - low) < 0) {
                                                return low;
                                    }
                                    return getAutofitTextSize(text, paint, targetWidth, maxLines, low, mid, 
                                                              displayMetrics);
                        }
                        else if (lineCount < maxLines) {
                                    return getAutofitTextSize(text, paint, targetWidth, maxLines, mid, high,
                                                              displayMetrics);
                        }
                        else {
                                    float maxLineWidth = 0;
                                    if (maxLines == 1) {
                                                maxLineWidth = paint.measureText(text, 0, text.length());
                                    } else {
                                                for (int i = 0; i < lineCount; i++) {
                                                            if (layout.getLineWidth(i) > maxLineWidth) {
                                                                        maxLineWidth = layout.getLineWidth(i);
                                                            }
                                                }
                                    }
                                    if ((high - low) < 0) {
                                                return low;
                                    } else if (maxLineWidth > targetWidth) {
                                                return getAutofitTextSize(text, paint, targetWidth, maxLines, low, mid,
                                                                          displayMetrics);
                                    } else if (maxLineWidth < targetWidth) {
                                                return getAutofitTextSize(text, paint, targetWidth, maxLines, mid, high,
                                                                          displayMetrics);
                                    } else {
                                                return mid;
                                    }
                        }
            }
            private static void autofit(TextView view, TextPaint paint, float minTextSize, float maxTextSize,
                int maxLines){
                        if (maxLines <= 0 || maxLines == Integer.MAX_VALUE) {
                                    return;
                        }

                        int targetWidth = view.getWidth() - view.getPaddingLeft() - view.getPaddingRight();
                        if (targetWidth <= 0) {
                                    return;
                        }

                        CharSequence text = view.getText();
                        TransformationMethod method = view.getTransformationMethod();
                        if (method != null) {
                                    text = method.getTransformation(text, view);
                        }

                        Context context = view.getContext();
                        Resources r = Resources.getSystem();
                        DisplayMetrics displayMetrics;

                        float size = maxTextSize;
                        float high = size;
                        float low = 0;

                        if (context != null) {
                                    r = context.getResources();
                        }
                        displayMetrics = r.getDisplayMetrics();

                        paint.set(view.getPaint());
                        paint.setTextSize(size);

                        if ((maxLines == 1 && paint.measureText(text, 0, text.length()) > targetWidth)
                            || getLineCount(text, paint, size, targetWidth, displayMetrics) > maxLines) {
                                      size = getAutofitTextSize(text, paint, targetWidth, maxLines, low, high,
                                      displayMetrics);
                                    }

                        if (size < minTextSize) {
                                    size = minTextSize;
                        }

                        view.setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
                        }
            public float getMinTextSize() {
                        return mMinTextSize;
            }
            public float getMaxTextSize() {
                        return mMaxTextSize;
            }
            public int getMaxLines() {
                        return mMaxLines;
            }
            public float getTextSize() {
                        return mTextSize;
            }
            public void setTextSize(float size) {
                        setTextSize(TypedValue.COMPLEX_UNIT_SP, size);
            }
            public void setTextSize(int unit, float size) {
                        Context context = mTextView.getContext();  
                        Resources r = Resources.getSystem();        
                        if (context != null) {
                                    r = context.getResources();
                        }
                        setRawTextSize(TypedValue.applyDimension(unit, size, r.getDisplayMetrics()));
            }
            private void setRawTextSize(float size) {
                        if (mTextSize != size) {
                                    mTextSize = size;
                        }
            }
            
}
