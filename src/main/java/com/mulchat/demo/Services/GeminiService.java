package com.mulchat.demo.Services;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import com.google.cloud.vertexai.VertexAI;
import com.google.cloud.vertexai.api.GenerateContentResponse;
import com.google.cloud.vertexai.api.GenerationConfig;
import com.google.cloud.vertexai.api.HarmCategory;
import com.google.cloud.vertexai.api.SafetySetting;
import com.google.cloud.vertexai.generativeai.GenerativeModel;
import com.google.cloud.vertexai.generativeai.ResponseHandler;
import com.mulchat.demo.Entities.Chat;
import com.mulchat.demo.Entities.Users;

@Service
// Remove the invalid line of code
public class GeminiService {

    private static final String PROJECT_ID = "";
    private static final String LOCATION = "";
    private static final String MODEL_NAME = "gemini-1.5-flash-001";
    


    public String generateBotResponse(Chat chat, Users bot, String userMessage) throws IOException {
    
        String personalityPrompt = getPersonalityPrompt(bot.getPersonality());

        // Combine the user's message with the bot's personality prompt
        String textPrompt = String.format("%s %s", personalityPrompt, userMessage);

        // Generate the response using the Gemini model with safety settings and generation config
        return textInput(PROJECT_ID, LOCATION, MODEL_NAME, textPrompt);
    }

    private String getPersonalityPrompt(String personality) {
        switch (personality.toLowerCase()) {
            case "humorous":
                return "Respond with humor in maximum 10 words or less:";
            case "angry":
                return "Respond with anger in maximum 10 words or less:";
            case "polite":
                return "Respond politely in maximum 10 words or less:";
            case "surprised":
                return "Respond with surprise in maximum 10 words or less:";
            default:
                return "Respond in a neutral tone in maximum 10 words or less:";
        }
    }
public RestTemplate restTemplate(RestTemplateBuilder builder) throws IOException {
		GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream("C:\\\\Users\\\\HP\\\\Desktop\\\\graphite-pad-433303-r4-2617d4e39520.json"));
		FirestoreOptions firestoreOptions = FirestoreOptions.getDefaultInstance().toBuilder()
			.setCredentials(credentials)
			.build();
		Firestore db = firestoreOptions.getService();
	
		return builder.build();
	}
    // Passes the provided text input to the Gemini model and returns the text-only response.
    public static String textInput(String projectId, String location, String modelName, String textPrompt) throws IOException {
        VertexAI vertexAI = new VertexAI(projectId, location);
        

            // Configure generation settings
            GenerationConfig generationConfig = GenerationConfig.newBuilder()
                    .setMaxOutputTokens(8192)
                    .setTemperature(1F)
                    .setTopP(0.95F)
                    .build();

            // Configure safety settings
            List<SafetySetting> safetySettings = Arrays.asList(
                    SafetySetting.newBuilder()
                            .setCategory(HarmCategory.HARM_CATEGORY_HATE_SPEECH)
                            .setThreshold(SafetySetting.HarmBlockThreshold.BLOCK_MEDIUM_AND_ABOVE)
                            .build(),
                    SafetySetting.newBuilder()
                            .setCategory(HarmCategory.HARM_CATEGORY_DANGEROUS_CONTENT)
                            .setThreshold(SafetySetting.HarmBlockThreshold.BLOCK_MEDIUM_AND_ABOVE)
                            .build(),
                    SafetySetting.newBuilder()
                            .setCategory(HarmCategory.HARM_CATEGORY_SEXUALLY_EXPLICIT)
                            .setThreshold(SafetySetting.HarmBlockThreshold.BLOCK_MEDIUM_AND_ABOVE)
                            .build(),
                    SafetySetting.newBuilder()
                            .setCategory(HarmCategory.HARM_CATEGORY_HARASSMENT)
                            .setThreshold(SafetySetting.HarmBlockThreshold.BLOCK_MEDIUM_AND_ABOVE)
                            .build()
            );

            // Build the GenerativeModel with safety and generation config
            GenerativeModel model = GenerativeModel.newBuilder()
                    .setModelName(modelName)
                    .setVertexAi(vertexAI)
                    .setGenerationConfig(generationConfig)
                    .setSafetySettings(safetySettings)
                    .build();

            // Generate the content and return the response
            GenerateContentResponse response = model.generateContent(textPrompt);
            return ResponseHandler.getText(response);
        }
    

    // Method to generate a new message ID (for demonstration purposes)
    public Long generateNewMessageId() {
        // This would typically involve saving the message to a database and returning the generated ID.
        // Here, we'll just return a mock ID for demonstration.
        return System.currentTimeMillis();
    }
}
