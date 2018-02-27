package kirgiz.stockandsalesmanagement.app.service.dto;


import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Material entity.
 */
public class MaterialDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 20)
    private String code;

    @NotNull
    @Size(max = 200)
    private String description;

    @NotNull
    private LocalDate creationDate;

    @Size(max = 500)
    private String comments;

    private Long materialclassification6Id;

    private String materialclassification6Name;

    private Long buycurrencyId;

    private String buycurrencyISOCode;

    private Long sellcurrencyId;

    private String sellcurrencyISOCode;

    private Long lot5Id;

    private String lot5Code;

    private Long materialclassification1Id;

    private String materialclassification1Code;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Long getMaterialclassification6Id() {
        return materialclassification6Id;
    }

    public void setMaterialclassification6Id(Long materialclassificationId) {
        this.materialclassification6Id = materialclassificationId;
    }

    public String getMaterialclassification6Name() {
        return materialclassification6Name;
    }

    public void setMaterialclassification6Name(String materialclassificationName) {
        this.materialclassification6Name = materialclassificationName;
    }

    public Long getBuycurrencyId() {
        return buycurrencyId;
    }

    public void setBuycurrencyId(Long currencyId) {
        this.buycurrencyId = currencyId;
    }

    public String getBuycurrencyISOCode() {
        return buycurrencyISOCode;
    }

    public void setBuycurrencyISOCode(String currencyISOCode) {
        this.buycurrencyISOCode = currencyISOCode;
    }

    public Long getSellcurrencyId() {
        return sellcurrencyId;
    }

    public void setSellcurrencyId(Long currencyId) {
        this.sellcurrencyId = currencyId;
    }

    public String getSellcurrencyISOCode() {
        return sellcurrencyISOCode;
    }

    public void setSellcurrencyISOCode(String currencyISOCode) {
        this.sellcurrencyISOCode = currencyISOCode;
    }

    public Long getLot5Id() {
        return lot5Id;
    }

    public void setLot5Id(Long lotId) {
        this.lot5Id = lotId;
    }

    public String getLot5Code() {
        return lot5Code;
    }

    public void setLot5Code(String lotCode) {
        this.lot5Code = lotCode;
    }

    public Long getMaterialclassification1Id() {
        return materialclassification1Id;
    }

    public void setMaterialclassification1Id(Long materialclassificationId) {
        this.materialclassification1Id = materialclassificationId;
    }

    public String getMaterialclassification1Code() {
        return materialclassification1Code;
    }

    public void setMaterialclassification1Code(String materialclassificationCode) {
        this.materialclassification1Code = materialclassificationCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MaterialDTO materialDTO = (MaterialDTO) o;
        if(materialDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), materialDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MaterialDTO{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", description='" + getDescription() + "'" +
            ", creationDate='" + getCreationDate() + "'" +
            ", comments='" + getComments() + "'" +
            "}";
    }
}
