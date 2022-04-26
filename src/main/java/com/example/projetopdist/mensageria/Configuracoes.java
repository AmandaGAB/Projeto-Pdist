package com.example.projetopdist.mensageria;
import com.example.projetopdist.controllers.FuncionarioController;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;

public class Configuracoes {
    public static final String EXCHANGE_NAME = "projetoexchange";
    public static final String QUEUE_NAME = "filaHoras";
    public static final String ROUTING_KEY = "funcionariosapi";


    @Bean
    TopicExchange Exchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    Queue createQueue() {
        return new Queue(QUEUE_NAME, false, false, false);
    }

    @Bean
    MessageListenerAdapter listener(FuncionarioController controller){
        return new MessageListenerAdapter(controller, "gethoras");
    }


    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
    }

    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory
            , MessageListenerAdapter messageListenerAdapter){
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(QUEUE_NAME);
//         messageListenerAdapter.setMessageConverter(new Jackson2JsonMessageConverter());
        container.setMessageListener(messageListenerAdapter);

        return container;
    }

}
