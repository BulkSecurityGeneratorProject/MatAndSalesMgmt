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
 * A Third.
 */
@Entity
@Table(name = "third")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "third")
public class Third implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(max = 3)
    @Column(name = "code", length = 3, nullable = false)
    private String code;

    @NotNull
    @Size(max = 60)
    @Column(name = "name", length = 60, nullable = false)
    private String name;

    @Size(max = 500)
    @Column(name = "comments", length = 500)
    private String comments;

    @OneToMany(mappedBy = "warehousefrom")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Materialhistory> materialhistory1S = new HashSet<>();

    @OneToMany(mappedBy = "warehouseto")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Materialhistory> materialhistory2S = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "third_address3",
               joinColumns = @JoinColumn(name="thirds_id", referencedColumnName="id"),
               inverseJoinColumns = @JoinColumn(name="address3s_id", referencedColumnName="id"))
    private Set<Address> address3S = new HashSet<>();

    @ManyToOne(optional = false)
    @NotNull
    private Thirdclassification thirdClassification9;

    @ManyToOne(optional = false)
    @NotNull
    private Civility civility1;

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

    public Third code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public Third name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComments() {
        return comments;
    }

    public Third comments(String comments) {
        this.comments = comments;
        return this;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Set<Materialhistory> getMaterialhistory1S() {
        return materialhistory1S;
    }

    public Third materialhistory1S(Set<Materialhistory> materialhistories) {
        this.materialhistory1S = materialhistories;
        return this;
    }

    public Third addMaterialhistory1(Materialhistory materialhistory) {
        this.materialhistory1S.add(materialhistory);
        materialhistory.setWarehousefrom(this);
        return this;
    }

    public Third removeMaterialhistory1(Materialhistory materialhistory) {
        this.materialhistory1S.remove(materialhistory);
        materialhistory.setWarehousefrom(null);
        return this;
    }

    public void setMaterialhistory1S(Set<Materialhistory> materialhistories) {
        this.materialhistory1S = materialhistories;
    }

    public Set<Materialhistory> getMaterialhistory2S() {
        return materialhistory2S;
    }

    public Third materialhistory2S(Set<Materialhistory> materialhistories) {
        this.materialhistory2S = materialhistories;
        return this;
    }

    public Third addMaterialhistory2(Materialhistory materialhistory) {
        this.materialhistory2S.add(materialhistory);
        materialhistory.setWarehouseto(this);
        return this;
    }

    public Third removeMaterialhistory2(Materialhistory materialhistory) {
        this.materialhistory2S.remove(materialhistory);
        materialhistory.setWarehouseto(null);
        return this;
    }

    public void setMaterialhistory2S(Set<Materialhistory> materialhistories) {
        this.materialhistory2S = materialhistories;
    }

    public Set<Address> getAddress3S() {
        return address3S;
    }

    public Third address3S(Set<Address> addresses) {
        this.address3S = addresses;
        return this;
    }

    public Third addAddress3(Address address) {
        this.address3S.add(address);
        address.getThirdaddresses().add(this);
        return this;
    }

    public Third removeAddress3(Address address) {
        this.address3S.remove(address);
        address.getThirdaddresses().remove(this);
        return this;
    }

    public void setAddress3S(Set<Address> addresses) {
        this.address3S = addresses;
    }

    public Thirdclassification getThirdClassification9() {
        return thirdClassification9;
    }

    public Third thirdClassification9(Thirdclassification thirdclassification) {
        this.thirdClassification9 = thirdclassification;
        return this;
    }

    public void setThirdClassification9(Thirdclassification thirdclassification) {
        this.thirdClassification9 = thirdclassification;
    }

    public Civility getCivility1() {
        return civility1;
    }

    public Third civility1(Civility civility) {
        this.civility1 = civility;
        return this;
    }

    public void setCivility1(Civility civility) {
        this.civility1 = civility;
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
        Third third = (Third) o;
        if (third.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), third.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Third{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", name='" + getName() + "'" +
            ", comments='" + getComments() + "'" +
            "}";
    }
}
