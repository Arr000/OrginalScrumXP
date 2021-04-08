/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

import Models.IPostDal;
import java.util.logging.Level;
import java.util.logging.Logger;
import oru.inf.InfException;

/**
 *
 * @author nene5
 */
public class PostDB implements IPostDal{

    @Override
    public void saveResearchPost(int id, String rubrik, String inlagg, String username) {
        
        try {
            DatabasAcess.Connect();
            var researchPostQuery = "INSERT INTO FORSKNINGSINLAGG (ID, RUBRIK, INLAGG, USERNAME) VALUES (  " + id + "," + DatabasAcess.MakeDBString(rubrik) + "," + DatabasAcess.MakeDBString(inlagg) + "," + DatabasAcess.MakeDBString(username) + ")";
            DatabasAcess.getidb().insert(researchPostQuery);
            
            
        } catch (InfException ex) {
            Logger.getLogger(PostDB.class.getName()).log(Level.SEVERE, null, ex);
        }
  
    }

    @Override
    public int getMaxIdResearchPost() {
        try {
            DatabasAcess.Connect();
            var getMaxIdQuery = "SELECT MAX(ID) FROM FORSKNINGSINLAGG ";
            var result = DatabasAcess.getidb().fetchSingle(getMaxIdQuery);
            
            if(result != null)
            {
                return Integer.parseInt(result);
            }
        } catch (InfException ex) {
            Logger.getLogger(PostDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
}
