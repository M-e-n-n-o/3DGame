package shaders;

import entities.Camera;
import entities.Light;
import org.lwjgl.util.vector.Matrix4f;
import toolbox.Maths;

public class TerrainShader extends ShaderProgram {

    private static final String VERTEX_FILE = "src/shaders/terrainVertexShader.txt";
    private static final String FRAGMENT_FILE = "src/shaders/terrainFragmentShader.txt";

    private int location_transformationMatrix;
    private int location_projectionMatrix;
    private int location_viewMatrix;
    private int location_lightPosition;
    private int location_lightColour;
    private int location_shineDamper;
    private int location_reflectivity;
    private int location_backgroundTexture;
    private int location_rTexture;
    private int location_gTexture;
    private int location_bTexture;
    private int location_blendMap;

    public TerrainShader() {
        super(VERTEX_FILE, FRAGMENT_FILE);
    }

    @Override
    protected void bindAttributes() {
        super.bindAttribute(0, "position");
        super.bindAttribute(1, "textureCoordinates");
        super.bindAttribute(2, "normal");
    }

    @Override
    protected void setAllUniformLocations() {
        this.location_transformationMatrix = super.getUniformLocation("transformationMatrix");
        this.location_projectionMatrix = super.getUniformLocation("projectionMatrix");
        this.location_viewMatrix = super.getUniformLocation("viewMatrix");
        this.location_lightPosition = super.getUniformLocation("lightPosition");
        this.location_lightColour = super.getUniformLocation("lightColour");
        this.location_shineDamper = super.getUniformLocation("shineDamper");
        this.location_reflectivity = super.getUniformLocation("reflectivity");
    }

    public void loadTransformationMatrix(Matrix4f matrix) {
        super.loadMatrix(this.location_transformationMatrix, matrix);
    }

    public void loadShineVariables(float damper, float reflectivity) {
        super.loadFloat(this.location_shineDamper, damper);
        super.loadFloat(this.location_reflectivity, reflectivity);
    }

    public void loadLight(Light light) {
        super.loadVector(this.location_lightPosition, light.getPosition());
        super.loadVector(this.location_lightColour, light.getColour());
    }

    public void loadViewMatrix(Camera camera) {
        Matrix4f viewMatrix = Maths.createViewMatrix(camera);
        super.loadMatrix(this.location_viewMatrix, viewMatrix);
    }

    public void loadProjectionMatrix(Matrix4f projection) {
        super.loadMatrix(this.location_projectionMatrix, projection);
    }
}
