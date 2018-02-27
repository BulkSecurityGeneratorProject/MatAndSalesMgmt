package kirgiz.stockandsalesmanagement.app.service.dto;


import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Lot entity.
 */
public class LotDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 20)
    private String code;

    @NotNull
    @Size(max = 200)
    private String description;

    @NotNull
    private LocalDate creationDate;

    @NotNull
    private Long numberOfItems;

    @Size(max = 500)
    private String comments;

    private Long buycurrency1Id;

    private String buycurrency1ISOCode;

    private Long sellcurrency1Id;

    private String sellcurrency1ISOCode;

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

    public Long getNumberOfItems() {
        return numberOfItems;
    }

    public void setNumberOfItems(Long numberOfItems) {
        this.numberOfItems = numberOfItems;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Long getBuycurrency1Id() {
        return buycurrency1Id;
    }

    public void setBuycurrency1Id(Long currencyId) {
        this.buycurrency1Id = currencyId;
    }

    public String getBuycurrency1ISOCode() {
        return buycurrency1ISOCode;
    }

    public void setBuycurrency1ISOCode(String currencyISOCode) {
        this.buycurrency1ISOCode = currencyISOCode;
    }

    public Long getSellcurrency1Id() {
        return sellcurrency1Id;
    }

    public void setSellcurrency1Id(Long currencyId) {
        this.sellcurrency1Id = currencyId;
    }

    public String getSellcurrency1ISOCode() {
        return sellcurrency1ISOCode;
    }

    public void setSellcurrency1ISOCode(String currencyISOCode) {
        this.sellcurrency1ISOCode = currencyISOCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        LotDTO lotDTO = (LotDTO) o;
        if(lotDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), lotDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "LotDTO{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", description='" + getDescription() + "'" +
            ", creationDate='" + getCreationDate() + "'" +
            ", numberOfItems=" + getNumberOfItems() +
            ", comments='" + getComments() + "'" +
            "}";
    }
}
