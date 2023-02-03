package com.godknows.challengeCrudClient.repositories;

import com.godknows.challengeCrudClient.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
