package nz.govt.doc.t1m.services.incoming;

import nz.govt.doc.t1m.domain.image.ImageEntity;
import nz.govt.doc.t1m.services.image.ImageService;
import nz.govt.doc.t1m.services.incoming.ImageForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by McCaulC on 9/02/2015.
 */
@Component
public class ImageParser {

    @Autowired
    protected ImageService imageService;

    public ImageEntity saveImage(ImageForm imageForm) {
        ImageEntity imageEntity = new ImageEntity();
        imageEntity.setInstanceId(-1);
        imageEntity.setInstanceType("testType");
        imageEntity.setImage(imageForm.getImg().getBytes());
        System.out.println("Length: " + imageEntity.getImage().length);
        return imageEntity;
    }

    public ImageEntity saveImage(Integer id, String type, String image) {
        System.out.println("!!--- ER MAH GERD I ACTUALLY GOT THE PHOTO ---!!");
        ImageEntity imageEntity = new ImageEntity();
        imageEntity.setInstanceId(id);
        imageEntity.setInstanceType(type);
        imageEntity.setImage(image.getBytes());
        ImageEntity res = imageService.saveImage(imageEntity);
        return imageEntity;
    }

}
