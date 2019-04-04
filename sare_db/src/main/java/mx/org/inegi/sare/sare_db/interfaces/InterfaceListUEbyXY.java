/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.inegi.sare.sare_db.interfaces;

import java.util.List;
import mx.org.inegi.sare.sare_db.dto.cat_IdentifyVialDTO;
import mx.org.inegi.sare.sare_db.dto.cat_InfoInmueblesDTO;

/**
 *
 * @author LIDIA.VAZQUEZ
 */
public interface InterfaceListUEbyXY {
    
    public List<cat_InfoInmueblesDTO> getInfoUEDenue(Integer proyecto, String x, String y);
    
    public List<cat_InfoInmueblesDTO> getInfoUE(Integer proyecto, String x, String y, String tipo);
    
    public List<cat_IdentifyVialDTO> getInfoVialidad(Integer proyecto, String x, String y);
    
}
