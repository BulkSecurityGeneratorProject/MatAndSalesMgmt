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
 * A Materialclassification.
 */
@Entity
@Table(name = "materialclassification")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "materialclassification")
public class Materialclassification implements Serializable {

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
    @Size(max = 60)
    @Column(name = "name", length = 60, nullable = false)
    private String name;

    @Size(max = 500)
    @Column(name = "comments", length = 500)
    private String comments;

    @OneToMany(mappedBy = "materialclassification6")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Material> material1S = new HashSet<>();

    @OneToMany(mappedBy = "materialclassification1")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Material> material4S = new HashSet<>();

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

    public Materialclassification code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public Materialclassification name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComments() {
        return comments;
    }

    public Materialclassification comments(String comments) {
        this.comments = comments;
        return this;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Set<Material> getMaterial1S() {
        return material1S;
    }

    public Materialclassification material1S(Set<Material> materials) {
        this.material1S = materials;
        return this;
    }

    public Materialclassification addMaterial1(Material material) {
        this.material1S.add(material);
        material.setMaterialclassification6(this);
        return this;
    }

    public Materialclassification removeMaterial1(Material material) {
        this.material1S.remove(material);
        material.setMaterialclassification6(null);
        return this;
    }

    public void setMaterial1S(Set<Material> materials) {
        this.material1S = materials;
    }

    public Set<Material> getMaterial4S() {
        return material4S;
    }

    public Materialclassification material4S(Set<Material> materials) {
        this.material4S = materials;
        return this;
    }

    public Materialclassification addMaterial4(Material material) {
        this.material4S.add(material);
        material.setMaterialclassification1(this);
        return this;
    }

    public Materialclassification removeMaterial4(Material material) {
        this.material4S.remove(material);
        material.setMaterialclassification1(null);
        return this;
    }

    public void setMaterial4S(Set<Material> materials) {
        this.material4S = materials;
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
        Materialclassification materialclassification = (Materialclassification) o;
        if (materialclassification.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), materialclassification.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Materialclassification{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", name='" + getName() + "'" +
            ", comments='" + getComments() + "'" +
            "}";
    }
}
