package com.mariannach.order;

import org.springframework.data.jpa.repository.JpaRepository;

interface ClientRepository extends JpaRepository<Client, Long> {

}
