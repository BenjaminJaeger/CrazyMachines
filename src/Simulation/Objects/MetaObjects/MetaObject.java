package Simulation.Objects.MetaObjects;

public abstract class MetaObject {
    protected String objectName, objectImageURL;

    public MetaObject (String objectName, String objectImageURL) {
        this.objectName = objectName;
        this.objectImageURL = objectImageURL;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public String getObjectImageURL() {
        return objectImageURL;
    }

    public void setObjectImageURL(String objectImageURL) {
        this.objectImageURL = objectImageURL;
    }

    public abstract void createObject (float x, float y);
}
