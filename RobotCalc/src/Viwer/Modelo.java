
package Viwer;

/**
 *
 * @author Josev
 */
class Modelo {
    
    private int vaoId;
    
    private int vertexCount;
    
    public Modelo(int id, int count){
        vaoId = id;
        vertexCount = count;
    }
    
    public int getVaoId(){
        return vaoId;
    }
    
    public int getVertexCount(){
        return vertexCount;
    }
    
    
}
