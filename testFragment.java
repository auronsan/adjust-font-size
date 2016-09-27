package com.example.anshor.adjust-font-size.app;

public class testFragment extends Fragment {
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        mWebView = (WebView) rootView.findViewById(R.id.webview);
        fontSize = mWebView.getSettings().getDefaultFontSize();                    
        
                
        return rootView;
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
