package com.example.sitpassbek.configuration;

import org.apache.tika.language.detect.LanguageDetector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class BeanConfiguration {

    @Bean
    public LanguageDetector languageDetector() throws Exception {
        LanguageDetector languageDetector;
        try {
            languageDetector = LanguageDetector.getDefaultLanguageDetector().loadModels();
        } catch (IOException e) {
            throw new Exception("Error while loading language models.");
        }
        return languageDetector;
    }
}
