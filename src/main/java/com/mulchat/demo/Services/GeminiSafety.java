package com.mulchat.demo.Services;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.google.cloud.vertexai.VertexAI;
import com.google.cloud.vertexai.api.GenerateContentResponse;
import com.google.cloud.vertexai.api.GenerationConfig;
import com.google.cloud.vertexai.api.HarmCategory;
import com.google.cloud.vertexai.api.SafetySetting;
import com.google.cloud.vertexai.generativeai.ContentMaker;
import com.google.cloud.vertexai.generativeai.GenerativeModel;
import com.google.cloud.vertexai.generativeai.ResponseStream;

 public class GeminiSafety {
// public static void main(String[] args) throws IOException {
//     try (VertexAI vertexAi = new VertexAI("graphite-pad-433303-r4", "us-central1"); ) {
//     GenerationConfig generationConfig =
//         GenerationConfig.newBuilder()
//             .setMaxOutputTokens(8192)
//             .setTemperature(1F)
//             .setTopP(0.95F)
//             .build();
//     List<SafetySetting> safetySettings = Arrays.asList(
//         SafetySetting.newBuilder()
//             .setCategory(HarmCategory.HARM_CATEGORY_HATE_SPEECH)
//             .setThreshold(SafetySetting.HarmBlockThreshold.BLOCK_MEDIUM_AND_ABOVE)
//             .build(),
//         SafetySetting.newBuilder()
//             .setCategory(HarmCategory.HARM_CATEGORY_DANGEROUS_CONTENT)
//             .setThreshold(SafetySetting.HarmBlockThreshold.BLOCK_MEDIUM_AND_ABOVE)
//             .build(),
//         SafetySetting.newBuilder()
//             .setCategory(HarmCategory.HARM_CATEGORY_SEXUALLY_EXPLICIT)
//             .setThreshold(SafetySetting.HarmBlockThreshold.BLOCK_MEDIUM_AND_ABOVE)
//             .build(),
//         SafetySetting.newBuilder()
//             .setCategory(HarmCategory.HARM_CATEGORY_HARASSMENT)
//             .setThreshold(SafetySetting.HarmBlockThreshold.BLOCK_MEDIUM_AND_ABOVE)
//             .build()
//     );
//       GenerativeModel model =
//           GenerativeModel.newBuilder()
//               .setModelName("gemini-1.5-flash-001")
//               .setVertexAi(vertexAi)
//               .setGenerationConfig(generationConfig)
//               .setSafetySettings(safetySettings)
//               .build();


//     var content = ContentMaker.fromMultiModalData();
//     ResponseStream<GenerateContentResponse> responseStream = model.generateContentStream(content);

//       // Do something with the response
//       responseStream.stream().forEach(System.out::println);
//     }
//   }
}