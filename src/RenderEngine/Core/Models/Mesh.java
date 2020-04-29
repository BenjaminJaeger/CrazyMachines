package RenderEngine.Core.Models;

import RenderEngine.Core.Math.Vector3f;
import RenderEngine.Primitives.Primitive;

/**
 * 
 * Represents a raw triangle mesh. The Mesh can get uploaded to the gpu.
 * @author Simon Weck
 */
public class Mesh {
	
	//ids to access VAO and VBOs
	private int vaoID;
	private int vertexVBOID;
	private int indexVBOID;
	private int normalVBOID;
	private int colorVBOID;
	private int textureCoordinatesID;
	private int textureID;
	
	//mesh data 
	private int indexCount;
	private float[] colors;
	private float[] vertices;
	private float[] baseVertices;
	private int[] indices;
	private float[] normals;
	private float[] textureCoordinates;
	private String textureFilePath;
	

	public Mesh(String file,float[] colors) { 
		OBJParser parser=new OBJParser(file);
		baseVertices=parser.getVertices();
		vertices=parser.getVertices();
		indices=parser.getIndices();
		indexCount=indices.length;
		this.colors=colors;
//		normals=parser.getNormals(); // normals dont get read properly
		calculateNormals();
		loadToGPU.loadMeshToGPU(this);
	}
	
	public Mesh(String file,float r,float g,float b) { 
		OBJParser parser=new OBJParser(file);
		baseVertices=parser.getVertices();
		vertices=parser.getVertices();
		indices=parser.getIndices();
		indexCount=indices.length;
		this.colors=new float[vertices.length];
		
		for (int i = 0; i < this.colors.length; i+=3) {
			this.colors[i] =  r;
			this.colors[i+1] = g;
			this.colors[i+2] =  b;
		}
		
//		normals=parser.getNormals(); // normals dont get read properly
		calculateNormals();
		loadToGPU.loadMeshToGPU(this);
	}

	public Mesh(Primitive primitive,float[] colors) { 
		baseVertices=primitive.getVertices();
		vertices=primitive.getVertices();
		indices=primitive.getIndices();
		if(indices!=null) {
			calculateNormals();
			indexCount=indices.length;	
		}
		this.colors=colors;		
		loadToGPU.loadMeshToGPU(this);
	}
	
	public Mesh(Primitive primitive,float r,float g,float b) { 
		baseVertices=primitive.getVertices();
		vertices=primitive.getVertices();
		indices=primitive.getIndices();
		if(indices!=null) {
			calculateNormals();
			indexCount=indices.length;	
		}
		this.colors=new float[vertices.length];
		
		for (int i = 0; i < this.colors.length; i+=3) {
			this.colors[i] =  r;
			this.colors[i+1] =  g;
			this.colors[i+2] = b;
		}
		
		loadToGPU.loadMeshToGPU(this);
	}
	/**
	 * creates a mesh with a texture on it.
	 * @param textureCoordinates
	 * 		-array containing the texture coordinate of each vertex
	 * @param textureFilePath
	 * 		-path of the texture file
	 */
	/*
	public Mesh(int[]indices, float[] vertices, float[] textureCoordinates,String textureFilePath) {
		baseVertices=vertices;
		this.indexCount = indices.length;
		this.vertices=vertices;
		this.indices=indices;
		this.textureCoordinates=textureCoordinates;
		this.textureFilePath=textureFilePath;
		loadToGPU.loadMeshToGPU(this);
	}
	*/
	
	/**
	 * Calculates the normals out of vertices and indices
	 */
	public void calculateNormals() {
		normals=new float[vertices.length];
		
		for (int i = 0; i < indices.length; i+=3) {
			Vector3f v1= new Vector3f(vertices[indices[i]*3], vertices[indices[i]*3+1], vertices[indices[i]*3+2]);
			Vector3f v2= new Vector3f(vertices[indices[i+1]*3], vertices[indices[i+1]*3+1], vertices[indices[i+1]*3+2]);		
			Vector3f v3= new Vector3f(vertices[indices[i+2]*3], vertices[indices[i+2]*3+1], vertices[indices[i+2]*3+2]);
			Vector3f v = Vector3f.subtract(v3, v1);
			Vector3f u = Vector3f.subtract(v2, v1);				
			Vector3f normal= Vector3f.cross(u, v);
			 
			normals[indices[i]*3]+=normal.x;
			normals[indices[i]*3+1]+=normal.y;
			normals[indices[i]*3+2]+=normal.z;
			
			normals[indices[i+1]*3]+=normal.x;
			normals[indices[i+1]*3+1]+=normal.y;
			normals[indices[i+1]*3+2]+=normal.z;
			
			normals[indices[i+2]*3]+=normal.x;
			normals[indices[i+2]*3+1]+=normal.y;
			normals[indices[i+2]*3+2]+=normal.z;	
		}	
	}
	
	/**
	 * sets the vertices of this mesh and updates the corresponding VBO
	 * @param vertices
	 * 						-vertices that should be updated
	 * @param calculateNormals
	 * 						-true= calculate normals and updates the corresponding VBO // false= no calculation of the normals
	 */
	public void setVertices(float[] vertices,boolean calculateNormals) {
		this.vertices = vertices;
		if (calculateNormals) {
			calculateNormals();
			loadToGPU.updateVBO(normalVBOID, normals);	 //updates the normal VBO
		}	
		loadToGPU.updateVBO(vertexVBOID, vertices);		
	}

	/**
	 * Sets indices of this mesh and updates the corresponding VBO
	 */
	public void setIndices(int[] indices) {
		this.indices = indices;
		loadToGPU.updateIndexVBO(this);
	}
	
	/**
	 * Sets normals of this mesh and updates the corresponding VBO
	 */
	public void setNormals(float[] normals) {
		this.normals = normals;
		loadToGPU.updateVBO(normalVBOID, normals);
	}
	
	/**
	 * Sets colors of this mesh and updates the corresponding VBO
	 */
	public void setColorValues(float[] colors) {
		this.colors = colors;
		loadToGPU.updateVBO(colorVBOID, colors);
	}
	
	public int getVaoID() {
		return vaoID;
	}

	public void setVaoID(int vaoID) {
		this.vaoID = vaoID;
	}

	public float[] getVertices() {
		return vertices;
	}

	public int[] getIndices() {
		return indices;
	}

	public float[] getNormals() {
		return normals;
	}
	
	public int getIndexCount() {
		return indexCount;
	}

	public void setNormalVBOID(int normalVBOID) {
		this.normalVBOID = normalVBOID;
	}

	public void setIndexVBOID(int indexVBOID) {
		this.indexVBOID = indexVBOID;
	}

	public void setVertexVBOID(int vertexVBOID) {
		this.vertexVBOID = vertexVBOID;
	}

	public int getVertexVBOID() {
		return vertexVBOID;
	}

	public int getIndexVBOID() {
		return indexVBOID;
	}

	public int getNormalVBOID() {
		return normalVBOID;
	}
	
	public void setIndexCount(int indexCount) {
		this.indexCount=indexCount;
	}

	public float[] getColors() {
		return colors;
	}

	public int getColorVBOID() {
		return colorVBOID;
	}

	public void setColorVBOID(int colorValueVBOID) {
		this.colorVBOID = colorValueVBOID;
	}
	
	public float[] getTextureCoordinates() {
		return textureCoordinates;
	}

	public void setTextureCoordinates(float[] textureCoordinates) {
		this.textureCoordinates = textureCoordinates;
	}

	public void setTextureCoordinatesID(int textureCoordinatesID) {
		this.textureCoordinatesID=textureCoordinatesID;
	}
	
	public int getTextureCoordinatesID() {
		return textureCoordinatesID;
	}

	public int getTextureID() {
		return textureID;
	}

	public void setTextureID(int textureID) {
		this.textureID = textureID;
	}

	public String getTextureFilePath() {
		return textureFilePath;
	}

	public void setTextureFilePath(String textureFilePath) {
		this.textureFilePath = textureFilePath;
	}

	public float[] getBaseVertices() {
		return baseVertices;
	}
	
}
