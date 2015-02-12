package nz.govt.doc.t1m.domain.image;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 */
@Entity
public class ImageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer imageId;
    public Integer getImageId() { return imageId; }

    @NotNull
    protected Integer instanceId;
    public Integer getInstanceId() { return instanceId; }
    public void setInstanceId(Integer instanceId) { this.instanceId = instanceId; }

    @NotNull
    protected String instanceType;
    public String getInstanceType() { return instanceType; }
    public void setInstanceType(String instanceType) { this.instanceType = instanceType; }

    protected String imageName;
    public String getImageName() { return imageName; }
    public void setImageName(String imageName) { this.imageName = imageName; }

    @NotNull
    @Lob
    @Column(length=1000000)
    protected byte[] image;
    public byte[] getImage() {
        return image;
    }
    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getImageString() {
        return new String(image);
    }

}
