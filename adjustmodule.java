public class adjustmodule {
            public setFontSize(View rootView,int value){ 
                        WebView mWebView = (WebView) rootView.findViewById(R.id.webview);
                        fontSize = mWebView.getSettings().getDefaultFontSize(); 
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

            private void changeFontSize(int value) {
            mWebView.getSettings().setDefaultFontSize(value);
            }

}
