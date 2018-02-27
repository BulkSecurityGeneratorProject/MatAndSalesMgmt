package kirgiz.stockandsalesmanagement.app.service.dto;


import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Materialhistory entity.
 */
public class MaterialhistoryDTO implements Serializable {

    private Long id;

    @Size(max = 20)
    private String code;

    @NotNull
    private LocalDate creationDate;

    @Size(max = 500)
    private String comments;

    private Set<MaterialDTO> itemTransfereds = new HashSet<>();

    private Long transferclassification2Id;

    private String transferclassification2Name;

    private Long warehousefromId;

    private String warehousefromName;

    private Long warehousetoId;

    private String warehousetoName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Set<MaterialDTO> getItemTransfereds() {
        return itemTransfereds;
    }

    public void setItemTransfereds(Set<MaterialDTO> materials) {
        this.itemTransfereds = materials;
    }

    public Long getTransferclassification2Id() {
        return transferclassification2Id;
    }

    public void setTransferclassification2Id(Long transferclassificationId) {
        this.transferclassification2Id = transferclassificationId;
    }

    public String getTransferclassification2Name() {
        return transferclassification2Name;
    }

    public void setTransferclassification2Name(String transferclassificationName) {
        this.transferclassification2Name = transferclassificationName;
    }

    public Long getWarehousefromId() {
        return warehousefromId;
    }

    public void setWarehousefromId(Long thirdId) {
        this.warehousefromId = thirdId;
    }

    public String getWarehousefromName() {
        return warehousefromName;
    }

    public void setWarehousefromName(String thirdName) {
        this.warehousefromName = thirdName;
    }

    public Long getWarehousetoId() {
        return warehousetoId;
    }

    public void setWarehousetoId(Long thirdId) {
        this.warehousetoId = thirdId;
    }

    public String getWarehousetoName() {
        return warehousetoName;
    }

    public void setWarehousetoName(String thirdName) {
        this.warehousetoName = thirdName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MaterialhistoryDTO materialhistoryDTO = (MaterialhistoryDTO) o;
        if(materialhistoryDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), materialhistoryDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MaterialhistoryDTO{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", creationDate='" + getCreationDate() + "'" +
            ", comments='" + getComments() + "'" +
            "}";
    }
}
