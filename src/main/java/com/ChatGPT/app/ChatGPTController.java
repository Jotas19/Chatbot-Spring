package com.ChatGPT.app;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.github.flashvayne.chatgpt.dto.ChatRequest;
import io.github.flashvayne.chatgpt.dto.ChatResponse;
import io.github.flashvayne.chatgpt.service.ChatgptService;

@RestController
@RequestMapping("/chatboot")
public class ChatGPTController  implements InitializingBean {
	
	@Autowired
    private ChatgptService chatgptService;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println(" ===== Starting Chat GPT Boot ==== ");
	}
	
	@GetMapping("/chat")
	public String chatWith(@RequestParam String message) {
	  System.out.println(message);
	  return chatgptService.sendMessage(message);
	}

	@GetMapping("/prompt")
	public ChatResponse prompt(@RequestParam String message) {
			//máximo de tokens que se pueden generar en una respuesta
	        Integer maxTokens = 300;

	        //Modelo que se utiliza para generar texto utilizando la API de OpenAI
	        String model = "text-davinci-003";

	        //Creatividad de las respuestas generadas
	        Double temperature = 0.5;

	        //Diversidad en las respuestas generadas
	        Double topP = 1.0;
	        
	        //Mostrar las solicitudes y la respuestas de una conversación con chatGPT
	        ChatRequest chatRequest = new ChatRequest(model, message, maxTokens,temperature,topP);
	        ChatResponse res =  chatgptService.sendChatRequest(chatRequest);
	        System.out.println("Response was: " + res.toString());
	        return res;

	    }

}
