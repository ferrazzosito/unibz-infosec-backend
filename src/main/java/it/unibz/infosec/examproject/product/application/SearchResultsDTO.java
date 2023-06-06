package it.unibz.infosec.examproject.product.application;

import it.unibz.infosec.examproject.product.domain.Product;
import lombok.Data;

import java.util.List;

public record SearchResultsDTO(List<Product> results, String query) {
}
