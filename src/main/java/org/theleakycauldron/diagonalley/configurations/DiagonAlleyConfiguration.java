package org.theleakycauldron.diagonalley.configurations;

import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.impl.TimeBasedReorderedGenerator;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;
/**
 * @author: Vijaysurya Mandala
 * @github: github/mandalavijaysurya (<a href="https://www.github.com/mandalavijaysurya"> Github</a>)
 */
@Configuration
public class DiagonAlleyConfiguration {

    @Bean
    public TimeBasedReorderedGenerator getTimeBasedReorderedGenerator(){
        return Generators.timeBasedReorderedGenerator();
    }

    @Bean
    public KafkaAdmin.NewTopics createTopics(){
        NewTopic diagonAlleyCreate = new NewTopic("diagon-alley-create", 1, (short) 1);
        NewTopic diagonAlleyUpdate = new NewTopic("diagon-alley-update", 1, (short) 1);

        return new KafkaAdmin.NewTopics(diagonAlleyCreate, diagonAlleyUpdate);
    }
}
