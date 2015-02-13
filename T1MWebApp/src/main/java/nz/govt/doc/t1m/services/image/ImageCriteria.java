package nz.govt.doc.t1m.services.image;

import nz.govt.doc.t1m.domain.criteria.AbstractCriteria;

import java.util.Date;

/**
 */
public class ImageCriteria extends AbstractCriteria {

    private Integer imageId;
    private Integer instanceId;
    private String instanceType;
    private String imageName;
    private String image;

    private String imageCriteria;

    public ImageCriteria() {
        super();
    }

    public Integer getImageId() {
        return imageId;
    }

    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }

    public Integer getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(Integer instanceId) {
        this.instanceId = instanceId;
    }

    public String getInstanceType() {
        return instanceType;
    }

    public void setInstanceType(String instanceType) {
        this.instanceType = instanceType;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImageCriteria() {
        return imageCriteria;
    }

    public void setImageCriteria(String imageCriteria) {
        this.imageCriteria = imageCriteria;
    }
}
