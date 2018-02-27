package kirgiz.stockandsalesmanagement.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Lot.
 */
@Entity
@Table(name = "lot")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "lot")
public class Lot implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(max = 20)
    @Column(name = "code", length = 20, nullable = false)
    private String code;

    @NotNull
    @Size(max = 200)
    @Column(name = "description", length = 200, nullable = false)
    private String description;

    @NotNull
    @Column(name = "creation_date", nullable = false)
    private LocalDate creationDate;

    @NotNull
    @Column(name = "number_of_items", nullable = false)
    private Long numberOfItems;

    @Size(max = 500)
    @Column(name = "comments", length = 500)
    private String comments;

    @OneToMany(mappedBy = "lot5")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Material> material4S = new HashSet<>();

    @ManyToOne(optional = false)
    @NotNull
    private Currency buycurrency1;

    @ManyToOne(optional = false)
    @NotNull
    private Currency sellcurrency1;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public Lot code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public Lot description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public Lot creationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public Long getNumberOfItems() {
        return numberOfItems;
    }

    public Lot numberOfItems(Long numberOfItems) {
        this.numberOfItems = numberOfItems;
        return this;
    }

    public void setNumberOfItems(Long numberOfItems) {
        this.numberOfItems = numberOfItems;
    }

    public String getComments() {
        return comments;
    }

    public Lot comments(String comments) {
        this.comments = comments;
        return this;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Set<Material> getMaterial4S() {
        return material4S;
    }

    public Lot material4S(Set<Material> materials) {
        this.material4S = materials;
        return this;
    }

    public Lot addMaterial4(Material material) {
        this.material4S.add(material);
        material.setLot5(this);
        return this;
    }

    public Lot removeMaterial4(Material material) {
        this.material4S.remove(material);
        material.setLot5(null);
        return this;
    }

    public void setMaterial4S(Set<Material> materials) {
        this.material4S = materials;
    }

    public Currency getBuycurrency1() {
        return buycurrency1;
    }

    public Lot buycurrency1(Currency currency) {
        this.buycurrency1 = currency;
        return this;
    }

    public void setBuycurrency1(Currency currency) {
        this.buycurrency1 = currency;
    }

    public Currency getSellcurrency1() {
        return sellcurrency1;
    }

    public Lot sellcurrency1(Currency currency) {
        this.sellcurrency1 = currency;
        return this;
    }

    public void setSellcurrency1(Currency currency) {
        this.sellcurrency1 = currency;
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
        Lot lot = (Lot) o;
        if (lot.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), lot.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Lot{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", description='" + getDescription() + "'" +
            ", creationDate='" + getCreationDate() + "'" +
            ", numberOfItems=" + getNumberOfItems() +
            ", comments='" + getComments() + "'" +
            "}";
    }
}
