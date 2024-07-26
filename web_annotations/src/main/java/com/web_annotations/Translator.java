package com.web_annotations;

import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import jakarta.annotation.Nullable;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class Translator {
    private final Translate translate;

    public Translator() {
        this.translate = TranslateOptions.getDefaultInstance().getService();
    }

    String translate(@Nullable String lang, String toTranslate) {
        return Optional.ofNullable(lang)
                .map(language ->
                        translate.translate(toTranslate,
                                        Translate.TranslateOption.sourceLanguage("en"),
                                        Translate.TranslateOption.targetLanguage(lang))
                                .getTranslatedText()
                )
                .orElse(toTranslate);
    }
}
