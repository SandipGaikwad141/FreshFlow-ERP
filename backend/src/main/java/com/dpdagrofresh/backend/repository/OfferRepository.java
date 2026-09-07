package com.dpdagrofresh.backend.repository;

import com.dpdagrofresh.backend.model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {

    List<Offer> findByActiveTrueOrderByIdDesc();
}