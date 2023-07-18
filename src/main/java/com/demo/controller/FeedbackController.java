package com.demo.controller;

import com.demo.data.DemoData;
import com.demo.error.DemoException;
import com.demo.model.Result;
import com.demo.serivce.FeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.script.*;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringWriter;

@RestController
@RequestMapping
@RequiredArgsConstructor
@CrossOrigin
public class FeedbackController {
    private FeedbackService feedbackService;

    public static void formatTranscript(String data) throws ScriptException, IOException {
        try {
            // Provide the path to the Python script you want to run
            String pythonScript = "src/main/java/com/demo/util/formatTranscript.py";

            StringWriter writer = new StringWriter(); //ouput will be stored here

            ScriptEngineManager manager = new ScriptEngineManager();
            ScriptEngine engine = manager.getEngineByName("python");

            // Set data variables in the script's context
            ScriptContext context = new SimpleScriptContext();
            context.setWriter(writer); // configures output redirection
            context.setAttribute("data", data, ScriptContext.ENGINE_SCOPE);

            // Execute the Python script
            engine.eval(new FileReader(pythonScript), context);

            System.out.println(writer.toString());

        } catch (IOException e) {
            System.out.println("exception happened - here's what I know: ");
            e.printStackTrace();
            System.exit(-1);
        }
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
