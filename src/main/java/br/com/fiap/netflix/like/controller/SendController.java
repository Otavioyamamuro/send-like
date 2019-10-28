package br.com.fiap.netflix.like.controller;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.TimeoutException;

import static org.springframework.amqp.rabbit.core.RabbitAdmin.QUEUE_NAME;

@RestController
public class SendController {

    @Autowired
    private RabbitTemplate template;

    @Autowired
    private Queue queue;


    @ApiOperation(
            value="Envia Mensagem de like",
            response= int.class,
            notes="Essa operação envia Mensagem de like")
    @ApiResponses(value= {
            @ApiResponse(
                    code=200,
                    message="Envio feita com sucesso",
                    response= int.class
            ),
            @ApiResponse(
                    code=500,
                    message="Erro",
                    response=int.class
            )

    })
    @GetMapping("/send/{id}")
    public void send(@PathVariable("id") String id) throws IOException, TimeoutException {
     // this.template.convertAndSend("fila_do_like", id);

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        try (Connection connection = factory.newConnection();

             Channel channel = connection.createChannel()) {

            channel.queueDeclare("fila_do_like", false, false, false, null);
            String message = id;
            channel.basicPublish("", "fila_do_like", null, message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");

        }



    }


}
