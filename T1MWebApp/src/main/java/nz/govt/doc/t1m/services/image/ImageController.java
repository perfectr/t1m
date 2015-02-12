package nz.govt.doc.t1m.services.image;

import nz.govt.doc.t1m.domain.image.ImageEntity;
import nz.govt.doc.t1m.domain.response.PagedResponse;
import nz.govt.doc.t1m.domain.response.Response;
import nz.govt.doc.t1m.services.incoming.ImageForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 */
@RestController
@RequestMapping("/rest/image")
public class ImageController {

    @Autowired
    protected ImageService imageService;

    @RequestMapping(value = "/{imageId}", method = RequestMethod.GET)
    public Response<ImageEntity> getImage(@PathVariable(value = "imageId") Integer imageId) {
        return imageService.findImageById(imageId);
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @ResponseBody
    public PagedResponse<ImageEntity> search(@RequestBody ImageCriteria criteria) {
        return imageService.findByCriteria(criteria);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Response<ImageEntity> saveImage(@RequestBody ImageForm imageForm) {
        return imageService.saveImage(imageForm);
    }

    @RequestMapping(value = "/{imageId}", method = RequestMethod.DELETE)
    public void removeImage(@PathVariable(value = "imageId") Integer imageId) {
        imageService.removeImage(imageId);
    }

}
