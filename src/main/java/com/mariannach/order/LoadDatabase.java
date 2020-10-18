package com.mariannach.order;

        import org.slf4j.Logger;
        import org.slf4j.LoggerFactory;
        import org.springframework.boot.CommandLineRunner;
        import org.springframework.context.annotation.Bean;
        import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(ClientRepository clientRepository, OrderRepository orderRepository) {

        return args -> {
            clientRepository.save(new Client("Bilbo", "Baggins", "burglar"));
            clientRepository.save(new Client("Frodo", "Baggins", "thief"));

            clientRepository.findAll().forEach(employee -> log.info("Preloaded " + employee));


            orderRepository.save(new Order("MacBook Pro", Status.COMPLETED));
            orderRepository.save(new Order("iPhone", Status.IN_PROGRESS));

            orderRepository.findAll().forEach(order -> {
                log.info("Preloaded " + order);
            });

        };
    }
}