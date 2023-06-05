package it.unibz.infosec.examproject.product.application;

public class UpdateProductDTO extends CreateProductDTO {

    private Long id;

    public UpdateProductDTO(Long id, String name, int cost){
        super(name, cost);
        this.id = id;
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

