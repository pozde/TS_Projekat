package ba.tim2.preporucivanjesadrzajapogodnosti.Messaging;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    public static final String QUEUE1 = "film-queue";
    public static final String EXCHANGE1 = "film-exchange";
    public static final String ROUTING_KEY1 = "film-routingKey";

    @Bean
    public Queue queue1() {
        return new Queue(QUEUE1);
    }
    @Bean
    public TopicExchange exchange1() {
        return new TopicExchange(EXCHANGE1, true, false);
    }

    @Bean
    public Binding binding1(){
        return BindingBuilder.bind(queue1()).to(exchange1()).with(ROUTING_KEY1);
    }




    public static final String QUEUE2 = "response-film-queue";
    public static final String EXCHANGE2 = "response-film-exchange";
    public static final String ROUTING_KEY2 = "response-film-routingKey";

    @Bean
    public Queue queue2() {
        return new Queue(QUEUE2);
    }
    @Bean
    public TopicExchange exchange2() {
        return new TopicExchange(EXCHANGE2, true, false);
    }

    @Bean
    public Binding binding2(){
        return BindingBuilder.bind(queue1()).to(exchange1()).with(ROUTING_KEY2);
    }


    @Bean
    public Jackson2JsonMessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory factory) {
        RabbitTemplate template = new RabbitTemplate(factory);
        template.setMessageConverter(converter());
        return template;
    }
}

