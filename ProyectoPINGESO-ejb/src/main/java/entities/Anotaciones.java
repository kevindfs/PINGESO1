/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Italo
 */
@Entity
@Table(name = "anotaciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Anotaciones.findAll", query = "SELECT a FROM Anotaciones a"),
    @NamedQuery(name = "Anotaciones.findById", query = "SELECT a FROM Anotaciones a WHERE a.id = :id"),
    @NamedQuery(name = "Anotaciones.findByGen", query = "SELECT a FROM Anotaciones a WHERE a.gen = :gen"),
    @NamedQuery(name = "Anotaciones.findByTermino", query = "SELECT a FROM Anotaciones a WHERE a.termino = :termino")})
public class Anotaciones implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Size(max = 20)
    @Column(name = "GEN")
    private String gen;
    @Column(name = "TERMINO")
    private Integer termino;

    public Anotaciones() {
    }

    public Anotaciones(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGen() {
        return gen;
    }

    public void setGen(String gen) {
        this.gen = gen;
    }

    public Integer getTermino() {
        return termino;
    }

    public void setTermino(Integer termino) {
        this.termino = termino;
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
        if (!(object instanceof Anotaciones)) {
            return false;
        }
        Anotaciones other = (Anotaciones) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Anotaciones[ id=" + id + " ]";
    }
    
}
