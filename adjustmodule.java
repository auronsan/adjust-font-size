public class adjustmodule {
            private WebView mWebView;
            private int fontSize;
            
            private void changeFontSize(int value) {
                        mWebView.getSettings().setDefaultFontSize(value);
            }
            private void fontSizePlus() {
                        fontSize++;
                        this.changeFontSize(fontSize);
            }

            private void fontSizeMinus() {
                        fontSize--;
                        this.changeFontSize(fontSize);
            }

}
