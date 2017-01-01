package no.fint.portal.model;

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
@Entry(objectClasses = {"inetOrgPerson", "organizationalPerson", "person", "top"})
public class Client implements BasicLdapEntry {

  @ApiModelProperty(value = "DN of the client. This is automatically set.")
  @Id
  private Name dn;

  @ApiModelProperty(value = "Username for the client. This is automatically set.")
  @Attribute(name = "cn")
  private String uuid;

  @ApiModelProperty(value = "Short description of the client")
  @Attribute(name = "sn")
  private String shortDescription;

  @ApiModelProperty(value = "OrgId of the organisation the client is connected to. This is automatically set.")
  @Attribute(name = "company")
  private String orgId;

  @ApiModelProperty(value = "A note of the client.")
  @Attribute(name = "description")
  private String note;

  @ApiModelProperty(value = "Client password.")
  @Attribute(name = "userPassword")
  private String password;

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
}