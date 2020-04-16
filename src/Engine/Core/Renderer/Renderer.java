package Engine.Core.Renderer;

import static com.jogamp.opengl.GL.GL_BLEND;
import static com.jogamp.opengl.GL.GL_COLOR_BUFFER_BIT;
import static com.jogamp.opengl.GL.GL_CULL_FACE;
import static com.jogamp.opengl.GL.GL_DEPTH_BUFFER_BIT;
import static com.jogamp.opengl.GL.GL_DEPTH_TEST;
import static com.jogamp.opengl.GL.GL_FRONT_AND_BACK;
import static com.jogamp.opengl.GL.GL_ONE_MINUS_SRC_ALPHA;
import static com.jogamp.opengl.GL.GL_SRC_ALPHA;
import static com.jogamp.opengl.GL.GL_TRIANGLES;
import static com.jogamp.opengl.GL.GL_UNSIGNED_INT;
import static com.jogamp.opengl.GL2GL3.GL_FILL;
import static com.jogamp.opengl.GL2GL3.GL_LINE;

import com.jogamp.opengl.GL4;
import com.jogamp.opengl.GLContext;

import Engine.Core.Config;
import Engine.Core.Camera.Camera;
import Engine.Core.Lights.AmbientLight;
import Engine.Core.Lights.DirectionalLight;
import Engine.Core.Lights.PointLight;
import Engine.Core.Math.Matrix4f;
import Engine.Core.Models.InstancedModel;
import Engine.Core.Models.Model;
import Engine.Core.Models.loadToGPU;
import Engine.Core.Shaders.Core.BasicShader;
import Objects.GameObject;

/**
 * 
 * Handles the rendering of a model to the screen. 
 * @author Simon Weck
 * 
 */
public class Renderer {

	private Matrix4f modelViewProjectionMatrix; //combination of model view and projection matrix
	private Matrix4f projectionMatrix;
	private Camera camera; 
	
	/**
	 * creates a renderer and sets it up depending on the config based on the Config.java class
	 */
	public Renderer(Camera camera,Matrix4f projectionMatrix4f) {
		GL4 gl=(GL4)GLContext.getCurrentGL(); 
		modelViewProjectionMatrix=new Matrix4f();
		this.camera=camera;
		this.projectionMatrix=projectionMatrix4f; 
	 
		//start settings of the renderer
		gl.glEnable(GL_DEPTH_TEST); 
		gl.glEnable(GL_BLEND);
		gl.glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
	}
	
	/**
	 * Clears the frame.
	 * Clears color and depth buffers and clears the frame with the color in the config class. by default its white
	 * Also activates wireframe mode or backfaceculling if settings in the config class changed.
	 */
	public void clear() {
		GL4 gl=(GL4)GLContext.getCurrentGL();
		gl.glClearColor(Config.BACKGROUND_COLOR.x, Config.BACKGROUND_COLOR.y,Config.BACKGROUND_COLOR.z,1); //Clears every pixel with a specific color
		gl.glClear(GL_COLOR_BUFFER_BIT);  //Clears color buffer
		gl.glClear(GL_DEPTH_BUFFER_BIT);//Clears the depth buffer
		wireframeMode(); //checks if wireframe mode is enabled
		backFacCulling(); //checks if backfaceculling is enabled
	}
	
	/**
	 * Renders a model to the screen with the current active shader program.
	 * 
	 * 0.activate shader
	 * 1.upload material of the model to the shader.
	 * 2.upload all lights that are available to the shader.
	 * 3.check if model moved. if yes recalculate model matrix using the transformations of the model. if no use unchanged model matrix from the model.
	 * 4.upload model matrix to the shader.
	 * 5.check if camera moved. if yes recalculate view matrix using the transformations of the camera. if no use unchanged view matrix from the camera.
	 * 6.upload view matrix to the shader.
	 * 7.concatenate model view and projection matrix to one matrix and upload it to the shader.
	 * 8.check if the mesh is a triangle mesh or line mesh
	 * 9.Activate vao of the model and render it using index buffer. (glDrawElements)
	 * 
	 * @param model
	 * 			-Model to be rendered
	 * @param shader
	 * 			-Shader of the model
	 */	
	@SuppressWarnings("exports")
	public void render(GameObject object,BasicShader shader) {
		GL4 gl=(GL4)GLContext.getCurrentGL();
		
		for (Model model : object.getModels()) {			
			shader.use(); //activate shader before rendering
			shader.uploadMaterial(model.getMaterial());		
			shader.uploadAmbientLight(AmbientLight.getAmbientLight());
			shader.uploadPointLights(PointLight.getPointLights());
			shader.uploadDirectionalLight(DirectionalLight.getDirectionalLights());
				
			
			if (model.getMatrixUpdate()) {
				model.getModelMatrix().changeToModelMatrix(model);
				model.setMatrixUpdate(false);
			}
			shader.uploadModelMatrix(model.getModelMatrix());
			
			
			if (camera.getMatrixUpdate()) {
				camera.getViewMatrix().changeToViewMatrix(camera);
				camera.setMatrixUpdate(false);
			}		
			shader.uploadViewMatrix(camera.getViewMatrix());
				
			Matrix4f.changeToModelViewProjectionMatrix(camera.getViewMatrix(), model.getModelMatrix(),projectionMatrix, modelViewProjectionMatrix);		
			shader.uploadModelViewProjectionMatrix(modelViewProjectionMatrix);
					
			shader.uploadProjectionMatrix(projectionMatrix);
			
			gl.glBindVertexArray(model.getMesh().getVaoID());//activates the specific VAO
			gl.glDrawElements(GL_TRIANGLES, model.getMesh().getIndexCount(), GL_UNSIGNED_INT, 0); //draws with the usage of indices 	
		}
	}
	
	
	
	/**
	 * Renders a planetEntity (rendering a instancedModel)
	 * 
	 * 0.activate shader of the entity
	 * 1.upload all lights to the shader
	 * 2.upload view and projection matrix (its the same for all models and instances)
	 * for every model of the entity (each entity can have multiple models e.g. tree tribe and top of the tree build one tree entity) do the following:
	 * 		3. upload material of the model (each entity could also have its own material but I decided not to)
	 * 		for every instance (amount of times we want to render the entity) of the model do the following:
	 * 			4.check if the instance successfully got placed on a planet. (if there is too much water a tree cant be placed) dont render if its not placed
	 * 			5.Create model matrix out of the transformations of the current instance.
	 * 		6.upload all matrices as huge array so the shader can access a matrix for every instance
	 * 		7.activate vao and draw the model multiple times to the screen using the matrices with the useg of glDrawElementsInstanced
	 * 
	 * @param entity
	 */
	public void render(InstancedModel model, BasicShader shader) {
		GL4 gl=(GL4)GLContext.getCurrentGL();
		float[] matrixData = new float[16*model.getInstances()];
			
		//activate shader before rendering
		shader.use(); 
		//upload light
		shader.uploadAmbientLight(AmbientLight.getAmbientLight());
		shader.uploadPointLights(PointLight.getPointLights());
		shader.uploadDirectionalLight(DirectionalLight.getDirectionalLights());		
		
		//upload view matrix
		if (camera.getMatrixUpdate()) {
			camera.getViewMatrix().changeToViewMatrix(camera);
			camera.setMatrixUpdate(false);
		}
		shader.uploadViewMatrix(camera.getViewMatrix());
				
		//upload projection matrix
		shader.uploadProjectionMatrix(projectionMatrix);
		
		shader.uploadMaterial(model.getMaterial());		
		
			for (int j = 0; j < model.getInstances(); j++) {		
						
					//reset matrix
					model.getModelMatrix()[j].setIdentityMatrix();													
										
					//translation
					model.getModelMatrix()[j].translate(model.getX(j),model.getY(j),model.getZ(j)); 
					
					//rotation
					model.getModelMatrix()[j].rotateX(model.getRotationX()[j]);
					model.getModelMatrix()[j].rotateY(model.getRotationY()[j]);
					model.getModelMatrix()[j].rotateZ(model.getRotationZ()[j]);
					
					//scale
					model.getModelMatrix()[j].scale(model.getScaleX()[j],model.getScaleY()[j],model.getScaleZ()[j]); 				
						
					//save matrix into array so it can get uploaded
					matrixData[j*16] = model.getModelMatrix()[j].m00; 
					matrixData[j*16+1] = model.getModelMatrix()[j].m10; 
					matrixData[j*16+2] = model.getModelMatrix()[j].m20; 
					matrixData[j*16+3] = model.getModelMatrix()[j].m30; 
					matrixData[j*16+4] = model.getModelMatrix()[j].m01; 
					matrixData[j*16+5] = model.getModelMatrix()[j].m11;  
					matrixData[j*16+6] = model.getModelMatrix()[j].m21; 
					matrixData[j*16+7] = model.getModelMatrix()[j].m31; 
					matrixData[j*16+8] = model.getModelMatrix()[j].m02; 
					matrixData[j*16+9] = model.getModelMatrix()[j].m12; 
					matrixData[j*16+10] = model.getModelMatrix()[j].m22; 
					matrixData[j*16+11] = model.getModelMatrix()[j].m32; 
					matrixData[j*16+12] = model.getModelMatrix()[j].m03; 
					matrixData[j*16+13] = model.getModelMatrix()[j].m13; 
					matrixData[j*16+14] = model.getModelMatrix()[j].m23; 
					matrixData[j*16+15] = model.getModelMatrix()[j].m33; 
					
			loadToGPU.updateVBO(model.getMatrixVBOID(),matrixData);
										
			gl.glBindVertexArray(model.getInstancedMesh().getVaoID()); //activates the specific VAO
			gl.glDrawElementsInstanced(GL_TRIANGLES,  model.getInstancedMesh().getIndexCount(), GL_UNSIGNED_INT, 0, model.getInstances());			
		}
	}
	
	
	
	/**
	 * sets the glPolygonMode to glLine or glFill depending on the setting inside the config class.
	 * drawing in glline mode is the wireframe mode
	 */
	private void wireframeMode() {
		GL4 gl=(GL4)GLContext.getCurrentGL();
		if (Config.WIREFRAME_MODE) 
			gl.glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);	
		else
			gl.glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);
	}
	
	/**
	 * activates back face culling depending on the setting inside the config class.
	 */
	public void backFacCulling() {
		GL4 gl=(GL4)GLContext.getCurrentGL();
		if (Config.BACK_FACE_CULLING) 
			gl.glEnable(GL_CULL_FACE);
		else
			gl.glDisable(GL_CULL_FACE);
	}
	
	public Matrix4f getProjectionMatrix() {
		return projectionMatrix;
	}
	
}
