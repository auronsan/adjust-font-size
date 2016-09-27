public class adjustmodule {
            private WebView mWebView;
            private int fontSize;
            
            public setFontSize(View rootView,int value){ 
                        mWebView = (WebView) rootView.findViewById(R.id.webview);                    
                        mWebView.getSettings().setDefaultFontSize(value);
                        return rootView;
            }
            private void fontSizePlus(View rootView) {
                        mWebView = (WebView) rootView.findViewById(R.id.webview);  
                        fontSize = mWebView.getSettings().getDefaultFontSize();
                        fontSize++;
                        this.changeFontSize(fontSize);
            }

            private void fontSizeMinus(View rootView) {
                        mWebView = (WebView) rootView.findViewById(R.id.webview);  
                        fontSize = mWebView.getSettings().getDefaultFontSize();
                        fontSize--;
                        this.changeFontSize(fontSize);
            }

}
