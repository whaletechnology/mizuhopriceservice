package io.freddief.mizuho.priceservice.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Maps;
import com.google.common.collect.Table;
import com.google.common.collect.Tables;
import io.freddief.mizuho.priceservice.domain.instrument.Instrument;
import io.freddief.mizuho.priceservice.domain.price.Price;
import io.freddief.mizuho.priceservice.domain.vendor.Vendor;
import org.apache.activemq.spring.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.spring.SpringCamelContext;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Collection;
import java.util.Map;

@Configuration
@EnableScheduling
public class Config {

    @Value(value = "${spring.activemq.broker-url}")
    String activeMQUrl;
    @Value(value = "${spring.activemq.user}")
    String activeMQUser;
    @Value(value = "${spring.activemq.password}")
    String activeMQPassword;

    @Bean
    public ActiveMQConnectionFactory activeMQConnectionFactory() {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
        activeMQConnectionFactory.setBrokerURL(activeMQUrl);
        activeMQConnectionFactory.setUserName(activeMQUser);
        activeMQConnectionFactory.setPassword(activeMQPassword);
        return activeMQConnectionFactory;
    }

    @Bean
    public CamelContext camelContext(ApplicationContext applicationContext,
                                     Collection<RoutesBuilder> routesBuilders,
                                     ActiveMQConnectionFactory activeMQConnectionFactory) {
        SpringCamelContext camelContext = new SpringCamelContext(applicationContext);

        camelContext.addComponent("active-mq", JmsComponent.jmsComponentAutoAcknowledge(activeMQConnectionFactory));
        routesBuilders.forEach(routeBuilder -> {
            try {
                camelContext.addRoutes(routeBuilder);
            } catch (Exception e) {
                throw new BeanCreationException(String.format("Error adding route %s to CamelContext", routeBuilder.toString()));
            }
        });
        return camelContext;
    }

    @Bean
    ObjectMapper objectMapper() {
        return new ObjectMapper()
            .registerModule(new ParameterNamesModule())
            .registerModule(new Jdk8Module())
            .registerModule(new JavaTimeModule());
    }

    @Bean
    Table<Vendor, Instrument, Price> pricesTable() {
        return Tables.synchronizedTable(HashBasedTable.create());
    }

    @Bean
    Map<String, Instrument> instrumentsByIdMap() {
        return Maps.newConcurrentMap();
    }

    @Bean
    Map<String, Vendor> vendorsByIdMap() {
        return Maps.newConcurrentMap();
    }
}
