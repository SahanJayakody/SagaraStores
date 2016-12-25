/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author SahaN
 */
@Entity
@Table(name = "wholesalecustomer")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Wholesalecustomer.findAll", query = "SELECT w FROM Wholesalecustomer w"),
    @NamedQuery(name = "Wholesalecustomer.findById", query = "SELECT w FROM Wholesalecustomer w WHERE w.id = :id"),
    @NamedQuery(name = "Wholesalecustomer.findAllByName", query = "SELECT w FROM Wholesalecustomer w WHERE w.name like :name"),
    @NamedQuery(name = "Wholesalecustomer.findByShopname", query = "SELECT w FROM Wholesalecustomer w WHERE w.shopname = :shopname"),
    @NamedQuery(name = "Wholesalecustomer.findByContact1", query = "SELECT w FROM Wholesalecustomer w WHERE w.contact1 = :contact1"),
    @NamedQuery(name = "Wholesalecustomer.findByContact2", query = "SELECT w FROM Wholesalecustomer w WHERE w.contact2 = :contact2")})
public class Wholesalecustomer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "shopname")
    private String shopname;
    @Lob
    @Column(name = "address")
    private String address;
    @Column(name = "contact1")
    private String contact1;
    @Column(name = "contact2")
    private String contact2;

    public Wholesalecustomer() {
    }

    public Wholesalecustomer(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public boolean setName(String name) {
        boolean validity = name != null && name.matches("^[_A-Za-z]{3}[_A-Za-z]*([_A-Za-z]*\\s[_A-Za-z]*)*");
        if (validity) {
            this.name = name;
        } else {
            this.name = null;
        }
        return validity;
    }

    public String getShopname() {
        return shopname;
    }

    public boolean setShopname(String shopname) {
        boolean validity = shopname != null && shopname.matches("^[_A-Za-z]{3}[_A-Za-z]*([_A-Za-z]*\\s[_A-Za-z]*)*");
        if (validity) {
            this.shopname = shopname;
        } else {
            this.shopname = null;
        }
        return validity;
    }

    public String getAddress() {
        return address;
    }

    public boolean setAddress(String address) {
        boolean validity = address != null && address.matches("^[_A-Za-z0-9-/.:,\\s]{5}[_A-Za-z0-9-/.:,\\s]*");
        if (validity) {
            this.address = address;
        } else {
            this.address = null;
        }
        return validity;
    }

    public String getContact1() {
        return contact1;
    }

    public boolean setContact1(String contact1) {
        boolean validity = contact1 != null && contact1.matches("0\\d{9}");
        if (validity) {
            this.contact1 = contact1;
        } else {
            this.contact1 = null;
        }
        return validity;
    }

    public String getContact2() {
        return contact2;
    }

    public boolean setContact2(String contact2) {
        boolean validity;
        if (contact2 == null || contact2.isEmpty()) {
            validity = true;
            this.contact2 = null;
        } else {
            contact2 = contact2.trim();
            if (contact2.matches("0\\d{9}")) {
                this.contact2 = contact2;
                validity = true;
            } else {
                this.contact2 = null;
                validity = false;
            }
        }
        return validity;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Wholesalecustomer)) {
            return false;
        }
        Wholesalecustomer other = (Wholesalecustomer) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Wholesalecustomer[ id=" + id + " ]";
    }

}
