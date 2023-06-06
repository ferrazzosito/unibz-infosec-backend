package it.unibz.infosec.examproject.product.application;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SearchProductDTO {
    private String query;
}
