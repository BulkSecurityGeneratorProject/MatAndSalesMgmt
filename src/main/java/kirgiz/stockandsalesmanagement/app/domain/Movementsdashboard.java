package kirgiz.stockandsalesmanagement.app.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A Movementsdashboard.
 */
@Entity
@Table(name = "movementsdashboard")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "movementsdashboard")
public class Movementsdashboard implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "movement_date", nullable = false)
    private LocalDate movementDate;

    @NotNull
    @Column(name = "material_class", nullable = false)
    private String materialClass;

    @NotNull
    @Column(name = "initial_location", nullable = false)
    private String initialLocation;

    @NotNull
    @Column(name = "final_location", nullable = false)
    private String finalLocation;

    @Column(name = "profit_or_loss", precision=10, scale=2)
    private BigDecimal profitOrLoss;

    @NotNull
    @Column(name = "number_of_items", nullable = false)
    private Integer numberOfItems;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getMovementDate() {
        return movementDate;
    }

    public Movementsdashboard movementDate(LocalDate movementDate) {
        this.movementDate = movementDate;
        return this;
    }

    public void setMovementDate(LocalDate movementDate) {
        this.movementDate = movementDate;
    }

    public String getMaterialClass() {
        return materialClass;
    }

    public Movementsdashboard materialClass(String materialClass) {
        this.materialClass = materialClass;
        return this;
    }

    public void setMaterialClass(String materialClass) {
        this.materialClass = materialClass;
    }

    public String getInitialLocation() {
        return initialLocation;
    }

    public Movementsdashboard initialLocation(String initialLocation) {
        this.initialLocation = initialLocation;
        return this;
    }

    public void setInitialLocation(String initialLocation) {
        this.initialLocation = initialLocation;
    }

    public String getFinalLocation() {
        return finalLocation;
    }

    public Movementsdashboard finalLocation(String finalLocation) {
        this.finalLocation = finalLocation;
        return this;
    }

    public void setFinalLocation(String finalLocation) {
        this.finalLocation = finalLocation;
    }

    public BigDecimal getProfitOrLoss() {
        return profitOrLoss;
    }

    public Movementsdashboard profitOrLoss(BigDecimal profitOrLoss) {
        this.profitOrLoss = profitOrLoss;
        return this;
    }

    public void setProfitOrLoss(BigDecimal profitOrLoss) {
        this.profitOrLoss = profitOrLoss;
    }

    public Integer getNumberOfItems() {
        return numberOfItems;
    }

    public Movementsdashboard numberOfItems(Integer numberOfItems) {
        this.numberOfItems = numberOfItems;
        return this;
    }

    public void setNumberOfItems(Integer numberOfItems) {
        this.numberOfItems = numberOfItems;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Movementsdashboard movementsdashboard = (Movementsdashboard) o;
        if (movementsdashboard.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), movementsdashboard.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Movementsdashboard{" +
            "id=" + getId() +
            ", movementDate='" + getMovementDate() + "'" +
            ", materialClass='" + getMaterialClass() + "'" +
            ", initialLocation='" + getInitialLocation() + "'" +
            ", finalLocation='" + getFinalLocation() + "'" +
            ", profitOrLoss=" + getProfitOrLoss() +
            ", numberOfItems=" + getNumberOfItems() +
            "}";
    }
}
