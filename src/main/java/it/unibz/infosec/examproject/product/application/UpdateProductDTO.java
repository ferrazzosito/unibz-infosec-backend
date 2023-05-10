package it.unibz.infosec.examproject.product.application;

public class UpdateProductDTO extends CreateProductDTO{

    private Long id;
    public UpdateProductDTO() {super();}

    public UpdateProductDTO(Long id, String name, int cost, Long vendorId){

        super(name, cost, vendorId);
        this.id=id;
    }

    public Long getId(){return id;}

    public void setId(Long id){this.id=id;}
}

