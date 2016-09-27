protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mWebView = (WebView) findViewById(R.id.webview);
        fontSize = mWebView.getSettings().getDefaultFontSize();

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
