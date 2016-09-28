public class adjustmodule {
            private static WebView mWebView;
            private static int fontSize;
            private int mMaxLines;
            private float mMinTextSize;
            private float mMaxTextSize;
            
            private static void changeFontSize(int value) {
                        mWebView.getSettings().setDefaultFontSize(value);
            }
            private static void fontSizePlus() {
                        fontSize++;
                        this.changeFontSize(fontSize);
            }

            private static void fontSizeMinus() {
                        fontSize--;
                        this.changeFontSize(fontSize);
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
