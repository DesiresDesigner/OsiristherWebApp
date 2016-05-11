package com.osiristher.webapp.form.entity;

import com.osiristher.webapp.testingsystem.tester.codes.Language;

/**
 * Created by DesiresDesigner on 5/9/16.
 */
public class Solution {
    private String source;
    private Language language;

    public void setSource(String source) {
        this.source = source;
    }

    public void setLanguage(String language) {
        if (language.equals("CPP"))
            this.language = Language.CPP;
        else if (language.equals("JAVA"))
            this.language = Language.JAVA;
    }

    public Language getLanguage() {
        return language;
    }

    public String getSource() {
        return source;
    }
}
