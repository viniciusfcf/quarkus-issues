package org.acme;

import dev.langchain4j.service.UserMessage;
import io.quarkiverse.langchain4j.RegisterAiService;

@RegisterAiService(retrievalAugmentor=RetrievalAugmentorExample.class)
public interface MyAiService {

    String chat(@UserMessage String userMessage);
    
}