package com.iomonad.hardwax.utils;

/**
 * Created by iomonad on 5/30/17.
 */

import android.util.Xml;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import java.io.IOException;
import java.io.InputStream;

public class StringXmlParser {

    private static final String ns = null;

    public String parse(InputStream in) throws XmlPullParserException, IOException {
        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in,null);
            parser.nextTag();
            return readString(parser);
        } finally {
            in.close();
        }
    }

    private String readString(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, ns, "string");
        String jsonString = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "string");
        return jsonString;
    }

    private String readText(XmlPullParser parser) throws IOException, XmlPullParserException {
        String result = "";
        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.getText();
            parser.nextTag();
        }
        return result;
    }
}