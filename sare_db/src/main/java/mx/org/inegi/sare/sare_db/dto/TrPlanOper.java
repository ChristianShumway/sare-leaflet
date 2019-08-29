/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.inegi.sare.sare_db.dto;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author LIDIA.VAZQUEZ
 */
@Entity
@Table(name = "TR_PLAN_OPER",schema = "CE2019_MASRENCAL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TrPlanOper.findAll", query = "SELECT t FROM TrPlanOper t"),
    @NamedQuery(name = "TrPlanOper.findByIdCop", query = "SELECT t FROM TrPlanOper t WHERE t.idCop = :idCop"),
    @NamedQuery(name = "TrPlanOper.findByCveOperativa", query = "SELECT t FROM TrPlanOper t WHERE t.cveOperativa = :cveOperativa")})
public class TrPlanOper implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_COP")
    private Long idCop;
    @Column(name = "CVE_OPERATIVA")
    private String cveOperativa;
    @OneToMany(mappedBy = "idCop")
    private Collection<TrPredios> trPrediosCollection;

    public TrPlanOper() {
    }

    public TrPlanOper(Long idCop) {
        this.idCop = idCop;
    }

    public Long getIdCop() {
        return idCop;
    }

    public void setIdCop(Long idCop) {
        this.idCop = idCop;
    }

    public String getCveOperativa() {
        return cveOperativa;
    }

    public void setCveOperativa(String cveOperativa) {
        this.cveOperativa = cveOperativa;
    }

    @XmlTransient
    public Collection<TrPredios> getTrPrediosCollection() {
        return trPrediosCollection;
    }

    public void setTrPrediosCollection(Collection<TrPredios> trPrediosCollection) {
        this.trPrediosCollection = trPrediosCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCop != null ? idCop.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TrPlanOper)) {
            return false;
        }
        TrPlanOper other = (TrPlanOper) object;
        if ((this.idCop == null && other.idCop != null) || (this.idCop != null && !this.idCop.equals(other.idCop))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.org.inegi.sare.sare_db.dto.TrPlanOper[ idCop=" + idCop + " ]";
    }
    
}
