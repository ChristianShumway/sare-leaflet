/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.inegi.sare.sare_db.interfaces;

import java.util.List;
import mx.org.inegi.sare.sare_db.dto.TrPredios;
import mx.org.inegi.sare.sare_db.dto.resultadoPrediosEtqVal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author LIDIA.VAZQUEZ
 */
public interface InmueblesRepository extends JpaRepository<TrPredios, Integer> {
    
    @Query("SELECT new mx.org.inegi.sare.sare_db.dto.resultadoPrediosEtqVal(d.ST_SARE, d.ID_UE, e.E03, e.E08) "
			+ "FROM TrPredios d INNER JOIN d.TrEtqVal e")
	List<resultadoPrediosEtqVal> fetchEmpDeptDataLeftJoin();
    
}
