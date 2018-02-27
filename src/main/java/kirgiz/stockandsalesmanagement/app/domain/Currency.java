package kirgiz.stockandsalesmanagement.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Currency.
 */
@Entity
@Table(name = "currency")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "currency")
public class Currency implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(max = 3)
    @Column(name = "i_so_code", length = 3, nullable = false)
    private String iSOCode;

    @NotNull
    @Size(max = 60)
    @Column(name = "name", length = 60, nullable = false)
    private String name;

    @OneToMany(mappedBy = "baseCurrency")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Company> company1S = new HashSet<>();

    @OneToMany(mappedBy = "buycurrency")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Material> material2S = new HashSet<>();

    @OneToMany(mappedBy = "sellcurrency")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Material> material3S = new HashSet<>();

    @OneToMany(mappedBy = "buycurrency1")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Lot> lot1S = new HashSet<>();

    @OneToMany(mappedBy = "sellcurrency1")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Lot> lot2S = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getiSOCode() {
        return iSOCode;
    }

    public Currency iSOCode(String iSOCode) {
        this.iSOCode = iSOCode;
        return this;
    }

    public void setiSOCode(String iSOCode) {
        this.iSOCode = iSOCode;
    }

    public String getName() {
        return name;
    }

    public Currency name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Company> getCompany1S() {
        return company1S;
    }

    public Currency company1S(Set<Company> companies) {
        this.company1S = companies;
        return this;
    }

    public Currency addCompany1(Company company) {
        this.company1S.add(company);
        company.setBaseCurrency(this);
        return this;
    }

    public Currency removeCompany1(Company company) {
        this.company1S.remove(company);
        company.setBaseCurrency(null);
        return this;
    }

    public void setCompany1S(Set<Company> companies) {
        this.company1S = companies;
    }

    public Set<Material> getMaterial2S() {
        return material2S;
    }

    public Currency material2S(Set<Material> materials) {
        this.material2S = materials;
        return this;
    }

    public Currency addMaterial2(Material material) {
        this.material2S.add(material);
        material.setBuycurrency(this);
        return this;
    }

    public Currency removeMaterial2(Material material) {
        this.material2S.remove(material);
        material.setBuycurrency(null);
        return this;
    }

    public void setMaterial2S(Set<Material> materials) {
        this.material2S = materials;
    }

    public Set<Material> getMaterial3S() {
        return material3S;
    }

    public Currency material3S(Set<Material> materials) {
        this.material3S = materials;
        return this;
    }

    public Currency addMaterial3(Material material) {
        this.material3S.add(material);
        material.setSellcurrency(this);
        return this;
    }

    public Currency removeMaterial3(Material material) {
        this.material3S.remove(material);
        material.setSellcurrency(null);
        return this;
    }

    public void setMaterial3S(Set<Material> materials) {
        this.material3S = materials;
    }

    public Set<Lot> getLot1S() {
        return lot1S;
    }

    public Currency lot1S(Set<Lot> lots) {
        this.lot1S = lots;
        return this;
    }

    public Currency addLot1(Lot lot) {
        this.lot1S.add(lot);
        lot.setBuycurrency1(this);
        return this;
    }

    public Currency removeLot1(Lot lot) {
        this.lot1S.remove(lot);
        lot.setBuycurrency1(null);
        return this;
    }

    public void setLot1S(Set<Lot> lots) {
        this.lot1S = lots;
    }

    public Set<Lot> getLot2S() {
        return lot2S;
    }

    public Currency lot2S(Set<Lot> lots) {
        this.lot2S = lots;
        return this;
    }

    public Currency addLot2(Lot lot) {
        this.lot2S.add(lot);
        lot.setSellcurrency1(this);
        return this;
    }

    public Currency removeLot2(Lot lot) {
        this.lot2S.remove(lot);
        lot.setSellcurrency1(null);
        return this;
    }

    public void setLot2S(Set<Lot> lots) {
        this.lot2S = lots;
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
        Currency currency = (Currency) o;
        if (currency.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), currency.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Currency{" +
            "id=" + getId() +
            ", iSOCode='" + getiSOCode() + "'" +
            ", name='" + getName() + "'" +
            "}";
    }
}
