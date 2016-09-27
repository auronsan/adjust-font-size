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
