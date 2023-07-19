package com.demo.controller;

import com.demo.data.TranscriptData;
import com.demo.data.TranscriptDataContainer;
import lombok.Data;
import org.python.util.PythonInterpreter;

import com.demo.error.DemoException;
import com.demo.model.Result;
import com.demo.serivce.FeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.script.*;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringWriter;

import java.io.FileInputStream;
import java.io.InputStream;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping
@RequiredArgsConstructor
@CrossOrigin
public class FeedbackController {
    private FeedbackService feedbackService;

    public static void formatTranscript(String data) throws ScriptException, IOException {

        try {
            // Remove newline characters from the data
            String jsonString = data.replace("\\n", "").replaceAll("\\s+", " ").trim();
            String newString = jsonString.replaceAll("\\\\", "");

            System.out.println(newString);

            ObjectMapper objectMapper = new ObjectMapper();
            TranscriptDataContainer dataContainer = objectMapper.readValue(newString, TranscriptDataContainer.class);

            Map<String, String> messageMap = new HashMap<>();
            List<TranscriptData> messages = dataContainer.getMessages();
            for (TranscriptData message : messages) {
                String senderName = message.getSenderName();
                String content = message.getMessage();
                messageMap.put(senderName, content);
            }

            System.out.println(messageMap);

        } catch (Exception e) {
            e.printStackTrace();
        }

//        try (PythonInterpreter pyInterp = new PythonInterpreter()) {
//            String pythonScript = "src/main/java/com/demo/util/formatTranscript.py";
//            InputStream pythonScriptInputStream = null;
//
//            pythonScriptInputStream = new FileInputStream(pythonScript);
//
//            String[] arguments = { data };
//            pyInterp.execfile(pythonScriptInputStream, data);
//        }

//        try {
//            // Provide the path to the Python script you want to run
//            String pythonScript = "src/main/java/com/demo/util/formatTranscript.py";
//
//            StringWriter writer = new StringWriter(); //ouput will be stored here
//
//            ScriptEngineManager manager = new ScriptEngineManager(ClassLoader.getSystemClassLoader());
//            System.out.println(manager);
//            ScriptEngine engine = manager.getEngineByName("python");
//
//            System.out.println(engine);
//
//            for (ScriptEngineFactory factory : new ScriptEngineManager().getEngineFactories()) {
//                System.out.println(factory.getEngineName() + " (" + factory.getEngineVersion() + ")");
//            }
//
//            // Set data variables in the script's context
//            ScriptContext context = new SimpleScriptContext();
//            context.setWriter(writer); // configures output redirection
//            context.setAttribute("data", data, ScriptContext.ENGINE_SCOPE);
//
//            // Execute the Python script
//            engine.eval(new FileReader(pythonScript), context);
//
//            System.out.println(writer.toString());
//
//        } catch (IOException e) {
//            System.out.println("exception happened - here's what I know: ");
//            e.printStackTrace();
//            System.exit(-1);
//        }
    }

    @PostMapping("/read_transcript")
    public Result query(@RequestBody String param) throws DemoException {
        System.out.println(param);

        try {
            formatTranscript(param);
        } catch (ScriptException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return Result.success();
    }
}
