package demo;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
public class MainConfiguration {

	@Autowired
	private MainProperties properties;

	@Bean
	public MainProperties properties() {
		return properties;
	}

	@Bean
	public KafkaTemplate<String, String> template() {
		KafkaTemplate<String, String> template = new KafkaTemplate<>(producerFactory());
		template.setDefaultTopic(properties.getTopics());
		return template;
	}

	@Bean
	public ProducerFactory<String, String> producerFactory() {
		return new DefaultKafkaProducerFactory<>(producerConfiguration(), new StringSerializer(),
				new StringSerializer());
	}

	@Bean
	public Map<String, Object> producerConfiguration() {
		Map<String, Object> configuration = new HashMap<>();
		configuration.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, properties.getProducerBootstrap());
		return configuration;
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConcurrency(1);
		factory.setConsumerFactory(consumerFactory());
		return factory;
	}

	@Bean
	public ConsumerFactory<String, String> consumerFactory() {
		return new DefaultKafkaConsumerFactory<>(consumerConfiguration(), new StringDeserializer(),
				new StringDeserializer());
	}

	@Bean
	public Map<String, Object> consumerConfiguration() {
		Map<String, Object> configuration = new HashMap<>();
		configuration.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, properties.getConsumerBootstrap());
		configuration.put(ConsumerConfig.GROUP_ID_CONFIG, properties.getGroup());
		configuration.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
		configuration.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "100");
		configuration.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "15000");
		return configuration;
	}

}
