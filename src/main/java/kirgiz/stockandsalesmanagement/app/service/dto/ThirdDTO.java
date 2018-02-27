package kirgiz.stockandsalesmanagement.app.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Third entity.
 */
public class ThirdDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 3)
    private String code;

    @NotNull
    @Size(max = 60)
    private String name;

    @Size(max = 500)
    private String comments;

    private Set<AddressDTO> address3S = new HashSet<>();

    private Long thirdClassification9Id;

    private String thirdClassification9Name;

    private Long civility1Id;

    private String civility1Name;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Set<AddressDTO> getAddress3S() {
        return address3S;
    }

    public void setAddress3S(Set<AddressDTO> addresses) {
        this.address3S = addresses;
    }

    public Long getThirdClassification9Id() {
        return thirdClassification9Id;
    }

    public void setThirdClassification9Id(Long thirdclassificationId) {
        this.thirdClassification9Id = thirdclassificationId;
    }

    public String getThirdClassification9Name() {
        return thirdClassification9Name;
    }

    public void setThirdClassification9Name(String thirdclassificationName) {
        this.thirdClassification9Name = thirdclassificationName;
    }

    public Long getCivility1Id() {
        return civility1Id;
    }

    public void setCivility1Id(Long civilityId) {
        this.civility1Id = civilityId;
    }

    public String getCivility1Name() {
        return civility1Name;
    }

    public void setCivility1Name(String civilityName) {
        this.civility1Name = civilityName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ThirdDTO thirdDTO = (ThirdDTO) o;
        if(thirdDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), thirdDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ThirdDTO{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", name='" + getName() + "'" +
            ", comments='" + getComments() + "'" +
            "}";
    }
}
