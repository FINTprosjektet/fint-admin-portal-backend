package no.fint.adminportal.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;
import org.springframework.ldap.support.LdapNameBuilder;

import javax.naming.Name;

@ApiModel
@Data
@Entry(objectClasses = {"organizationalUnit", "top", "fintComponent"})
public class Component implements LdapEntry {

  @Id
  private Name dn;

  @ApiModelProperty(value = "Unique identifier for the component (UUID). This is automatically generated and should not be set")
  //@DnAttribute(value = "ou", index = 2)
  @Attribute(name = "ou")
  private String uuid;

  @ApiModelProperty(value = "Technical name of the component.")
  @Attribute(name = "fintCompTechnicalName")
  private String technicalName;

  @ApiModelProperty(value = "Displayname of the component.")
  @Attribute(name = "fintCompDisplayName")
  private String displayName;

  @ApiModelProperty(value = "A description of what the component does.")
  @Attribute(name = "description")
  private String description;

  @Override
  public String getDn() {
    if (dn != null) {
      return dn.toString();
    } else {
      return null;
    }
  }

  @Override
  public void setDn(Name dn) {
    this.dn = dn;

  }

  @Override
  public void setDn(String dn) {
    this.dn = LdapNameBuilder.newInstance(dn).build();
  }

  @Override
  public String getUuid() {
    return uuid;
  }

  @Override
  public void setUuid(String uuid) {
    this.uuid = uuid;
  }
}

